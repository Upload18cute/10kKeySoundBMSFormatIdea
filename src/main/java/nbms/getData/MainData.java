package nbms.getData;

import java.util.*;

public class MainData {
    private final Map<String, String> note = new HashMap<>();
    private final List<Map.Entry<String, String>> noteEntries = new ArrayList<>();

    public void parseMainDataField(List<String> mainDataField) {
        for (String line : mainDataField) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                note.put(key, value);
            }
        }
        noteEntries.addAll(note.entrySet());
    }

    public int getNoteCount() {
        return note.size();
    }

    public NoteEntry getNote(int index) {
        if (index >= 0 && index < noteEntries.size()) {
            Map.Entry<String, String> entry = noteEntries.get(index);
            return new NoteEntry(entry);
        }
        throw new IndexOutOfBoundsException("Index out of range.");
    }

    public static class NoteEntry {
        private final Map.Entry<String, String> entry;
        int bar;
        int line;
        int split;
        int index;
        int type;

        private NoteEntry(Map.Entry<String, String> entry) {
            this.entry = entry;
            String[] frontData = entry.getKey().split("\\.");
            if (frontData.length == 5) {
                frontData[0] = frontData[0].replace("#", "");
                try {
                    this.bar = Integer.parseInt(frontData[0]);
                    this.line = Integer.parseInt(frontData[1]);
                    this.split = Integer.parseInt(frontData[2]);
                    this.index = Integer.parseInt(frontData[3]);
                    this.type = Integer.parseInt(frontData[4]);
                } catch (NumberFormatException e) {
                    // Handle the case where parsing fails
                    System.err.println("Failed to parse key parts: " + e.getMessage());
                }
            }
        }

        /**
         * @return default bms bar value
         * */
        public int getBar() {
            return this.bar;
        }

        /**
         * @return default bms line value (example: line 16 was turntable)
         * */
        public int getLine() {
            return this.line;
        }

        /**
         * @return 1 ~ 128
         * */
        public int getSplit() {
            return this.split;
        }

        /**
         * @return 0 ~ (split - 1)
         * */
        public int getIndex() {
            return this.index;
        }

        /**
         * @return 0: short 1: long
         * */
        public int getType() {
            return this.type;
        }

        /**
         * @return front data (bar.line.split.index.type)
         * */
        public String getKey() {
            return entry.getKey();
        }

        /**
         * @return wav index (WAV00000)
         * */
        public String getWav() {
            return entry.getValue();
        }
    }
}
