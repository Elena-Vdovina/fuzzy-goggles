import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {

  private Date date;         // дата
  private String article;    // наименование статьи расхода или дохода
  private int amount;        // сумма в центах
  private String type;           // тип - доход или расход
  private String category;      // тип - из справочника
  private static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

  public Record(String dateStr, String article, double amount, String type, String category)
      throws ParseException {
    setDate(dateStr);
    this.article = article;
    setAmount(amount);
    this.type = type;
    this.category = category;
  }

  public void setDate(String dateStr) throws ParseException {
    this.date = formatter.parse(dateStr);
  }

  public static Date setDateF(String dateStr) throws ParseException {
    return formatter.parse(dateStr);
  }

  public void setArticle(String article) {
    this.article = article;
  }

  public void setAmount(double amount) {
    this.amount = (int) amount * 100;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Date getDate() {
    return date;
  }

  public String getDateStr() {
    return formatter.format(date);
  }

  public String getArticle() {
    return article;
  }

  public int getAmount() {
    return amount;
  }

  public String getType() {
    return type;
  }

  public String getCategory() {
    return category;
  }

  @Override
  public String toString() {
    double amountD=(double)(amount / 100);
    return String.format("%-12s %30s %10.2f %-10s %-20s", getDateStr(), article, amountD, type,
        category);
  }
}
