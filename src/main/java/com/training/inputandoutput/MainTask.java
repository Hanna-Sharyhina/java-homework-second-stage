package com.training.inputandoutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class MainTask {
    private static final Logger LOGGER = Logger.getLogger(MainTask.class.getSimpleName());
    private static final String PATH_TO_GENERAL_DIRECTORY = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    private static File mainTaskData = new File(PATH_TO_GENERAL_DIRECTORY + File.separator + "MainTaskData");
    private static String mainTaskDataPath = mainTaskData.getPath();
    private static final String ADDITION_LINES_FOR_DIRECTORY_LINES = "  |----";
    private static final String ADDITION_LINES_FOR_FILE_LINES = "  |    ";

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
                                writeDataToFile(ADDITION_LINES_FOR_DIRECTORY_LINES + file.getName(), mainTaskDataPath);
                            } else {
                                writeDataToFile(ADDITION_LINES_FOR_FILE_LINES + file.getName(), mainTaskDataPath);
                            }
                        });
            } catch (IOException e) {
                LOGGER.warning(String.valueOf(e));
            }
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
}
