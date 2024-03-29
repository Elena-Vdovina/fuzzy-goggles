package tracker.comparator;

import java.util.Comparator;

import tracker.record.Record;

public class RecordDateCategoryAmountComparator implements Comparator<Record> {

    /**
     * Метод сравнивает два объекта типа Record
     */
    @Override
    public int compare(Record o1, Record o2) {
        if (!o1.getDate().equals(o2.getDate())) {
            return o1.getDate().compareTo(o2.getDate());
        } else if (!o1.getCategory().equals(o2.getCategory())) {
            return o1.getCategory().compareTo(o2.getCategory());
        } else {
            return o1.getAmount() - (o2.getAmount());
        }
    }
}
