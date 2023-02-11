
import Game.GameProgress;

import static FileAndArchive.Archive.openZip;
import static FileAndArchive.FileAndDirectory.*;
import static Game.GameProgress.openProgress;
import static Logging.Logging.printLog;
import static FileAndArchive.Archive.zipFiles;
import static Game.GameProgress.saveGame;

public class Main {
    public static void main(String[] args) {

        //Task 1

        String mainPathName = "D:/Games";
        StringBuilder log = new StringBuilder();

        createDirectory(mainPathName, "src", log);
        createDirectory(mainPathName, "res", log);
        createDirectory(mainPathName, "savegames", log);
        createDirectory(mainPathName, "temp", log);

        createDirectory(mainPathName + "/src", "main", log);
        createDirectory(mainPathName + "/src", "test", log);

        createFile(mainPathName + "/src/main", "Main.java", log);
        createFile(mainPathName + "/src/main", "Utils.java", log);

        createDirectory(mainPathName + "/res", "drawables", log);
        createDirectory(mainPathName + "/res", "vectors", log);
        createDirectory(mainPathName + "/res", "icons", log);

        createFile(mainPathName + "/temp", "temp.txt", log);

        printLog(mainPathName + "/temp/temp.txt", false, log);

        //Task 2

        GameProgress gameProgressPlayer1 = new GameProgress(1, 5, 8, 594.51);
        saveGame(mainPathName + "/savegames/save1.dat", gameProgressPlayer1);
        GameProgress gameProgressPlayer2 = new GameProgress(5, 6, 8, 594.51);
        saveGame(mainPathName + "/savegames/save2.dat", gameProgressPlayer2);
        GameProgress gameProgressPlayer3 = new GameProgress(3, 8, 8, 594.51);
        saveGame(mainPathName + "/savegames/save3.dat", gameProgressPlayer3);

        zipFiles(mainPathName + "/savegames/zipSave.zip", new String[]{
                mainPathName + "/savegames/save1.dat",
                mainPathName + "/savegames/save2.dat",
                mainPathName + "/savegames/save3.dat"
        });

        deleteFileOtherThanArchives(mainPathName + "/savegames/");

        //Task 3

        openZip(mainPathName + "/savegames/zipSave.zip", mainPathName + "/savegames/");

        GameProgress loadGameProgress1 = openProgress(mainPathName + "/savegames/save1.dat");
        GameProgress loadGameProgress2 = openProgress(mainPathName + "/savegames/save2.dat");
        GameProgress loadGameProgress3 = openProgress(mainPathName + "/savegames/save3.dat");

        System.out.println(loadGameProgress1);
        System.out.println(loadGameProgress2);
        System.out.println(loadGameProgress3);
    }
}