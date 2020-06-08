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
    private static File mainTaskData = new File(PATH_TO_GENERAL_DIRECTORY + File.separator + "MainTaskData");
    private static String mainTaskDataPath = mainTaskData.getPath();
    private static final String ADDITION_LINES_FOR_DIRECTORY_LINES = "  |----";
    private static final String ADDITION_LINES_FOR_FILE_LINES = "  |    ";
    private static DecimalFormat formattedDouble = new DecimalFormat("#0");

    public static void main(String[] args) {
        File commandLineArgumentWithDirectoryPath = new File(args[0]);
        File commandLineArgumentWithDataFilePath = new File(args[1]);
        if (commandLineArgumentWithDirectoryPath.exists()) {
            try (Stream<Path> filePathStream = Files.walk(Paths.get(commandLineArgumentWithDirectoryPath.getPath()))) {
                writeDataToFile(commandLineArgumentWithDirectoryPath.getName(), mainTaskDataPath);
                filePathStream
                        .map(Path::toFile)
                        .skip(1)
                        .forEach(file -> {
                            if (file.isDirectory()) {
                                writeDataToFile(ADDITION_LINES_FOR_DIRECTORY_LINES + file.getName(), mainTaskDataPath);
                            } else {
                                writeDataToFile(ADDITION_LINES_FOR_FILE_LINES + file.getName(), mainTaskDataPath);
                            }
                        });
            } catch (IOException e) {
                LOGGER.warning(String.valueOf(e));
            }
        } else {
            LOGGER.warning("Path is incorrect");
        }
        if (commandLineArgumentWithDirectoryPath.exists()) {
            calcSomeFileInfo(getDataFromFile(commandLineArgumentWithDataFilePath));
        } else {
            LOGGER.warning("File isn't exist");
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
        } catch (FileNotFoundException e) {
            LOGGER.warning(String.valueOf(e));
        } catch (IOException e) {
            LOGGER.warning(String.valueOf(e));
        }
        return dataFromFile;
    }

    private static void calcSomeFileInfo(List<String> dataFromFile) {
        int averageAmountOfFilesPerFolder = 0;
        int amountOfFolders = (int) dataFromFile.stream().filter(line -> !line.contains(ADDITION_LINES_FOR_FILE_LINES)).count();
        int amountOfFiles = (int) dataFromFile.stream().filter(line -> line.contains(ADDITION_LINES_FOR_FILE_LINES)).count();
        double averageFileNameLength = dataFromFile.stream()
                .mapToInt(s -> s.length() - ADDITION_LINES_FOR_FILE_LINES.length()).average().orElseThrow(NullPointerException::new);
        if (amountOfFiles != 0) {
            averageAmountOfFilesPerFolder = amountOfFiles / amountOfFolders;
        }
        String message = "В данной директории находится : " + amountOfFolders + " папок, " + amountOfFiles + " файлов, " +
                "\n Среднее количество файлов в папке = " + averageAmountOfFilesPerFolder +
                ".\n Средняя длина названия файла = " + formattedDouble.format(averageFileNameLength) + " символ(а/ов).";
        LOGGER.info(message);
    }
}
