package org.solidereviews.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {

    private static final String DIRECTORY_PATH = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath(); //Location of the code
    private static final String FILE_PATH1 = DIRECTORY_PATH + "data/data.txt";
    private static final String FILE_PATH2 = DIRECTORY_PATH + "data/games.txt";//for now the file is located in build

    private final String dataFilePath;
    private final String gamesFilePath;


    public FileManager() {
        makeDataFile();
        makeGamesFile();
        this.dataFilePath = FILE_PATH1;
        this.gamesFilePath = FILE_PATH2;
    }


    private void makeDataFile() {
        File file = new File(FILE_PATH1);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } // Makes a data file if it doesn't exist

    private void makeGamesFile() {
        File file = new File(FILE_PATH2);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } // Makes a data file if it doesn't exist

    public void writeDataFile(List<String> data) {
        writeFile(data, dataFilePath);
    } //file to write to data

    public void writeGamesFile(List<String> games) {
        writeFile(games, gamesFilePath);
    } //file to write to games

    private void writeFile(List<String> lines, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

