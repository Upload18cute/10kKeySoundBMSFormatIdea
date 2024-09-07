package nbms.getData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderData {
    private String genre;
    private String title;
    private String artist;
    private String stageFile;

    private int player;
    private int bpm;
    private int playLevel;
    private int rank;
    private int total;

    private final Map<Integer, String> wav = new HashMap<>();

    /**
     * @return Genre
     * */
    public String getGenre() {
        return genre;
    }

    /**
     * @return Title
     * */
    public String getTitle() {
        return title;
    }

    /**
     * @return Artist
     * */
    public String getArtist() {
        return artist;
    }

    /**
     * @return StageFile
     * */
    public String getStageFile() {
        return stageFile;
    }

    /**
     * @return Player (Single / Double)
     * */
    public int getPlayer() {
        return player;
    }

    /**
     * @return BPM
     * */
    public int getBpm() {
        return bpm;
    }

    /**
     * @return Play level (difficult)
     * */
    public int getPlayLevel() {
        return playLevel;
    }

    /**
     * @return Rank
     * */
    public int getRank() {
        return rank;
    }

    /**
     * @return Total
     * */
    public int getTotal() {
        return total;
    }

    /**
     * @return all wav list. you want to get once, use getWav().get(index).
     * */
    public Map<Integer, String> getWav() {
        return wav;
    }

    /**
     * @param key defined name (example: WAV00001)
     * */
    public String getWavFromKey(String key) {
        int index = Integer.parseInt(key.substring(3));
        return getWav().get(index);
    }

    public void parseHeaderField(List<String> headerField) {
        for (String line : headerField) {
            if (line.startsWith("#")) {
                String[] parts = line.substring(1).split(" ", 2);
                if (parts.length < 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "PLAYER":
                        player = Integer.parseInt(value);
                        break;
                    case "GENRE":
                        genre = value;
                        break;
                    case "TITLE":
                        title = value;
                        break;
                    case "ARTIST":
                        artist = value;
                        break;
                    case "BPM":
                        bpm = Integer.parseInt(value);
                        break;
                    case "PLAYLEVEL":
                        playLevel = Integer.parseInt(value);
                        break;
                    case "RANK":
                        rank = Integer.parseInt(value);
                        break;
                    case "TOTAL":
                        total = Integer.parseInt(value);
                        break;
                    case "STAGEFILE":
                        stageFile = value;
                        break;
                    default:
                        if (key.startsWith("WAV")) {
                            String wavKey = key.substring(3);
                            wav.put(Integer.valueOf(wavKey), value);
                        }
                        break;
                }
            }
        }
    }
}