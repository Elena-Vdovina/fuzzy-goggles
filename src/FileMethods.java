
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
   * Пытаемся открыть файл
   *
   * @return pathToFile имя выбранного файла
   * @throws IOException не обрабатывается
   */
  public static String openFile(String pathToFile) throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    pathToFile = changeFile(pathToFile);
    System.out.println("1 " + pathToFile);
    while (pathToFile.isEmpty()) { // выбор или создание файла для работы
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
      System.out.println("1 " + pathToFile);
    }
    return pathToFile;
  }

  /**
   * Открываем другой файл
   *
   * @param pathToFile имя текущего файла
   * @return pathStr имя выбранного файла
   * @throws IOException не обрабатывается
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
      if (exists) {
        String s = Files.probeContentType(path);
        if (!s.equals("text/plain")) {
          System.out.println(Colors.RED + "Файл \"" + newPath +
              "\" не является текстовым!" + Colors.RESET);
          pathStr = "";
        } else {
          pathStr = newPath;
          System.out.println(newPath + " " + pathStr);
        }
      } else {
        System.out.println(Colors.RED + "Нет файла " + newPath + " с данными" + Colors.RESET);
        // pathStr = newFile(pathToFile, newPath);
        pathStr = "";
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    if (pathStr.isEmpty()) {
      System.out.println("Создать файл " + newPath + " с данными");
      pathStr = newFile(pathToFile, newPath);
    }
    return pathStr;
  }

  /**
   * Создаем новый файл
   *
   * @param pathToFile имя текущего файла
   * @param newPath    имя нового файла
   * @return pathStr имя выбранного файла
   * @throws IOException не обрабатывается
   */
  public static String newFile(String pathToFile, String newPath)
      throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String pathStr = "";
    System.out.println(pathToFile + " " + newPath);
    if (!pathToFile.isEmpty()) {
      System.out.println("1 - Создать список дел в файле " + newPath);
      System.out.println("2 - Создать новый список дел в файле " + pathToFile);
      System.out.println("3 - Вернуться в меню и остаться в файле ");
      String file = (br.readLine());
      while (!(file.equals("1") || file.equals("2") || file.equals("3"))) {
        // проверка на соответствующее значение
        System.out.print(Colors.RED + "Некорректное значение. Попробуйте еще раз: " + Colors.RESET);
        file = br.readLine();
      }
      switch (file) {
        case "1" -> {  // создать список дел в новом файле
          createNewList(newPath);
          pathStr = newPath;
        }
        case "2" -> {   // создать новый список дел в текущем файле
          createNewList(pathToFile);
          pathStr = pathToFile;
        }
        case "3" -> pathStr = pathToFile;  // вернуться в меню с текущим файлом
      }
    } else {
      createNewList(newPath);
      pathStr = newPath;
    }
    return pathStr;
  }

  public static void createNewList(String pathToFile) throws IOException, ParseException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Создание нового списка дел: ");
    List<java.lang.Record> records = new ArrayList<>();
    // прочитали
    int i = 1;
    while (i == 1) { //
      System.out.println();
      RecordMethods.addRecord();
      System.out.print("Добавить новую запись (1-да, 2-выход): ");
      i = Integer.parseInt(br.readLine());
    }
    writeFile();
    //printList(pathToFile);
  }

  /**
   * Метод чтения данных из файла
   *
   * @return List<Record> records лист с записями
   * @throws IOException               если файла с записями не существует
   * @throws IndexOutOfBoundsException если файл с записями пустой
   */
  public static List<Record> readFile() throws IOException, ParseException {
    RecordMethods.records = new ArrayList<>();
    try {
      List<String> lines = new ArrayList<>();
      BufferedReader fr = new BufferedReader(new FileReader(MenuMethods.pathToFile_));
      String line;
      while ((line = fr.readLine()) != null) {
        lines.add(line);
      }
      System.out.println(lines);

      for (String s : lines) {
        List<String> columns = List.of(s.split(";", -1));
        int amount = Integer.parseInt(columns.get(2));
        Record record = new Record(columns.get(0), columns.get(1), amount, columns.get(3),
            columns.get(4));
        RecordMethods.records.add(record);
        System.out.println(record);
      }

      fr.close();
    } catch (IOException e) {
      System.out.println("У Вас не обнаружен список дел");
      createNewList(MenuMethods.pathToFile_);
      //} catch (ParseException e) {
      //  throw new RuntimeException(e);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(Colors.RED + "В текущем файле нет списка дел!" + Colors.RESET);
      createNewList(MenuMethods.pathToFile_);
    }
    return RecordMethods.records;
  }

  /**
   * Метод записи List<Record> records в файл
   *
   * @throws IOException не обрабатывается
   */
  public static void writeFile() throws IOException {
    FileWriter fr = new FileWriter(MenuMethods.pathToFile_);
    for (Record record : RecordMethods.records) {
      fr.write(record.toStringFile());
    }
    fr.close();
  }
}
