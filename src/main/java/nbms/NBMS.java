package nbms;

import nbms.getData.HeaderData;
import nbms.getData.HeaderDataImpl;
import nbms.getData.MainData;
import nbms.getData.MainDataImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NBMS {
    private final List<String> headerField = new ArrayList<>();
    private final List<String> mainDataField = new ArrayList<>();
    private final HeaderData headerData;
    private final MainData mainData;
    boolean loadingFinish;

    // Constructor to initialize HeaderData and MainData
    public NBMS() {
        this.headerData = new HeaderDataImpl();
        this.mainData = new MainDataImpl();
    }

    public HeaderData getHeaderData() {
        return headerData;
    }

    public MainData getMainData() {
        return mainData;
    }

    public void loadFile(String fileName) throws IOException {
        loadingFinish = false;
        ClassLoader classLoader = getClass().getClassLoader();
        File nbmsFile = convertInputStreamToFile(classLoader.getResourceAsStream(fileName), fileName);

        System.out.println("File: " + Objects.requireNonNull(nbmsFile).getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(nbmsFile)))) {
            String line;
            boolean inHeaderField = false;
            boolean inMainDataField = false;
            boolean randomBlockActive = false;
            int randomValue = -1;
            boolean conditionMet = false;

            while ((line = br.readLine()) != null) {
                if (line.equals("") || line.equals(" ")) {
                    continue;
                }

                if (line.equals("*---------------------- HEADER FIELD")) {
                    inHeaderField = true;
                    inMainDataField = false;
                    randomBlockActive = false;
                } else if (line.equals("*---------------------- MAIN DATA FIELD")) {
                    inHeaderField = false;
                    inMainDataField = true;
                    randomBlockActive = false;
                } else if (line.startsWith("#RANDOM")) {
                    String[] parts = line.split(" ");
                    int maxValue = Integer.parseInt(parts[1].trim());
                    randomValue = (int) (Math.random() * maxValue) + 1;
                    randomBlockActive = true;
                } else if (line.startsWith("#IF")) {
                    if (!randomBlockActive) continue;
                    String[] parts = line.split(" ");
                    int ifValue = Integer.parseInt(parts[1].trim());
                    conditionMet = (randomValue == ifValue);
                } else if (line.startsWith("#ENDIF")) {
                    if (!randomBlockActive) continue;
                    conditionMet = false;
                } else if (line.startsWith("#ENDRANDOM")) {
                    randomBlockActive = false;
                    randomValue = -1;
                } else if (randomBlockActive) {
                    if (conditionMet) {
                        if (inHeaderField) {
                            headerField.add(line);
                        } else if (inMainDataField) {
                            mainDataField.add(line);
                        }
                    }
                } else {
                    if (inHeaderField) {
                        headerField.add(line);
                    } else if (inMainDataField) {
                        mainDataField.add(line);
                    }
                }
            }
        }

        headerData.parseHeaderField(headerField);
        mainData.parseMainDataField(mainDataField);
    }

    /**
     * @deprecated maybe who need...
     * */
    public boolean isLoadingSuccess() {
        return loadingFinish;
    }

    private File convertInputStreamToFile(InputStream inputStream, String filePath) throws IOException {
        File file = new File(filePath);

        if (file.exists()) {
            return file;
        }

        if (inputStream != null) {
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            }
        }

        return file;
    }
}