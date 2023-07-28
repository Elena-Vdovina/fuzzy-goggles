package tracker.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import tracker.record.Record;
import tracker.comparator.RecordDateCategoryAmountComparator;

public class RecordTests {

  @Test
  public void RecordDateCategoryAmountComparator() throws ParseException {
    // arrange - упорядочить, установить
    Record record1 = new Record("01.01.2023", "A", 5, "expenses", "Продукты");
    Record record2 = new Record("02.02.2023", "B", 5, "expenses", "Продукты");
    Record record3 = new Record("02.02.2023", "C", 25, "expenses", "Транспорт");
    Record record4 = new Record("03.03.2023", "C", 20, "expenses", "Обучение");
    Record record5 = new Record("02.02.2023", "C", 2, "expenses", "Транспорт");

    List<Record> actual = new ArrayList<>();
    actual.add(record5);
    actual.add(record1);
    actual.add(record2);
    actual.add(record4);
    actual.add(record3);

    List<Record> expected = new ArrayList<>();
    expected.add(record1);
    expected.add(record2);
    expected.add(record5);
    expected.add(record3);
    expected.add(record4);

    // act - выполнить действие
    actual.sort(new RecordDateCategoryAmountComparator());

    // assert - сравнить результат с правильным
    assertEquals(expected, actual);
  }

}
