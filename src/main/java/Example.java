import nbms.NBMS;
import nbms.getData.HeaderData;
import nbms.getData.MainData;

import java.io.IOException;
import java.util.Scanner;

public class Example {
    public static void main(String[] args) throws IOException {
        doLoad("song.nbms");
    }

    public static void doLoad(String fileName) throws IOException {
        if (!fileName.endsWith(".nbms")) {
            System.out.println("Please enter only .nbms files.");
            return;
        }
        NBMS nbms = new NBMS();
        HeaderData headerData = nbms.getHeaderData();
        MainData mainData = nbms.getMainData();

        nbms.loadFile(fileName);

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
        System.out.println("Volwav: " + headerData.getVolwav());
        System.out.println("StageFile: " + headerData.getStageFile());
        System.out.println();
        System.out.println("LNType: " + headerData.getLntype());
        System.out.println();
        System.out.println("Wav List:" + headerData.getWav());
        System.out.println();
        for (int i = 1; i < headerData.getWav().size() + 1; i++) {
            System.out.println("Wav " + i + ": " + headerData.getWav().get(i));
        }
        System.out.println();

        for (int i = 1; i < headerData.getSpeed().size() + 1; i++) {
            System.out.println("Speed " + i + ": " + headerData.getSpeed().get(i));
        }
        System.out.println();

        for (int i = 1; i < headerData.getScroll().size() + 1; i++) {
            System.out.println("Scroll " + i + ": " + headerData.getScroll().get(i));
        }
        System.out.println();

        for (int i = 1; i < headerData.getStop().size() + 1; i++) {
            System.out.println("Stop " + i + ": " + headerData.getStop().get(i));
        }
        System.out.println();

        for (int i = 1; i < headerData.getBpmNote().size() + 1; i++) {
            System.out.println("BpmNote " + i + ": " + headerData.getBpmNote().get(i));
        }
        System.out.println();

        System.out.println("What is this: " + headerData.getWhatIsThis());

        System.out.println();
        System.out.println("-=-=- MAIN DATA FIELD -=-=-");
        System.out.println();

        for (int i = 0; i < mainData.getNoteCount(); i++) {
            if (mainData.getNote(i).getLine().equals("02")) {
                System.out.println("-=- Time Signature note -=-");
                System.out.println("Bar: " + mainData.getNote(i).getBar());
                System.out.println("Time Signature: " + 4 * (Float.parseFloat(mainData.getNote(i).getWav())) + " / 4");
                System.out.println();
                continue;
            } else if (mainData.getNote(i).getLine().equals("SC")) {
                System.out.println("-=- Scroll note -=-");
            } else if (mainData.getNote(i).getLine().equals("SP")) {
                System.out.println("-=- Speed note -=-");
            } else if (mainData.getNote(i).getLine().equals("08")) {
                System.out.println("-=- BPM note -=-");
            } else if (mainData.getNote(i).getLine().equals("09")) {
                System.out.println("-=- Stop note -=-");
            }

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
