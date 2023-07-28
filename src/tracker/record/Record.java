package tracker.record;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {

  private Date date;           // дата
  private String article;      // наименование статьи расхода или дохода
  private int amount;          // сумма в центах
  private String type;         // тип - доход или расход
  private String category;     // категория - из справочника
  private static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

  public Record(String dateStr, String article, int amount, String type, String category)
      throws ParseException {
    setDate(dateStr);
    this.article = article;
    this.amount = amount;
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

  public void setAmount(int amount) {
    this.amount = amount;
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

  public int getMonth() {
    return date.getMonth();
  }

  public int getYear() {
    return date.getYear();
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
    double amountD = (double) amount / 100;
    return String.format("%-12s %-10s %-25s %10.2f  %-30s", getDateStr(), type, category, amountD,
        article);
  }

  public String toStringFile() {
    return String.format("%s;%s;%d;%s;%s\n", getDateStr(), article, amount, type, category);
  }
}

