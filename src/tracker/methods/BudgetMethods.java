package tracker.methods;

import java.io.IOException;
import java.text.ParseException;

import tracker.service.Colors;

public class BudgetMethods {

    public static void drawTypeDiagram(int currentMonth, int sumAmount1, int sumAmount2)
            throws IOException, ParseException {
        if (sumAmount1 != 0 && sumAmount2 != 0) {
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
                int sum = RecordMethods.doSumCategory(currentMonth, s);
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
                int sum = RecordMethods.doSumCategory(currentMonth, s);
                int part = sum / percent;
                int width = Math.round(part);
                drawLine(width, Colors.RED);
                System.out.printf(" %s %s EUR - %d%%", s, RecordMethods.SumAmountToString(sum),
                        part);
                System.out.println();
            }
        } else if (sumAmount1 == 0 || sumAmount2 == 0) {
            int width1 = Math.round(sumAmount1);
            int width2 = Math.round(sumAmount2);
            String color1 = Colors.BLUE_BOLD_BRIGHT; // income
            String color2 = Colors.RED_BOLD_BRIGHT; // expenses
            System.out.println();
            System.out.println();
            System.out.println("=== Структура доходов/расходов ===");
            System.out.println();
            if (sumAmount1 != 0) {
                width1 = 100;
            }
            drawLine(width1, color1);
            System.out.printf("  Доходы %s EUR",
                    RecordMethods.SumAmountToString(sumAmount1), sumAmount1);
            System.out.println();
            if (sumAmount1 != 0) {
                for (String s : Guide.income) {
                    int sum = RecordMethods.doSumCategory(currentMonth, s);
                    int sum1 = sum / (sumAmount1 / 100);
                    int width = Math.round(sum1);
                    drawLine(width, Colors.BLUE);
                    System.out.printf(" %s %s EUR", s, RecordMethods.SumAmountToString(sum),
                            sum);
                    System.out.println();
                }
            }
            System.out.println();
            if (sumAmount2 != 0) {
                width2=100;
            }
            drawLine(width2, color2);
            System.out.printf("  Расходы %s EUR", RecordMethods.SumAmountToString(sumAmount2),
                    sumAmount2);
            System.out.println();
            if (sumAmount2 != 0) {
                for (String s : Guide.expenses) {
                    int sum = RecordMethods.doSumCategory(currentMonth, s);
                    int sum1 = sum/(sumAmount2/100);
                    int width = Math.round(sum1);
                    drawLine(width, Colors.RED);
                    System.out.printf(" %s %s EUR", s, RecordMethods.SumAmountToString(sum),
                            sum);
                    System.out.println();
                }
            }
        }
        System.out.println();
        int month = MenuMethods.horizontalMenu(currentMonth + 1) - 1;
        if (month != 12 && month <= DateMethods.checkCurrentMonth()) {
            drawTypeDiagram(month,
                    RecordMethods.SumAmount(RecordMethods.doTypeList(month, "Доход")),
                    RecordMethods.SumAmount(RecordMethods.doTypeList(month, "Расход")));
        }
    }

    public static void drawLine(int width, String color) {
        char c = (char) (9632);
        for (int i = 0; i < width; i++) {
            System.out.print(color + c + Colors.RESET);
        }
    }
}
