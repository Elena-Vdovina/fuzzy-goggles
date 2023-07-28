package tracker;

import java.io.IOException;
import java.text.ParseException;
import tracker.methods.MenuMethods;

public class Main {

  /**
   * Точка входа в программу
   *
   * @param args список аргументов
   * @throws IOException    не обрабатывается
   * @throws ParseException не обрабатывается
   */
  public static void main(String[] args) throws IOException, ParseException {
    MenuMethods.menuListOfFiles();
  }
}
