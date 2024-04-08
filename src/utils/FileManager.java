package org.solidereviews.utils;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String DIRECTORY_PATH = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    private static final String DATA_DIRECTORY_PATH = DIRECTORY_PATH + "data/";
    private static final String DATA_FILE_PATH = DATA_DIRECTORY_PATH + "data.txt";
    private static final String GAMES_FILE_PATH = DATA_DIRECTORY_PATH + "games.txt";

    public FileManager() {
        makeDataDirectory();
        makeDataFile(DATA_FILE_PATH);
        makeDataFile(GAMES_FILE_PATH);
    }

    private void makeDataDirectory() {
        File directory = new File(DATA_DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void makeDataFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeDataFile(String data, String filePath) {
        writeFile(data, filePath);
    }

    public void writeGameToFile(String gameInfo) {
        writeFile(gameInfo, GAMES_FILE_PATH);
    }

    private void writeFile(String gameInfo, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(gameInfo);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> readAllLines(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public ArrayList<String> readGamesFile() {
        return readAllLines(GAMES_FILE_PATH);
    }

    public static String getGamesFilePath() {
        return GAMES_FILE_PATH;
    }

    public void deleteGamesFile() {
        File file = new File(GAMES_FILE_PATH);
        if (file.exists()) {
            file.delete();
            makeDataFile(GAMES_FILE_PATH); // recreate the file
        }
    }
}

