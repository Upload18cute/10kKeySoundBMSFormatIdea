package nbms.getData;

import java.util.*;

public class MainDataImpl implements MainData {
    private final Map<String, String> note = new HashMap<>();
    private final List<Map.Entry<String, String>> noteEntries = new ArrayList<>();

    @Override
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

    @Override
    public int getNoteCount() {
        return note.size();
    }

    @Override
    public NoteEntry getNote(int index) {
        if (index >= 0 && index < noteEntries.size()) {
            Map.Entry<String, String> entry = noteEntries.get(index);
            return new NoteEntryImpl(entry);
        }
        throw new IndexOutOfBoundsException("Index out of range.");
    }

    public static class NoteEntryImpl implements NoteEntry {
        private final Map.Entry<String, String> entry;
        private int bar;
        private String line;
        private int split;
        private int index;
        private int type;

        public NoteEntryImpl(Map.Entry<String, String> entry) {
            this.entry = entry;
            String[] frontData = entry.getKey().split("\\.");
            if (frontData.length == 5) {
                frontData[0] = frontData[0].replace("#", "");
                try {
                    this.bar = Integer.parseInt(frontData[0]);
                    this.line = frontData[1];
                    this.split = Integer.parseInt(frontData[2]);
                    this.index = Integer.parseInt(frontData[3]);
                    this.type = Integer.parseInt(frontData[4]);
                } catch (NumberFormatException e) {
                    // Handle the case where parsing fails
                    System.err.println("Failed to parse key parts: " + e.getMessage());
                }
            } else if (frontData.length == 2 && frontData[1].equals("02")) {
                frontData[0] = frontData[0].replace("#", "");
                this.bar = Integer.parseInt(frontData[0]);
                this.line = frontData[1];
            }
        }

        @Override
        public int getBar() {
            return this.bar;
        }

        @Override
        public String getLine() {
            return this.line;
        }

        @Override
        public int getSplit() {
            return this.split;
        }

        @Override
        public int getIndex() {
            return this.index;
        }

        @Override
        public int getType() {
            return this.type;
        }

        @Override
        public String getKey() {
            return entry.getKey();
        }

        @Override
        public String getWav() {
            return entry.getValue();
        }
    }
}