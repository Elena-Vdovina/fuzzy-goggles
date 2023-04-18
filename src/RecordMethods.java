import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class RecordMethods {

  public static List<Record> records;

  // Выводит список на экран
  public static void printRecord() {
    System.out.println();
    System.out.println("Бюджет");
    int i = 0;
    for (Record record : RecordMethods.records) {
      System.out.println(i + 1 + " " + record.toString());
      ++i;
    }
  }

  public static void addRecord() throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Новая запись:");
    System.out.print("Дата (\"дд.мм.гггг\") - ");
    String dateStr = dateValidation(br); // проверка формата ввода
    System.out.print("Содержание ");
    String article = br.readLine();
    while (article.isEmpty()) { // проверка на пустоту для названия
      System.out.println(Colors.RED + "Содержание не может быть пустым:" + Colors.RESET);
      article = br.readLine();
    }
    System.out.print("Сумма ");
    double amountD = Double.parseDouble(br.readLine());
    while (amountD <= 0) { // должна быть больше 0
      System.out.println(Colors.RED + "Сумма должна быть >0: " + Colors.RESET);
      amountD = Double.parseDouble(br.readLine());
    }
    int amount = (int) amountD * 100;
    System.out.println("Доход/расход");
    String type = br.readLine();
    System.out.print("Категория: ");
    String category = br.readLine();
    // добавили
    Record record = new Record(dateStr, article, amount, type, category);
    records.add(record);
    // записали в файл
    FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
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
        System.out.print(
            Colors.RED + "Неправильный формат ввода! Попробуйте еще раз: " + Colors.RESET);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return dateStr;
  }

  public static void removeRecord() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    printRecord();
    List<Record> records = RecordMethods.records;
    System.out.print("Введите номер записи, которую хотите удалить ");
    int n = Integer.parseInt(br.readLine());
    System.out.print("Вы уверены? (1/0) - ");
    int answer = yesNoValidation(br); // проверка ввода
    // записали
    if (answer == 1) {
      records.remove(n - 1);
      FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
    }
    printRecord();
  }

  public static int yesNoValidation(BufferedReader br) throws IOException {
    String answer = br.readLine().toUpperCase();
    while (!(answer.equals("0") || answer.equals("1") || answer.equals("ДА") ||
        answer.equals("НЕТ"))) {
      // проверка на соответствующее значение
      System.out.print(Colors.RED + "Некорректное значение. Попробуйте еще раз: " + Colors.RESET);
      answer = br.readLine().toUpperCase();
    }
    switch (answer) {
      case "0", "НЕТ" -> answer = "0"; // не выполнено
      case "1", "ДА" -> answer = "1"; // выполнено
    }
    return Integer.parseInt(answer);
  }


}
