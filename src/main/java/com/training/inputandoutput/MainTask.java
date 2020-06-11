package com.training.inputandoutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class MainTask {
    private static final Logger LOGGER = Logger.getLogger(MainTask.class.getSimpleName());
    private static final String PATH_TO_GENERAL_DIRECTORY = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    private static File mainTaskData = new File(PATH_TO_GENERAL_DIRECTORY + File.separator + "MainTaskData.txt");
    private static String mainTaskDataPath = mainTaskData.getPath();
    private static final String ADDITIONAL_CHARS_FOR_DIRECTORY_LINES = "  |----";
    private static final String ADDITIONAL_CHARS_FOR_FILE_LINES = "  |    ";
    private static DecimalFormat formattedDouble = new DecimalFormat("#0");

    public static void main(String[] args) {
        File commandLineArgument = new File(args[0]);
        if (!commandLineArgument.exists()) {
            LOGGER.warning("Path is incorrect");
        }
        if (commandLineArgument.isDirectory()) {
            writeDataToFile(commandLineArgument.getName(), mainTaskDataPath);
            try (Stream<Path> filePathStream = Files.walk(Paths.get(commandLineArgument.getPath()))) {
                filePathStream
                        .map(Path::toFile)
                        .skip(1)
                        .forEach(file -> {
                            if (file.isDirectory()) {
                                writeDataToFile(ADDITIONAL_CHARS_FOR_DIRECTORY_LINES + file.getName(), mainTaskDataPath);
                            } else {
                                writeDataToFile(ADDITIONAL_CHARS_FOR_FILE_LINES + file.getName(), mainTaskDataPath);
                            }
                        });
            } catch (IOException e) {
                LOGGER.warning(String.valueOf(e));
            }
        } else {
            calcSomeFileInfo(getDataFromFile(commandLineArgument));
        }
    }

    private static void writeDataToFile(String line, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            LOGGER.warning(String.valueOf(e));
        }
    }

    private static List<String> getDataFromFile(File file) {
        List<String> dataFromFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(dataFromFile::add);
        } catch (IOException e) {
            LOGGER.warning(String.valueOf(e));
        }
        return dataFromFile;
    }

    private static void calcSomeFileInfo(List<String> dataFromFile) {
        int averageAmountOfFilesPerFolder = 0;
        int amountOfFolders = (int) dataFromFile.stream().filter(line -> !line.contains(ADDITIONAL_CHARS_FOR_FILE_LINES)).count();
        int amountOfFiles = (int) dataFromFile.stream().filter(line -> line.contains(ADDITIONAL_CHARS_FOR_FILE_LINES)).count();
        double averageFileNameLength = dataFromFile.stream()
                .mapToInt(s -> s.length() - ADDITIONAL_CHARS_FOR_FILE_LINES.length()).average().orElseThrow(NullPointerException::new);
        if (amountOfFiles != 0) {
            averageAmountOfFilesPerFolder = amountOfFiles / amountOfFolders;
        }
        String message = "This directory has : " + amountOfFolders + " folders, " + amountOfFiles + " files, " +
                "\n Average number of files in a folder = " + averageAmountOfFilesPerFolder +
                ".\n Average length of the file name = " + formattedDouble.format(averageFileNameLength) + " chars.";
        LOGGER.info(message);
    }
}
