import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class RecordMethods {
  public static List<Record> records;

/*  Этот метод в FileMetodth
  // Читает список из файла в начале работы программы
  public static List<Record> readFile(String pathToFile) throws IOException, ParseException { }
*/
    // Выводит список на экран
   public static void printRecord(/*String pathToFile*/) throws IOException, ParseException {
      System.out.println();
      System.out.println("Бюджет");
      System.out.println("2!"+records);
      //List<Record> records = FileMethods.readFile(MenuMethods.pathToFile_);
      int i = 0;
      for (Record record : RecordMethods.records) {
        System.out.println( i + 1+" "+ record.toString());
        ++i;
      }
    }

  public static void addRecord() throws IOException, ParseException {
    //List<> events = readFile(pathToFile);
    // прочитали
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Новая запись:");
    System.out.print("До какого числа (\"дд.мм.гггг\") - ");
    String dateStr = dateValidation(br); // проверка формата ввода
    System.out.print("Содержание ");
    String article = br.readLine();
    while (article.isEmpty()) { // проверка на пустоту для названия
      System.out.println(Colors.RED + "Содержание не может быть пустым:" + Colors.RESET);
      article = br.readLine();
    }
    System.out.print("Сумма ");
    double amount=Double.parseDouble(br.readLine());
    while (amount<=0) { // должна быть больше 0
      System.out.println(Colors.RED + "Сумма должна быть >0: " + Colors.RESET);
      article = br.readLine();
    }
    System.out.println("Доход/расход");
    //System.out.print("     1     /  2  /    3   /    4     ");
    String type= br.readLine();
    //int prioritet = priorityValidation(br);
    System.out.print("Категория: ");
    String category= br.readLine();
    //int status = checkValidation(br); // проверка ввода
    // добавили
    Record record = new Record(dateStr,article,amount,type,category);
    records.add(record);
    // записали в файл
    FileMethods.writeFile();
    printRecord();
  }


  public static String dateValidation(BufferedReader br) {
    String dateStr = "";
    boolean tr = false; // флаг для проверки условий
    while (!tr) {
      try {
        dateStr = br.readLine();
        if (dateStr.isEmpty()) { // сообщение, если пустая строка
          System.out.print(Colors.RED + "Дата не может быть пустой. " + Colors.RESET);
        }
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String dateTest = String.valueOf(formatter.parse(dateStr));
        tr = true;
      } catch (ParseException e) { //ошибка, если некорректный формат
        System.out.print(Colors.RED + "Неправильный формат ввода! Попробуйте еще раз: " + Colors.RESET);
        tr = false;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return dateStr;
  }

  }
