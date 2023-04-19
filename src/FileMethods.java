import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileMethods {

  /**
   * Метод выбора или создания файла для работы
   *
   * @param pathToFile имя текущего файла
   * @return pathToFile имя выбранного файла
   * @throws IOException    не обрабатывается
   * @throws ParseException не обрабатывается
   */
  public static String openFile(String pathToFile) throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    pathToFile = changeFile(pathToFile);
    while (pathToFile.isEmpty()) {
      System.out.print("Имя файла не определенно. Продолжим или закончим? ");
      String command = br.readLine().toLowerCase();
      switch (command) {
        case "1", "yes", "continue", "да", "продолжим", "продолжить" ->
            pathToFile = changeFile(pathToFile);
        case "0", "no", "stop", "нет", "конец", "закончим", "закончить" -> {
          System.out.println("До свидания!");
          System.exit(1);
        }
      }
    }
    return pathToFile;
  }

  /**
   * Метод выбора другого файла
   *
   * @param pathToFile имя текущего файла
   * @return pathStr имя выбранного файла
   * @throws IOException    обрабатывается
   * @throws ParseException не обрабатывается
   */
  public static String changeFile(String pathToFile) throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    if (!pathToFile.isEmpty()) {
      System.out.println("Текущий файл: " + pathToFile);
    }
    System.out.print("Введите имя файла с вашими данными: ");
    String newPath = br.readLine();
    String pathStr = "";
    try {
      Path path = Paths.get(newPath);
      boolean exists = Files.isRegularFile(path);
      if (exists) {                                                          // если файл существует
        String s = Files.probeContentType(path);
        if (!s.equals("text/plain")) {                                     // если не текстовый файл
          System.out.println(Colors.RED + "Файл \"" + newPath +
              "\" не является текстовым!" + Colors.RESET);
          if (!pathToFile.isEmpty()) {         // если были в каком-то файле, имя оставляем таким же
            pathStr = pathToFile;
            System.out.println("Текущий файл: " + pathToFile);
          } else {            // создаем новое имя файла - имя нетекстового файла с расширением .txt
            pathStr = newPath.substring(0, newPath.length() - 3) + "txt";
            System.out.println("Создаем файл " + pathStr);
            pathStr = newFile(pathToFile, pathStr);
          }
        } else {
          pathStr = newPath;                                                // берем новое имя файла
        }
      } else {                                                            // если файл не существует
        System.out.println(Colors.RED + "Нет файла " + newPath + " с данными" + Colors.RESET);
        System.out.println("Создаем новый файл " + newPath);
        pathStr = newFile(pathToFile, newPath);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return pathStr;
  }

  /**
   * Метод создает новый файл
   *
   * @param pathToFile имя текущего файла
   * @param newPath    имя нового файла
   * @return pathStr имя выбранного файла
   * @throws IOException не обрабатывается
   */
  public static String newFile(String pathToFile, String newPath)
      throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    if (!pathToFile.isEmpty()) {                                             //если был текущий файл
      System.out.println("1 - Создать список в файле " + newPath);
      System.out.println("2 - Создать новый список в файле " + pathToFile);
      System.out.println("3 - Вернуться в меню и остаться в файле ");
      String file = (br.readLine());
      while (!(file.equals("1") || file.equals("2") || file.equals("3"))) {
        System.out.print(Colors.RED + "Некорректное значение. Попробуйте еще раз: " + Colors.RESET);
        file = br.readLine();
      }
      switch (file) {
        case "1" -> {                                                // создать список в новом файле
          MenuMethods.pathToFile_ = newPath;
          RecordMethods.records = createNewList();
          FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
        }
        case "2" -> {                                        // создать новый список в текущем файле
          MenuMethods.pathToFile_ = pathToFile;
          RecordMethods.records = createNewList();
          FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
        }
        case "3" -> MenuMethods.pathToFile_ = pathToFile;       // вернуться в меню с текущим файлом
      }
    } else {                                                         // при первом входе в программу
      MenuMethods.pathToFile_ = newPath;
      RecordMethods.records = createNewList();
      FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
    }
    return MenuMethods.pathToFile_;
  }

  /**
   * Метод создает новый список в файле
   *
   * @return List<Record> records возвращает созданный список
   * @throws IOException    не обрабатывается
   * @throws ParseException не обрабатывается
   */
  public static List<Record> createNewList() throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Создание нового списка: ");
    List<Record> records = new ArrayList<>();
    int i = 1;
    while (i == 1) {
      System.out.println();
      System.out.println("Новая запись:");
      System.out.print("Дата (\"ДД.ММ.ГГГГ\") - ");
      String dateStr = RecordMethods.dateValidation(br); // проверка формата ввода
      System.out.print("Доход/расход: (1/0): ");
      int typeN = Integer.parseInt(br.readLine());
      while ((typeN < 0) || typeN > 1) {
        System.out.println(Colors.RED + "Доход - 1, расход - 0" + Colors.RESET);
        typeN = Integer.parseInt(br.readLine());
      }
      String type;
      String category;
      Guide.expenses = Guide.readGuideE();
      Guide.income = Guide.readGuideI();
      if (typeN == 1) {
        type = "Доход";
        category = RecordMethods.categoryGuide(Guide.income, br);
      } else {
        type = "Расход";
        category = RecordMethods.categoryGuide(Guide.expenses, br);
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
      System.out.print("Добавить новую запись (1-да, 2-выход): ");
      i = Integer.parseInt(br.readLine());
    }
    return records;
  }

  /**
   * Метод чтения данных из файла
   *
   * @return List<Record> records лист со списком
   * @throws IOException               если файла со списком не существует
   * @throws IndexOutOfBoundsException если файл со списком пустой
   */
  public static List<Record> readFile() throws IOException, ParseException {
    List<Record> records = new ArrayList<>();
    try {
      List<String> lines = new ArrayList<>();
      BufferedReader fr = new BufferedReader(new FileReader(MenuMethods.pathToFile_));
      String line;
      while ((line = fr.readLine()) != null) {
        lines.add(line);
      }
      for (String s : lines) {
        List<String> columns = List.of(s.split(";", -1));
        int amount = Integer.parseInt(columns.get(2));
        Record record = new Record(columns.get(0), columns.get(1), amount, columns.get(3),
            columns.get(4));
        records.add(record);
      }
      fr.close();
    } catch (IOException e) {
      System.out.println("У Вас не обнаружен списка");
      RecordMethods.records = createNewList();
      FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(Colors.RED + "В текущем файле нет списка!" + Colors.RESET);
      RecordMethods.records = createNewList();
      FileMethods.writeFile(MenuMethods.pathToFile_, RecordMethods.records);
    }
    return records;
  }

  /**
   * Метод записывает лист списка в файл
   *
   * @param pathToFile имя текущего файла
   * @param records    лист списка
   * @throws IOException не обрабатывается
   */
  public static void writeFile(String pathToFile, List<Record> records) throws IOException {
    FileWriter fr = new FileWriter(pathToFile);
    for (Record record : records) {
      fr.write(record.toStringFile());
    }
    fr.close();
  }
}
