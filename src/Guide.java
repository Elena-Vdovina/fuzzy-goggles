import java.util.ArrayList;
import java.util.List;

public class Guide {

  final public static char SEP = ';';
  public static List<String> income;
  public static List<String> expenses;

  public static List<String> readGuideI() {
    List<String> income = new ArrayList<>();
    income.add("Аванс");
    income.add("Зарплата");
    income.add("Проценты");
    income.add("Гонорар");
    income.add("Подарки");
    income.add("Пособие");
    return income;
  }

  public static List<String> readGuideE() {
    List<String> expenses = new ArrayList<>();
    expenses.add("Коммунальные платежи");
    expenses.add("Продукты");
    expenses.add("Одежда");
    expenses.add("Транспорт");
    expenses.add("Бытовая химия");
    expenses.add("Аптека");
    expenses.add("Обучение");
    expenses.add("Игрушки");
    expenses.add("Канцелярия");
    expenses.add("Домашние животные");
    expenses.add("Жилье");
    expenses.add("Ремонт");
    expenses.add("Техника");
    expenses.add("Мебель");
    expenses.add("Развлечения");
    expenses.add("Связь");
    expenses.add("Копилка");
    expenses.add("Инвестиции");
    return expenses;
  }
/*
    public static void main(String[] args) throws IOException {
      File phoneBook = new File("res/phonebook.txt"); // создали путь к файлу
      if (!phoneBook.exists()) { // файла ещё нет - первый запуск программы
        phoneBook.createNewFile(); // создали файл, если его нет
      }

      List<Map<String, String>> records = new ArrayList<>();
      BufferedReader br = new BufferedReader(new FileReader(phoneBook)); // открыли файл для чтения
      // читаем строчки файла, пока они есть
      // если строчек нет или они закончились, readLine() вернёт null
      // курсор в файле всегда стоит в конце уже прочитанных данных
      for (String line = br.readLine(); line != null; line = br.readLine()) {
        int lastSep = line.lastIndexOf(SEP); // последнее появление разделителя
        // прочитал из файла строку вида "имя;номер телефона", ';' - это SEP
        String name = line.substring(0, lastSep); // имя до разделителя
        String phone = line.substring(lastSep + 1); // номер телефона после разделителя

        Map<String, String> entry = new HashMap<>();
        entry.put("name", name);
        entry.put("phone", phone);
        // создал запись - словарь {"name": "имя", "phone": "номер телефона"}
        records.add(entry); // добавил эту запись в список записей
      }
      br.close(); // закрываю файл после чтения

      // Что-то делаю с записями или даже добавляю новые
      Map<String, String> newEntry = new HashMap<>();
      newEntry.put("name", "Name"); // создал запись - словарь {"имя": "номер телефона"}
      newEntry.put("phone", "Phone"); // создал запись - словарь {"имя": "номер телефона"}
      records.add(newEntry); // добавил эту запись в список записей

      FileWriter fileWriter = new FileWriter(phoneBook); // открываю этот файл теперь для записи
      for (Map<String, String> entry : records) {
        String name = entry.get("name");
        String phone = entry.get("phone");
        fileWriter.write(name + SEP + phone + "\n");
      }
      fileWriter.close(); // закрываем файл после записи
    }

 */
}
