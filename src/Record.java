import java.util.Date;

public class Record {
  private Date date;         // дата
  private String article;    // наименование статьи расхода или дохода
  private int amount;        // сумма в центах
  private int type;           // тип - доход или расход
  private int category;      // тип - из справочника

  public Record(String s, String s1, int amount, String type, String category){

  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setArticle(String article) {
    this.article = article;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public void setTyp(int typ) {
    this.type = type;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public Date getDate() {
    return date;
  }

  public String getArticle() {
    return article;
  }

  public int getAmount() {
    return amount;
  }

  public int getTyp() {
    return type;
  }

  public int getCategory() {
    return category;
  }

  @Override
  public String toString() {
    return "Record {" +
        "date=" + date +
        ", article='" + article + '\'' +
        ", amount=" + amount +
        ", type=" + type +
        ", category=" + category +
        '}';
  }
}
