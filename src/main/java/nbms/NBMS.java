package nbms;

import nbms.getData.HeaderData;
import nbms.getData.MainData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NBMS {
    private final List<String> headerField = new ArrayList<>();
    private final List<String> mainDataField = new ArrayList<>();
    private final HeaderData headerData;
    private final MainData mainData;
    private boolean loadingFinish;

    // Constructor to initialize HeaderData and MainData
    public NBMS() {
        this.headerData = new HeaderData();
        this.mainData = new MainData();
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

            while ((line = br.readLine()) != null) {
                if (line.equals("") || line.equals(" ")) {
                    continue;
                }

                if (line.equals("*---------------------- HEADER FIELD")) {
                    inHeaderField = true;
                    inMainDataField = false;
                } else if (line.equals("*---------------------- MAIN DATA FIELD")) {
                    inHeaderField = false;
                    inMainDataField = true;
                } else if (inHeaderField) {
                    headerField.add(line);
                } else if (inMainDataField) {
                    mainDataField.add(line);
                }
            }
        }

        headerData.parseHeaderField(headerField);
        mainData.parseMainDataField(mainDataField);
        loadingFinish = true;
    }

    public boolean loadingSuccess() {
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