import java.util.ArrayList;
import java.util.List;

public class BugetMethods {

  public static void drawTypeDiagram(int sumAmount1, int sumAmount2) {
    int percent = sumAmount1 / 100; // доходы - 100% имеющихся денег, 1500 / 100 = 15
    int part1 = 100; // 1500
    int part2 = Math.round(sumAmount2 / percent); // 1300 / 15  = 86,66
    int k = 2;
    int width1 = Math.round(part1 / k);
    int width2 = Math.round(part2 / k);
    String color1 = Colors.BLUE; // income
    String color2 = Colors.RED; // expenses
    System.out.println();
    System.out.println();
    System.out.println("=== Диаграмма ===");
    System.out.println();
    drawLine(width1, color1);
    System.out.println();
    System.out.println(
        "Доходы: " + " " + RecordMethods.SumAmountToString(sumAmount1) + " EUR" + " - " + "100%");
    drawLine(width2, color2);
    System.out.println();
    System.out.println(
        "Расходы: " + " " + RecordMethods.SumAmountToString(sumAmount2) + " EUR" + " - " + part2
            + "%");
  }

  public static void drawLine(int width, String color) {
    char c = (char) (9632);
    for (int i = 0; i < width; i++) {
      System.out.print(color + c + Colors.RESET);
    }

  }
}
