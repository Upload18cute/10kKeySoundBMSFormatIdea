package nbms.getData;

import java.util.List;

public interface MainData {
    void parseMainDataField(List<String> mainDataField);
    int getNoteCount();
    NoteEntry getNote(int index);
}

