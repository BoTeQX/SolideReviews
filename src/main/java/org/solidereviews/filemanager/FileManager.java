package org.solidereviews.filemanager;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static final String DIRECTORY_PATH = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    private static final String FILE_PATH = DIRECTORY_PATH + "data.txt";

    public FileManager() {
        System.out.println("FileManager constructor called");
        MakeDataFile();
    }

    public void MakeDataFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Data file created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Data file already exists.");
        }
    }
}
