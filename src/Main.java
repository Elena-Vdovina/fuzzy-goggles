import java.io.IOException;
import java.text.ParseException;

public class Main {


  public static void main(String[] args) throws IOException, ParseException {
    MenuMethods.pathToFile_ = "";
    // определяем файл пользователя
    MenuMethods.pathToFile_ = FileMethods.openFile(MenuMethods.pathToFile_);
    MenuMethods.menu();  // вход в основное меню
  }

}

/*
01.03.2023;Расход;22201;expenses;Обучение
29.03.2023;Расход;100201;income;Аванс
01.04.2023;Расход;200;expenses;Транспорт
03.04.2023;Расход;40000;expenses;Жилье
06.04.2023;Расход;500;expenses;Ремонт
10.04.2023;Расход;400000;income;Зарплата
12.04.2023;Расход;15000;expenses;Продукты
 */