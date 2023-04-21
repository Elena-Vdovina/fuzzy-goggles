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
      System.out.printf("%4d   %s\n", (i + 1), record.toString());
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
    System.out.print("Доход/расход: (1/0): ");
    int typeN = Integer.parseInt(br.readLine());
    while ((typeN < 0) || typeN > 1) {
      System.out.println(Colors.RED + "Доход - 1, расход - 0" + Colors.RESET);
      typeN = Integer.parseInt(br.readLine());
    }
    String type;
    String category;
    if (typeN == 1) {
      type = "Доход";
      category = categoryGuide(Guide.income, br);
    } else {
      type = "Расход";
      category = categoryGuide(Guide.expenses, br);
    }
    System.out.print("Сумма ");
    double amountD = Double.parseDouble(br.readLine());
    while (amountD <= 0) { // должна быть больше 0
      System.out.println(Colors.RED + "Сумма должна быть >0: " + Colors.RESET);
      amountD = Double.parseDouble(br.readLine());
    }
    int amount = (int) amountD * 100;
    System.out.print("Содержание ");
    String article = br.readLine();
    while (article.isEmpty()) { // проверка на пустоту для названия
      System.out.println(Colors.RED + "Содержание не может быть пустым:" + Colors.RESET);
      article = br.readLine();
    }
    Record record = new Record(dateStr, article, amount, type, category);
    records.add(record);
    FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
    printRecord();
  }

  /**
   * Добавляет запись в конец списка, записывает в файл, выводит итоговый список.
   */
  public static void changeRecord() throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Введите номер записи для редактирования: ");
    int n = Integer.parseInt(br.readLine());
    Record record = RecordMethods.records.get(n - 1);
    System.out.println("Дата " + record.getDateStr());
    System.out.print("Дата (\"ДД.ММ.ГГГГ\") - ");
    String dateStr = dateValidation(br);
    System.out.println("Доход/расход: " + record.getType());
    System.out.print("Доход/расход: (1/0): ");
    int typeN = Integer.parseInt(br.readLine());
    while ((typeN < 0) || typeN > 1) {
      System.out.println(Colors.RED + "Доход - 1, расход - 0" + Colors.RESET);
      typeN = Integer.parseInt(br.readLine());
    }
    String type;
    String category;
    if (typeN == 1) {
      type = "Доход";
      System.out.println("Категория: " + record.getCategory());
      category = categoryGuide(Guide.income, br);
    } else {
      type = "Расход";
      category = categoryGuide(Guide.expenses, br);
    }
    System.out.println("Сумма: " + (double) record.getAmount() / 100);
    System.out.print("Сумма ");
    double amountD = Double.parseDouble(br.readLine());
    while (amountD <= 0) { // должна быть больше 0
      System.out.println(Colors.RED + "Сумма должна быть >0: " + Colors.RESET);
      amountD = Double.parseDouble(br.readLine());
    }
    int amount = (int) amountD * 100;
    System.out.println("Содержание: " + record.getArticle());
    System.out.print("Содержание ");
    String article = br.readLine();
    while (article.isEmpty()) { // проверка на пустоту для названия
      System.out.println(Colors.RED + "Содержание не может быть пустым:" + Colors.RESET);
      article = br.readLine();
    }
    record = new Record(dateStr, article, amount, type, category);
    records.set(n - 1, record);
    FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
    printRecord();
  }


  /**
   * Метод выбора категории дохода/расхода
   *
   * @param guide справочник дохода/расхода
   * @param br    BufferedReader
   * @return String категорию дохода/расхода
   * @throws IOException цикл while заменяет условие - стражника
   */
  public static String categoryGuide(List<String> guide, BufferedReader br) throws IOException {
    Guide.printGuide(guide);
    System.out.print("Выберите номер категории: ");
    int categoryN = Integer.parseInt(br.readLine());
    while ((categoryN < 1) || (categoryN > guide.size())) {
      System.out.println(
          Colors.RED + "Номер категории должен быть в диапазоне от 1 до " + guide.size()
              + ": " + Colors.RESET);
      categoryN = Integer.parseInt(br.readLine());
    }
    return guide.get(categoryN - 1);
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
   * Выводит список расходов/доходов с сортировкой по дате и категориям, и итоговую сумму. Отбирает
   * список расходов/доходов за месяц.
   *
   * @param currentMonth при вызове из меню текущий месяц, при вызове из метода выбранный месяц
   * @param type         тип записи(расходы/доходы) для отбора из списка
   * @return selected    отобранных записей
   */
  public static List<Record> doTypeList(int currentMonth, String type) {
    List<Record> records = RecordMethods.records;
    List<Record> selected = new ArrayList<>();
    boolean y = false; // флаг для определения пустого списка
    for (Record record : records) {
      if (record.getType().equals(type) &&
          (record.getMonth() == currentMonth) &&
          (record.getYear() == DateMethods.checkCurrentYear())) {
        selected.add(record);
        y = true;
      }
    }
    if (!y) {
      System.out.println("Список на этот месяц пуст");
    }
    return selected;
  }

  /**
   * Выводит список расходов/доходов с сортировкой по дате и категориям, и итоговую сумму.
   *
   * @param selected     отобранных записей
   * @param currentMonth при вызове из меню текущий месяц, при вызове из метода выбранный месяц
   * @param category     выбранная категория
   * @throws IOException не обрабатывается
   */
  public static void printTypeList(List<Record> selected, int currentMonth, String category)
      throws IOException {
    selected.sort(new RecordDateCategoryAmountComparator());
    System.out.println(selected.get(0));
    Record record = selected.get(0);
    String type = record.getType();
    System.out.println();
    System.out.println();
    if (type.equals("Расход")) {
      System.out.println("=== Расходы за месяц: ===");
    } else {
      System.out.println("=== Доходы за месяц: ===");
    }
    System.out.println("_________________________________________________________________________");
    for (Record r : selected) {
      System.out.println(r);
    }
    System.out.println("_________________________________________________________________________");
    System.out.println("Итого: " + SumAmountToString(SumAmount(selected)) + " EUR");
    int month = MenuMethods.horizontalMenu(currentMonth + 1) - 1;
    if (month != 12 && month <= DateMethods.checkCurrentMonth()) {
      RecordMethods.printTypeList(RecordMethods.doTypeList(month, category), month, category);
    }
  }

  /**
   * Отбирает список по категории за месяц.
   *
   * @param currentMonth при вызове из меню текущий месяц, при вызове из метода выбранный месяц
   * @return список по категории
   * @throws IOException обрабатывается неправильный ввод
   */
  public static List<Record> doCategoryList(int currentMonth) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Доход/расход: (1/0): ");
    int typeN = Integer.parseInt(br.readLine());
    while ((typeN < 0) || typeN > 1) {
      System.out.println(Colors.RED + "Доход - 1, расход - 0" + Colors.RESET);
      typeN = Integer.parseInt(br.readLine());
    }
    String category;
    if (typeN == 1) {
      category = categoryGuide(Guide.income, br);
    } else {
      category = categoryGuide(Guide.expenses, br);
    }
    List<Record> records = RecordMethods.records;
    List<Record> selected = new ArrayList<>();
    boolean y = false; // флаг для определения пустого списка
    for (Record record : records) {
      if ((record.getCategory().equals(category)) &&
          (record.getMonth() == currentMonth) &&
          (record.getYear() == DateMethods.checkCurrentYear())) {
        selected.add(record);
        y = true;
      }
    }
    if (!y) {
      System.out.println("Список в этой категории пуст");
    }
    return selected;
  }

  /**
   * Метод считает сумму за месяц по выбранной категории
   *
   * @param currentMonth при вызове из меню текущий месяц, при вызове из метода выбранный месяц
   * @param category     выбранная категория
   * @return сумма по категории
   */
  public static int doSumCategory(int currentMonth, String category) {
    int result = 0;
    for (Record record : RecordMethods.records) {
      if ((record.getCategory().equals(category)) &&
          (record.getMonth() == currentMonth) &&
          (record.getYear() == DateMethods.checkCurrentYear())) {
        result += record.getAmount();
      }
    }
    return result;
  }

  /**
   * Выводит список расходов/доходов с сортировкой по дате и категориям, и итоговую сумму.
   *
   * @param selected отобранных записей
   */
  public static void printCategoryList(List<Record> selected, int currentMonth) throws IOException {
    if (selected.size() != 0) {
      selected.sort(new RecordDateCategoryAmountComparator());
      Record record = selected.get(0);
      String category = record.getCategory();
      System.out.println();
      System.out.println();
      System.out.println("  === Расходы за месяц в категории: " + category + " ===");
      System.out.println(
          "________________________________________________________________________");
      for (Record r : selected) {
        System.out.println(r);
      }
      System.out.println(
          "________________________________________________________________________");
      System.out.println(
          "Итого: " + SumAmountToString(SumAmount(selected)) + " EUR");
    }
    int month = MenuMethods.horizontalMenu(currentMonth + 1) - 1;
    if (month != 12 && month <= DateMethods.checkCurrentMonth()) {
      RecordMethods.printCategoryList(RecordMethods.doCategoryList(month), month);
    }
  }


  /**
   * Считает сумму денег в переданном списке, переводит результат в десятичную дробь с округлением
   * до сотых долей.
   *
   * @param records список
   * @return строка с суммой
   */
  public static int SumAmount(List<Record> records) {
    int result = 0;
    for (Record record : records) {
      result += record.getAmount();
    }
    return result;
  }

  /**
   * Приводит сумму денег в вид для печати - с 2 знаками после запятой.
   */
  public static String SumAmountToString(int sumAmount) {
    return Double.toString((double) sumAmount / 100);
  }
}
