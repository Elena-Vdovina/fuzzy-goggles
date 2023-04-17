import java.util.Date;

public class Budget {
  private Date date;         // дата
  private String article;    // наименование статьи расхода или дохода
  private int amount;        // сумма в центах
  private int typ;           // тип - доход или расход
  private int category;      // тип - из справочника

  public Budget(){

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
    this.typ = typ;
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
    return typ;
  }

  public int getCategory() {
    return category;
  }

  @Override
  public String toString() {
    return "Budget{" +
        "date=" + date +
        ", article='" + article + '\'' +
        ", amount=" + amount +
        ", typ=" + typ +
        ", category=" + category +
        '}';
  }
}
