import java.io.IOException;
import java.text.ParseException;

public class Main {

  /**
   * Точка входа в программу
   *
   * @param args список аргументов
   * @throws IOException    не обрабатывается
   * @throws ParseException не обрабатывается
   */
  public static void main(String[] args) throws IOException, ParseException {
   // MenuMethods.pathToFile_ = "";
    // определяем файл пользователя
   // MenuMethods.pathToFile_ = FileMethods.openFile(MenuMethods.pathToFile_);
    //MenuMethods.menu();  // вход в основное меню
    MenuMethods.ListOfFiles();
  }
}
