package com.training.inputandoutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OptionalTask {
    private static final Logger LOGGER = Logger.getLogger(OptionalTask.class.getSimpleName());
    private static final String PATH_TO_GENERAL_DIRECTORY_WITH_RESOURCES = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    private static File firstOptionalTaskFolder = new File(PATH_TO_GENERAL_DIRECTORY_WITH_RESOURCES + "OptionalTask1");
    private static File secondOptionalTaskFolder = new File(PATH_TO_GENERAL_DIRECTORY_WITH_RESOURCES + "OptionalTask2");
    private static File thirdOptionalTaskFolder = new File(PATH_TO_GENERAL_DIRECTORY_WITH_RESOURCES + "OptionalTask3");
    private static String filePathForRandomNumbers = firstOptionalTaskFolder.getPath() + File.separator + "OptionalTask1.txt";

    public static void main(String[] args) {
        if (firstOptionalTaskFolder.mkdir() && secondOptionalTaskFolder.mkdir() && thirdOptionalTaskFolder.mkdir()) {
            writeDataToFile(getRandomNumbers(), filePathForRandomNumbers);
            writeDataToFile(getSortedNumbers(), filePathForRandomNumbers);
            writeDataToFile(getChangedLinesWithJustPrivateObjects(), secondOptionalTaskFolder.getPath() + File.separator + "OptionalTask2.java");
            writeDataToFile(getChangedLinesWithReverseOrderOfChars(), thirdOptionalTaskFolder.getPath() + File.separator + "OptionalTask3.java");
        }
    }

    private static List<String> getRandomNumbers() {
        return new Random()
                .ints(30, 0, 51)
                .boxed()
                .map(Objects::toString)
                .collect(Collectors.toList());
    }

    private static void writeDataToFile(List<String> lines, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String string : lines) {
                writer.write(string);
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.warning(String.valueOf(e));
        }
    }

    private static List<String> getSortedNumbers() {
        List<String> sortedNumbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathForRandomNumbers))) {
            sortedNumbers = reader.lines().mapToInt(Integer::parseInt).boxed().sorted(Integer::compareTo).map(Objects::toString).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            LOGGER.warning(String.valueOf(e));
        } catch (IOException e) {
            LOGGER.warning(String.valueOf(e));
        }
        return sortedNumbers;
    }

    private static List<String> getLinesFromJavaFile() {
        List<String> linesFromFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_GENERAL_DIRECTORY_WITH_RESOURCES + "OriginalDataForI&O.java"))) {
            reader.lines().forEach(linesFromFile::add);
        } catch (FileNotFoundException e) {
            LOGGER.warning(String.valueOf(e));
        } catch (IOException e) {
            LOGGER.warning(String.valueOf(e));
        }
        return linesFromFile;
    }

    private static List<String> getChangedLinesWithJustPrivateObjects() {
        List<String> changedLines;
        changedLines = getLinesFromJavaFile().stream().map(s -> s.replace("public", "private")).collect(Collectors.toList());
        return changedLines;
    }

    private static List<String> getChangedLinesWithReverseOrderOfChars() {
        List<String> changedLines;
        changedLines = getLinesFromJavaFile().stream().map(s -> new StringBuffer(s).reverse().toString()).collect(Collectors.toList());
        return changedLines;
    }
}
