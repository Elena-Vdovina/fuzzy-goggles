import java.io.IOException;

public class Main {


  public static void main(String[] args) throws IOException {
    String pathToFile = FileMethods.openFile();  // определяем файл пользователя
    MenuMethods.menu();  // вход в основное меню
  }

}