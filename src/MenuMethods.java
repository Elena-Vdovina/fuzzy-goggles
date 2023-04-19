import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MenuMethods {

  public static String pathToFile_;

  /**
   * Создаем основное меню
   *
   * @return menu
   */
  private static List<String> listMenu() {
    List<String> menu = new ArrayList<>(6);
    menu.add("Выбрать другой файл");
    menu.add("Создать новый файл");
    menu.add("Работать с данными");
    menu.add("Работать со справочниками");
    menu.add("Структура доходов - расходов"); //???
    menu.add("Выход");
    return menu;
  }

  /**
   * Создаем подменю работы с таблицей данных
   *
   * @return listMenuT
   */
  private static List<String> listMenuT() {
    List<String> listMenuT = new ArrayList<>(7);
    listMenuT.add("Просмотр данных");
    listMenuT.add("Добавить запись");
    listMenuT.add("Изменить запись");
    listMenuT.add("Удалить запись");
    listMenuT.add("Только расходы");
    listMenuT.add("Только доходы");
    listMenuT.add("Сортировать по");
    listMenuT.add("Вернуться в основное меню");
    return listMenuT;
  }

  /**
   * Создаем подменю работы со справочниками
   *
   * @return listMenuG
   */
  private static List<String> listMenuG() {
    List<String> listMenuG = new ArrayList<>(5);
    listMenuG.add("Просмотр статей доходов");
    listMenuG.add("Добавить статью доходов");
    listMenuG.add("Просмотр статей расходов");
    listMenuG.add("Добавить статью расходов");
    listMenuG.add("Вернуться в основное меню");
    return listMenuG;
  }

  /**
   * Печатаем меню и читаем команду
   *
   * @param menu Меню по контексту
   * @return command
   * @throws IOException Обработка некорректной команды
   */
  public static int readCommand(List<String> menu) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println();
    for (int i = 0; i < menu.size(); ++i) {
      System.out.println(("    " + (i + 1) + ". " + menu.get(i)));
    }
    System.out.println();
    int command = 0;
    while (command < 1 || command > menu.size()) {
      System.out.print("Введите номер команды: ");
      try {
        command = Integer.parseInt(br.readLine());
      } catch (NumberFormatException e) {
        System.err.println("Некорректная команда:: " + e.getMessage());
        System.out.print("Введите номер команды: ");
      }
    }
    return command;
  }

  /**
   * Основной рабочий цикл программы, обрабатывающий команды
   *
   * @throws IOException Обработка некорректной команды
   */
  public static void menu() throws IOException, ParseException {
    List<String> menu = listMenu();
    int command = readCommand(menu);
    while (command != menu.size()) {
      switch (command) {
        case 1 ->                                               // Выбрать другой файл
            pathToFile_ = FileMethods.openFile(pathToFile_);
        case 2 ->                                               // Создать новый файл
            pathToFile_ = FileMethods.changeFile(pathToFile_);
        case 3 -> {                                             // Меню работы с таблицей
          RecordMethods.records = FileMethods.readFile();
          if (RecordMethods.records.size() == 0) {
            FileMethods.createNewList();
            RecordMethods.records = FileMethods.readFile();
          }
          Guide.expenses = Guide.readGuideE();
          Guide.income = Guide.readGuideI();
          menuTable();
        }
        case 4 -> {                                             // Меню работы со справочниками
          Guide.expenses = Guide.readGuideE();
          Guide.income = Guide.readGuideI();
          menuGuide();
        }
        case 5 -> System.out.println("analytics");              // ????
      }
      command = readCommand(menu);                              // Выход из программы
    }
    System.out.println("До свидания!");
  }

  /**
   * Подменю работы с таблицей данных
   *
   * @throws IOException Обработка некорректной команды
   */
  public static void menuTable() throws IOException, ParseException {
    List<String> menu = listMenuT();
    int command = readCommand(menu);
    while (command != menu.size()) {
      switch (command) {
        case 1 -> RecordMethods.printRecord();             // Просмотр данных
        case 2 -> RecordMethods.addRecord();               // Добавить запись
        case 3 -> RecordMethods.changeRecord();            // Изменить запись
        case 4 -> RecordMethods.removeRecord();            // Удалить запись
        case 5 -> RecordMethods.printTypeList("expenses"); // Список расходов
        case 6 -> RecordMethods.printTypeList("income");   // Список доходов
        case 7 -> System.out.println("SORT1 RECORD");      // Сортировать по
      }
      command = readCommand(menu);                         // Вернуться в основное меню
    }
    System.out.println("В основное меню");
  }

  /**
   * Подменю работы со справочниками
   *
   * @throws IOException Обработка некорректной команды
   */
  public static void menuGuide() throws IOException {
    List<String> menu = listMenuG();
    int command = readCommand(menu);
    while (command != menu.size()) {
      switch (command) {
        case 1 -> Guide.printGuideI();  // Просмотр статей доходов
        case 2 -> Guide.addGuideI();    // Добавить статью дохода
        case 3 -> Guide.printGuideE();  // Просмотр статей расходов
        case 4 -> Guide.addGuideE();    // Добавить статью расхода
      }
      command = readCommand(menu);      // Вернуться в основное меню
    }
    System.out.println("В основное меню");
  }
}
