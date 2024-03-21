package org.solidereviews.utils;
import java.io.File;
import java.io.IOException;

public class FileManager {
    private static final String DIRECTORY_PATH = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    private static final String FILE_PATH = DIRECTORY_PATH + "data/data.txt";

    public FileManager() {
        MakeDataFile();
    }

    public void MakeDataFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
