import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BudgetMethods {

  public static void drawTypeDiagram(int sumAmount1, int sumAmount2) throws IOException {
    int percent = sumAmount1 / 100; // доходы - 100% имеющихся денег, 1500 / 100 = 15
    int part1 = 100; // 1500
    int part2 = sumAmount2 / percent; // 1300 / 15  = 86,66
    int width1 = Math.round(part1);
    int width2 = Math.round(part2);
    String color1 = Colors.BLUE_BOLD_BRIGHT; // income
    String color2 = Colors.RED_BOLD_BRIGHT; // expenses
    System.out.println();
    System.out.println();
    System.out.println("=== Структура доходов/расходов ===");
    System.out.println();
    drawLine(width1, color1);
    System.out.printf("  Доходы %s EUR - %d%%",
        RecordMethods.SumAmountToString(sumAmount1), 100);
    System.out.println();
    for (String s : Guide.income) {
      int sum = RecordMethods.doSumCategory(s);
      int part = sum / percent;
      int width = Math.round(part);
      drawLine(width, Colors.BLUE);
      System.out.printf(" %s %s EUR - %d%%", s, RecordMethods.SumAmountToString(sum),
          part);
      System.out.println();
    }
    System.out.println();
    drawLine(width2, color2);
    System.out.printf("  Расходы %s EUR - %d%%", RecordMethods.SumAmountToString(sumAmount2),
        part2);
    System.out.println();
    for (String s : Guide.expenses) {
      int sum = RecordMethods.doSumCategory(s);
      int part = sum / percent;
      int width = Math.round(part);
      drawLine(width, Colors.RED);
      System.out.printf(" %s %s EUR - %d%%", s, RecordMethods.SumAmountToString(sum),
          part);
      System.out.println();
    }
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String k= br.readLine();
  }

  public static void drawLine(int width, String color) {
    char c = (char) (9632);
    for (int i = 0; i < width; i++) {
      System.out.print(color + c + Colors.RESET);
    }

  }
}
