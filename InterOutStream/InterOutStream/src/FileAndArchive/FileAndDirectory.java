package FileAndArchive;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileAndDirectory {
    public static void createDirectory(String pathName, String name, StringBuilder log) {
        File dir = new File(pathName, name);
        if (dir.mkdir()) {
            log.append(new Date())
                    .append(" Папка ")
                    .append(pathName)
                    .append("\\")
                    .append(name)
                    .append(" успешно создана!")
                    .append("\n");
        } else {
            log.append(new Date()).append(" Папка ").append(pathName)
                    .append("\\")
                    .append(name)
                    .append(" не создана!")
                    .append("\n");
        }
    }

    public static void createFile(String pathName, String name, StringBuilder log) {
        File file = new File(pathName, name);
        try {
            if (file.createNewFile()) {
                log.append(new Date())
                        .append(" Файл ")
                        .append(pathName)
                        .append("\\")
                        .append(name)
                        .append(" успешно создан!")
                        .append("\n");
            } else {
                log.append(new Date())
                        .append(" Файл ")
                        .append(pathName)
                        .append("\\")
                        .append(name)
                        .append(" не создан!")
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFileOtherThanArchives(String pathDir) {
        File dir = new File(pathDir);
        for (File file : dir.listFiles()) {
            if (!file.getName().contains(".zip")) {
                if (file.delete()) {
                    System.out.println("Файл " + file.getName() + " успешно удален!");
                } else {
                    System.out.println("Файл " + file.getName() + " не удален!");
                }
            }
        }
    }
}

