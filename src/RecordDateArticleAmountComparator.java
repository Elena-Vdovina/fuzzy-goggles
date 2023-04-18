import java.util.Comparator;

public class RecordDateArticleAmountComparator implements Comparator<Record> {

  @Override
  public int compare(Record o1, Record o2) {
    if (!o1.getDate().equals(o2.getDate())) {
      return o1.getDate().compareTo(o2.getDate());
    } else if (!o1.getArticle().equals(o2.getArticle())) {
      return o1.getArticle().compareTo(o2.getArticle());
    } else {
      return o1.getAmount() - (o2.getAmount());
    }
  }
}