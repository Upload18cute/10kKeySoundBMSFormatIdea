import nbms.NBMS;
import nbms.getData.HeaderData;
import nbms.getData.MainData;

import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException {
        NBMS nbms = new NBMS();
        HeaderData headerData = nbms.getHeaderData();
        MainData mainData = nbms.getMainData();

        nbms.loadFile("song.nbms");

        System.out.println();
        System.out.println("============================");
        System.out.println();
        System.out.println("-=-=- HEADER FIELD -=-=-");
        System.out.println();

        System.out.println("Player: " + headerData.getPlayer());
        System.out.println("Genre: " + headerData.getGenre());
        System.out.println("Title: " + headerData.getTitle());
        System.out.println("Artist: " + headerData.getArtist());
        System.out.println("BPM: " + headerData.getBpm());
        System.out.println("PlayLevel: " + headerData.getPlayLevel());
        System.out.println("Rank: " + headerData.getRank());
        System.out.println("Total: " + headerData.getTotal());
        System.out.println("StageFile: " + headerData.getStageFile());
        System.out.println();
        System.out.println("Wav List:" + headerData.getWav());
        System.out.println();
        for (int i = 1; i < headerData.getWav().size() + 1; i++) {
            System.out.println("Wav " + i + ": " + headerData.getWav().get(i));
        }

        System.out.println();
        System.out.println("-=-=- MAIN DATA FIELD -=-=-");
        System.out.println();

        for (int i = 0; i < mainData.getNoteCount(); i++) {
            System.out.println("Bar: " + mainData.getNote(i).getBar());
            System.out.println("Line: " + mainData.getNote(i).getLine());
            System.out.println("Split: " + mainData.getNote(i).getSplit());
            System.out.println("Index: " + mainData.getNote(i).getIndex());
            System.out.println("Type: " + mainData.getNote(i).getType());
            System.out.println("Wav: " + mainData.getNote(i).getWav());
            System.out.println("WavFile: " + headerData.getWavFromKey(mainData.getNote(i).getWav()));
            System.out.println();
        }

        System.out.println("============================");
    }
}
