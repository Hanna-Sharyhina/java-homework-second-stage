import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class OriginalData {
    public static List<String> poem = new ArrayList<>();
    public static String choice;
    public static final Logger LOGGER = Logger.getLogger(OriginalData.class.getSimpleName());

    public static void main(String[] args){
        readLinesFromFile();
        outPutGenuinePoem();
        runSortingByChosenCriteria();
    }

    public static void readLinesFromFile(){
        try (BufferedReader reader
                     = new BufferedReader(new FileReader("src\\main\\resources\\AnnabelLeePoem.txt"))){
            reader.lines().forEach(poem::add);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void outPutGenuinePoem(){
        for (String s : poem) {
            System.out.println(s);
        }
        System.out.println();
    }

    public static void chooseSortingMethodByUserChoice(){
        System.out.println("Выберите способ сортировки, где: ");
        System.out.println("1 - сортировка по длине строк. ");
        System.out.println("2 - сортировка строк по алфавиту. ");
        System.out.println("3 - запись строк в обратном порядке в другой файл. ");
        Scanner scanner = new Scanner(System.in);
        choice = scanner.next().trim();
    }

    public static void runSortingByChosenCriteria(){
        chooseSortingMethodByUserChoice();
        switch (choice) {
            case "1":
                sortPoemByLineLength();
                outPutSortedPoem();
                break;
            case "2":
                sortPoemByAlphabetOrder();
                outPutSortedPoem();
                break;
            case "3":
                writeReverseOrderOfPoemInDifferentFile();
                break;
            default:
                LOGGER.warning("Ввод осуществлен неверно. Попробуйте повторить. ");
                runSortingByChosenCriteria();
        }
    }

    public static void sortPoemByLineLength(){
        poem.sort((o1, o2) -> o1.length() - o2.length());
    }

    public static void sortPoemByAlphabetOrder(){
        Collections.sort(poem);
    }

    public static void writeReverseOrderOfPoemInDifferentFile(){
        try(BufferedWriter writer =
                    new BufferedWriter(new FileWriter("src\\src\\main\\resources\\AnnabelLeePoemWithReverseOrder.txt"))){
            for (int i = poem.size()-1; i>=0; i--){
                writer.write(poem.get(i) + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void outPutSortedPoem(){
        for (String s : poem) {
            System.out.println(s);
        }
    }
}


