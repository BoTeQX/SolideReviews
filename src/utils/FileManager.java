package utils;

import surveys.QuestionAndAnswers;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String DIRECTORY_PATH = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    private static final String FILE_DIRECTORY_PATH = DIRECTORY_PATH + "data/";
    private static final String GAMES_FILE_PATH = FILE_DIRECTORY_PATH + "games.txt";
    private static final String REVIEWS_FILE_PATH = FILE_DIRECTORY_PATH + "reviews.txt";
    private static final String SURVEYS_FILE_PATH = FILE_DIRECTORY_PATH + "surveys.txt";

    public FileManager() {
        makeDataDirectory();
        makeDataFile(GAMES_FILE_PATH);
        makeDataFile(REVIEWS_FILE_PATH);
        makeDataFile(SURVEYS_FILE_PATH);
    }

    private void makeDataDirectory() {
        File directory = new File(FILE_DIRECTORY_PATH);
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

    public void writeGameToFile(String gameInfo) {

        writeFile(gameInfo, GAMES_FILE_PATH);
    }

    public void writeReviewToFile(String reviewInfo) {

        writeFile(reviewInfo, REVIEWS_FILE_PATH);
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

    public ArrayList<String> readReviewFile() {
        return readAllLines(REVIEWS_FILE_PATH);
    }

    public ArrayList<String> readSurveysFile() {
        return readAllLines(SURVEYS_FILE_PATH);
    }

    public static String getGamesFilePath() {
        return GAMES_FILE_PATH;
    }

    public void deleteFile(String filepath) {
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
            makeDataFile(filepath); // recreate the file
        }
    }

    public void deleteSurvey() {
        deleteFile(SURVEYS_FILE_PATH);
    }

    public void deleteGame() {
        deleteFile(GAMES_FILE_PATH);
    }

    public void writeSurveyToFile(String surveyData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SURVEYS_FILE_PATH, true))) {
            writer.write(surveyData);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<QuestionAndAnswers> readSurveysFromFile(ArrayList<String> gameNames) {
        ArrayList<QuestionAndAnswers> surveys = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SURVEYS_FILE_PATH))) {
            String line;
            String gameName = null;
            String question = null;
            Boolean multipleChoice = null;
            ArrayList<String> answers = new ArrayList<>();
            ArrayList<String> choices = new ArrayList<>();
            boolean isSurveyStarted = false;
            boolean isChoicesSection = false;
            boolean isAnswerSection = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Game Name:")) {
                    gameName = line.substring("Game Name: ".length()).trim();
                    if (gameNames.contains(gameName)) {
                        isSurveyStarted = true; // Start a new survey for this game
                    } else {
                        // Skip this survey and move to the next one
                        skipSurvey(reader);
                        isSurveyStarted = false;
                    }
                } else if (isSurveyStarted && line.startsWith("Question:")) {
                    question = line.substring("Question: ".length()).trim();
                } else if (isSurveyStarted && line.startsWith("Multiple Choice:")) {
                    multipleChoice = Boolean.valueOf(line.substring("Multiple Choice: ".length()).trim());
                } else if (isSurveyStarted && line.equals("### Choices ###")) {
                    isChoicesSection = true;
                } else if (isSurveyStarted && line.equals("### Answers ###")) {
                    isChoicesSection = false;
                    isAnswerSection = true;
                } else if (isSurveyStarted && isChoicesSection && line.startsWith("- ")) {
                    choices.add(line.substring(2).trim());
                } else if (isSurveyStarted && isAnswerSection && line.equals("### End of Answers ###")) {
                    // For multiple choice surveys, the end of answers section also signals the end of the survey
                    QuestionAndAnswers qna = new QuestionAndAnswers(question, multipleChoice);
                    qna.getAnswers().addAll(answers);
                    qna.getChoices().addAll(choices);
                    surveys.add(qna);
                    // Clear data for the next survey
                    question = null;
                    multipleChoice = null;
                    answers.clear();
                    choices.clear();
                    isSurveyStarted = false;
                    isChoicesSection = false;
                    isAnswerSection = false;
                } else if (isSurveyStarted && isAnswerSection && !line.isEmpty()) {
                    answers.add(line.trim());
                } else if (line.isEmpty()) {
                    // End of survey
                    isSurveyStarted = false; // End the current survey
                    choices.clear(); // Clear choices for the next survey
                    answers.clear(); // Clear answers for the next survey
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return surveys;
    }

    // Helper method to skip lines until an empty line is encountered
    private static void skipSurvey(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            // Skip lines until an empty line is encountered
        }
    }

}