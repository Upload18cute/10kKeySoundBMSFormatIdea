package nbms.getData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderDataImpl implements HeaderData {
    private String genre;
    private String title;
    private String artist;
    private String stageFile;

    private int player;
    private int bpm;
    private int playLevel;
    private int rank;
    private int total;
    private int lntype;

    private float volwav;

    private final Map<Integer, String> wav = new HashMap<>();
    private final Map<Integer, Float> stop = new HashMap<>();
    private final Map<Integer, Float> scroll = new HashMap<>();
    private final Map<Integer, Float> speed = new HashMap<>();
    private final Map<Integer, Float> bpmNote = new HashMap<>();
    private final Map<String, String> whatIsThis = new HashMap<>();

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getStageFile() {
        return stageFile;
    }

    @Override
    public int getPlayer() {
        return player;
    }

    @Override
    public int getBpm() {
        return bpm;
    }

    @Override
    public int getPlayLevel() {
        return playLevel;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public int getLntype() {
        return lntype;
    }

    @Override
    public float getVolwav() {
        return volwav;
    }

    @Override
    public Map<Integer, String> getWav() {
        return wav;
    }

    @Override
    public Map<Integer, Float> getStop() {
        return stop;
    }

    @Override
    public Map<Integer, Float> getScroll() {
        return scroll;
    }

    @Override
    public Map<Integer, Float> getSpeed() {
        return speed;
    }

    @Override
    public Map<Integer, Float> getBpmNote() {
        return bpmNote;
    }

    @Override
    public Map<String, String> getWhatIsThis() {
        return whatIsThis;
    }

    @Override
    public String getWavFromKey(String key) {
        int index = Integer.parseInt(key.substring(3));
        return getWav().get(index);
    }

    @Override
    public void parseHeaderField(List<String> headerField) {
        for (String line : headerField) {
            if (line.startsWith("#")) {
                String[] parts = line.substring(1).split(" ", 2);
                if (parts.length < 2) {
                    whatIsThis.put(parts[0], "no value");
                    continue;
                }

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
                    case "LNTYPE":
                        lntype = Integer.parseInt(value);
                        break;
                    case "VOLWAV":
                        volwav = Float.parseFloat(value);
                        break;
                    default:
                        if (key.startsWith("WAV")) {
                            String wavKey = key.substring(3);
                            wav.put(Integer.valueOf(wavKey), value);
                        } else if (key.startsWith("STOP")) {
                            String wavKey = key.substring(4);
                            stop.put(Integer.valueOf(wavKey), Float.valueOf(value));
                        } else if (key.startsWith("SCROLL")) {
                            String wavKey = key.substring(6);
                            scroll.put(Integer.valueOf(wavKey), Float.valueOf(value));
                        } else if (key.startsWith("SPEED")) {
                            String wavKey = key.substring(5);
                            speed.put(Integer.valueOf(wavKey), Float.valueOf(value));
                        } else if (key.startsWith("BPM")) {
                            String wavKey = key.substring(3);
                            bpmNote.put(Integer.valueOf(wavKey), Float.valueOf(value));
                        } else {
                            whatIsThis.put(key, value);
                        }
                        break;
                }
            }
        }
    }
}