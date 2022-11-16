import java.util.HashMap;

public class YearlyReport {
    public int year;
    public final int YEAR_COUNT = 1;
    public HashMap<Integer, InfoYearlyReport> monthsData = new HashMap<>();
    public int sumExpenses;
    public int sumIncome;

    public YearlyReport(int year) {
        this.year = year;
    }

    public void calculationYearlyReport() {
        String yearPath = "resources/y." + year + ".csv";
        String content = ReadFile.ReadFileContentsOrNull(yearPath);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int sum = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            if (!monthsData.containsKey(month)) {
                monthsData.put(month, new InfoYearlyReport(month));
            }
            InfoYearlyReport oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += sum;
            } else {
                oneMonthData.incomes += sum;
            }
        }

        for (InfoYearlyReport oneMonthData : monthsData.values()) {
            sumExpenses += oneMonthData.expenses;
            sumIncome += oneMonthData.incomes;
        }
    }

    public String report() {
        String content = null;
        for (int y = 1; y <= YEAR_COUNT; y++) {
            String yearPath = "resources/y.202" + y + ".csv";
            content = ReadFile.ReadFileContentsOrNull(yearPath);
        }
        return content;
    }

    public void sumProfit() {
        for (InfoYearlyReport oneMonthData : monthsData.values()) {
            oneMonthData.profit = oneMonthData.incomes - oneMonthData.expenses;
        }
    }

    void printInfoYearlyReport() {
        InfoYearlyReport oneMonthData = null;
        System.out.println("Отчёт за " + year + " год: ");
        sumProfit();
        for (int month = 1; month <= 3; month++) {
            oneMonthData = monthsData.get(month);
            System.out.println("Прибыль за " + MonthlyReport.nameMonth[oneMonthData.month - 1] + " составила: " + oneMonthData.profit);
        }
        System.out.println("Средний расход за все месяцы в году составил: " + sumExpenses / 12);
        System.out.println("Средний доход за все месяцы в году составил: " + sumIncome / 12);
        System.out.println();
    }
}



