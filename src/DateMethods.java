import java.util.Date;

public class DateMethods {

  /**
   * Получает текущий месяц из системной даты.
   *
   * @return string month
   */
  public static int checkCurrentMonth() {
    Date current = new Date(); // записываем текущую системную дату
    return current.getMonth();
  }

}