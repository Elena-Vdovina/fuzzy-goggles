import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class RecordMethods {

  public static List<Record> records;

  /**
   * Выводит список на экран.
   */
  public static void printRecord() {
    System.out.println();
    System.out.println("Бюджет");
    int i = 0;
    for (Record record : RecordMethods.records) {
      System.out.println(i + 1 + " " + record.toString());
      ++i;
    }
  }

  /**
   * Добавляет запись в конец списка, записывает в файл, выводит итоговый список.
   */
  public static void addRecord() throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Новая запись:");
    System.out.print("Дата (\"ДД.ММ.ГГГГ\") - ");
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
    System.out.print("Доход/расход: ");
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

  /**
   * Проверяет корректность формата введенной даты. Шаблон: "dd.MM.yyyy"
   *
   * @return возвращает строку
   */
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

  /**
   * Удаляет запись из списка, записывает итоговый список в файл, выводит итоговый список.
   */
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

  /**
   * Проверяет корректность введенного ответа на вопрос. Варианты ответов: да/нет/1/0.
   *
   * @return возвращает число 1/0
   */
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

  /**
   * Выводит список расходов/доходов c сортировкой по дате и категориям, и итоговую сумму.
   *
   * @param type тип записи(расхды/доходы) для отбора из списка
   */
  public static void printTypeList(String type) {
    List<Record> records = RecordMethods.records;
    List<Record> selected = new ArrayList<>();
    System.out.println();
    for (Record record : records) {
      if ((record.getType().equals(type))) {
        selected.add(record);
      }
    }
    selected.sort(new RecordDateCategoryAmountComparator());
    for (Record record : selected) {
      System.out.println(record);
    }
    String text;
    if (type.equals("expenses")) {
      text = "Сумма расходов за текущий месяц: ";
    } else {
      text = "Сумма доходов за текущий месяц: ";
    }
    System.out.println(text + SumAmount(selected));
  }

  /**
   * Считает сумму денег в переданном списке, переводит результат в десятичную дробь с округлением
   * до сотых долей.
   *
   * @param records список
   * @return строка с суммой
   */
  public static String SumAmount(List<Record> records) {
    int result = 0;
    for (Record record : records) {
      result += record.getAmount();
    }
    return Double.toString((double) result / 100);
  }


}
