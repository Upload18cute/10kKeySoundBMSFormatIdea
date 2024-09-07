package nbms.getData;

import java.util.List;
import java.util.Map;

public interface HeaderData {
    String getGenre();
    String getTitle();
    String getArtist();
    String getStageFile();
    int getPlayer();
    int getBpm();
    int getPlayLevel();
    int getRank();
    int getTotal();
    int getLntype();
    float getVolwav();
    Map<Integer, String> getWav();
    Map<Integer, Float> getStop();
    Map<Integer, Float> getScroll();
    Map<Integer, Float> getSpeed();
    Map<Integer, Float> getBpmNote();
    Map<String, String> getWhatIsThis();
    String getWavFromKey(String key);
    void parseHeaderField(List<String> headerField);
}