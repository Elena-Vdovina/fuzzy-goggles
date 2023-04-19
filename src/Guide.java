import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Guide {

  public static List<String> income;
  public static List<String> expenses;

  /**
   * Метод читает справочник доходов из файла
   *
   * @return income лист доходов
   * @throws IOException не обрабатывается
   */
  public static List<String> readGuideI() throws IOException {
    List<String> income = new ArrayList<>();
    File pathGuide = new File("res/income.txt");                   // создали путь к файлу
    if (!pathGuide.exists()) {
      pathGuide.createNewFile();                                       // создали файл, если его нет
    }
    BufferedReader br = new BufferedReader(new FileReader(pathGuide));
    for (String line = br.readLine(); line != null; line = br.readLine()) {
      income.add(line);
    }
    br.close();
    return income;
  }

  /**
   * Метод читает справочник расходов из файла
   *
   * @return expenses лист расходов
   * @throws IOException не обрабатывается
   */
  public static List<String> readGuideE() throws IOException {
    List<String> expenses = new ArrayList<>();
    File pathGuide = new File("res/expenses.txt");                 // создали путь к файлу
    if (!pathGuide.exists()) {
      pathGuide.createNewFile();                                       // создали файл, если его нет
    }
    BufferedReader br = new BufferedReader(new FileReader(pathGuide));
    for (String line = br.readLine(); line != null; line = br.readLine()) {
      expenses.add(line);
    }
    br.close();
    return expenses;
  }

  /**
   * Метод печатает лист доходов на экран
   */
  public static void printGuideI() {
    System.out.println();
    System.out.println("Справочник доходов");
    int i = 0;
    for (String s : income) {
      System.out.println(i + 1 + " " + s);
      ++i;
    }
  }

  /**
   * Метод добавляет новый вид дохода в справочник
   *
   * @throws IOException не обрабатывается
   */
  public static void addGuideI() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println();
    System.out.print("Новый вид дохода: ");
    String line = br.readLine();
    while (line.isEmpty()) {
      System.out.println(Colors.RED + "Содержание не может быть пустым:" + Colors.RESET);
      line = br.readLine();
    }
    income.add(line);
    writeGuide("res/income.txt", income);
  }

  /**
   * Метод печатает расходов на экран
   */
  public static void printGuideE() {
    System.out.println();
    System.out.println("Справочник расходов");
    int i = 0;
    for (String s : expenses) {
      System.out.println(i + 1 + " " + s);
      ++i;
    }
  }

  /**
   * Метод добавляет новый вид расходов в справочник
   *
   * @throws IOException не обрабатывается
   */
  public static void addGuideE() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println();
    System.out.print("Новый вид расходов: ");
    String line = br.readLine();
    while (line.isEmpty()) {
      System.out.println(Colors.RED + "Содержание не может быть пустым:" + Colors.RESET);
      line = br.readLine();
    }
    expenses.add(line);
    writeGuide("res/income.txt", expenses);
  }

  /**
   * Метод записывает справочник в файл
   *
   * @param pathGuide имя файла справочника
   * @param guide лист справочника
   * @throws IOException не обрабатывается
   */
  public static void writeGuide(String pathGuide, List<String> guide) throws IOException {
    FileWriter fileWriter = new FileWriter(pathGuide);
    for (String s : guide) {
      fileWriter.write(s + "\n");
    }
    fileWriter.close();
  }
}
