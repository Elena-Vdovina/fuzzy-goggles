package tracker.methods;

import java.util.Date;

public class DateMethods {

  /**
   * Получает текущий месяц из системной даты.
   *
   * @return int month
   */
  public static int checkCurrentMonth() {
    Date current = new Date(); // записываем текущую системную дату
    return current.getMonth();
  }

  /**
   * Метод получает текущий год из системной даты
   *
   * @return int year
   */
  public static int checkCurrentYear() {
    Date current = new Date();
    return current.getYear();
  }
}
