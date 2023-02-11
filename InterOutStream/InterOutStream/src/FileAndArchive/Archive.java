package FileAndArchive;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Archive {
    public static void zipFiles(String path, String[] namesFile) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path))) {
            for (String name : namesFile) {
                FileInputStream fis = new FileInputStream(name);
                String[] nameFile = name.split("/");
                ZipEntry entry = new ZipEntry(nameFile[nameFile.length - 1]);
                zos.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openZip(String zipPath, String unpackingPath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(unpackingPath + name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
