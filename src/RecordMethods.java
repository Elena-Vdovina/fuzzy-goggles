import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class RecordMethods {

  // Читает список из файла в начале работы программы
  public static List<Record> readFile(String pathToFile) throws IOException, ParseException {
    List<Record> records = new ArrayList<>();
    try {
      List<String> lines = new ArrayList<>();
      BufferedReader fr = new BufferedReader(new FileReader(pathToFile));
      String line;
      while ((line = fr.readLine()) != null) {
        lines.add(line);
      }
      for (String s : lines) {
        List<String> columns = List.of(s.split(";", -1));
        String date = columns.get(0);
        String article = columns.get(1);
        int amount = Integer.parseInt(columns.get(2));
        String type = columns.get(3);
        String category = columns.get(4);
        Record record = new Record(date, article, amount, type, category);
        records.add(record);
      }
      fr.close();
    } catch (IOException e) {
      System.out.println("У Вас не обнаружен файл с бюджетом");
////      createNewList(pathToFile);
    }
    return records;
  }

    // Выводит список на экран
    public static void printBudget(String pathToFile) throws IOException, ParseException {
      System.out.println();
      System.out.println("Бюджет");
      List<Record> records = readFile(pathToFile);
      int i = 0;
      for (Record record : records) {
        System.out.printf(" %3d ", i + 1);
        System.out.println(record);
        ++i;
      }
    }


  }