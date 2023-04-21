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
    listMenuT.add("Просмотр данных"); // 1
    listMenuT.add("Добавить запись"); // 2
    listMenuT.add("Изменить запись"); // 3
    listMenuT.add("Удалить запись");  // 4
    listMenuT.add("Только расходы");  // 5
    listMenuT.add("Только доходы");   // 6
    listMenuT.add("Структура доходов/расходов"); // 7
    listMenuT.add("Выборка по категории"); // 8
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
    listMenuG.add("Просмотр категорий доходов");
    listMenuG.add("Добавить категорию доходов");
    listMenuG.add("Просмотр категорий расходов");
    listMenuG.add("Добавить категорию расходов");
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
    Guide.expenses = Guide.readGuideE();
    Guide.income = Guide.readGuideI();
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
          menuTable();
        }
        case 4 -> menuGuide();                                  // Меню работы со справочниками
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
    int currentMonth = DateMethods.checkCurrentMonth();
    int command = readCommand(menu);
    while (command != menu.size()) {
      switch (command) {
        case 1 -> RecordMethods.printRecord();             // Просмотр данных
        case 2 -> RecordMethods.addRecord();               // Добавить запись
        case 3 -> RecordMethods.changeRecord();            // Изменить запись
        case 4 -> RecordMethods.removeRecord();            // Удалить запись
        case 5 -> RecordMethods.printTypeList(
            RecordMethods.doTypeList(currentMonth, "Расход"), currentMonth, "Расход");
        // Список расходов
        case 6 -> RecordMethods.printTypeList(
            RecordMethods.doTypeList(currentMonth, "Доход"), currentMonth, "Доход");
        // Список доходов
        case 7 -> BudgetMethods.drawTypeDiagram(currentMonth,
            RecordMethods.SumAmount(RecordMethods.doTypeList(currentMonth, "Доход")),
            RecordMethods.SumAmount(RecordMethods.doTypeList(currentMonth, "Расход")));
        // Диаграмма
        case 8 -> RecordMethods.printCategoryList(RecordMethods.doCategoryList(currentMonth),
            currentMonth);                                 // Выборка по категории
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

  /**
   * Метод выводит на экран горизонтальное меню выбора месяца
   * @param currentMonth при вызове из меню текущий месяц, при вызове из метода выбранный месяц
   * @return номер выбранного месяца
   * @throws IOException обработка неправильного ввода
   */
  public static int horizontalMenu(int currentMonth) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Выберите месяц ");
    int n = DateMethods.checkCurrentMonth();
    String c1 = Colors.YELLOW;
    String c2 = Colors.RESET;
    for (int i = 1; i <= n + 1; ++i) {
      if (i == 1) {
        if (i == currentMonth) {
          System.out.print(c1 + " 1 - Январь " + c2);
        } else {
          System.out.print(" 1 - Январь ");
        }
      }
      if (i == 2) {
        if (i == currentMonth) {
          System.out.print(c1 + " 2 - Февраль " + c2);
        } else {
          System.out.print(" 2 - Февраль ");
        }
      }
      if (i == 3) {
        if (i == currentMonth) {
          System.out.print(c1 + " 3 - Март " + c2);
        } else {
          System.out.print(" 3 - Март ");
        }
      }
      if (i == 4) {
        if (i == currentMonth) {
          System.out.print(c1 + " 4 - Апрель " + c2);
        } else {
          System.out.print(" 4 - Апрель ");
        }
      }
      if (i == 5) {
        if (i == currentMonth) {
          System.out.print(c1 + " 5 - Май " + c2);
        } else {
          System.out.print(" 5 - Май ");
        }
      }
      if (i == 6) {
        if (i == currentMonth) {
          System.out.print(c1 + " 6 - Июнь " + c2);
        } else {
          System.out.print(" 6 - Июнь ");
        }
      }
      if (i == 7) {
        if (i == currentMonth) {
          System.out.print(c1 + " 7 - Июль " + c2);
        } else {
          System.out.print(" 7 - Июль ");
        }
      }
      if (i == 8) {
        if (i == currentMonth) {
          System.out.print(c1 + " 8 - Август " + c2);
        } else {
          System.out.print(" 8 - Август ");
        }
      }
      if (i == 9) {
        if (i == currentMonth) {
          System.out.print(c1 + " 9 - Сентябрь " + c2);
        } else {
          System.out.print(" 9 - Сентябрь ");
        }
      }
      if (i == 10) {
        if (i == currentMonth) {
          System.out.print(c1 + " 10 - Октябрь " + c2);
        } else {
          System.out.print(" 10 - Октябрь ");
        }
      }
      if (i == 11) {
        if (i == currentMonth) {
          System.out.print(c1 + " 11 - Ноябрь " + c2);
        } else {
          System.out.print(" 11 - Ноябрь ");
        }
      }
      if (i == 12) {
        if (i == currentMonth) {
          System.out.print(c1 + " 12 - Декабрь " + c2);
        } else {
          System.out.print(" 12 - Декабрь ");
        }
      }
    }
    System.out.println(" 13 - Выход");
    int result = Integer.parseInt(br.readLine());
    while ((result < 1) || result > 13) {
      System.out.print(Colors.RED + "Повторите ввод: " + Colors.RESET);
      result = Integer.parseInt(br.readLine());
    }
    return result;
  }
}


