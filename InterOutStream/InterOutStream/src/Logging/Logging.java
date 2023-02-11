package Logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logging {
    public static void printLog(String pathName, boolean append, StringBuilder log) {
        try (FileWriter fileWriter = new FileWriter(pathName, append)) {
            fileWriter.write(log.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
