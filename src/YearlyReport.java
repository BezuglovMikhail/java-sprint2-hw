import java.util.HashMap;
import java.util.ArrayList;

public class YearlyReport {
    public int year;
    public final int YEAR_COUNT = 1;
    public HashMap<Integer, MonthlyReport> monthsData = new HashMap<>();
    public int sumExpenses;
    public int sumIncome;

    public YearlyReport(int year, String path) {
        this.year = year;

        String content = ReadFile.ReadFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int sum = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

           if (!monthsData.containsKey(month)) {
                monthsData.put(month, new MonthlyReport(month));
            }
            MonthlyReport oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += sum;
            } else {
                oneMonthData.incomes += sum;
            }
        }

        for (MonthlyReport oneMonthData : monthsData.values()) {
            sumExpenses += oneMonthData.expenses;
            sumIncome += oneMonthData.incomes;
        }
     }

    void printInfoYearlyReport() {
        System.out.println("Отчёт за " + year + " год: ");
        sumProfit();
        System.out.println("Средний расход за все месяцы в году составил: " + sumIncome / 12);
        System.out.println("Средний доход за все месяцы в году составил: " +  sumExpenses / 12);
        System.out.println();
        }

    public ArrayList<String> report() {
        ArrayList<String> yearContents = new ArrayList<>();
        for (int y = 1; y <= YEAR_COUNT; y++) {
            String yearPath = "resources/y.202" + y + ".csv";
            String content = ReadFile.ReadFileContentsOrNull(yearPath);
            yearContents.add(content);
        }
        return yearContents;
    }

    public void sumProfit() {
        int profit;

        for (MonthlyReport oneMonthData : monthsData.values()) {
            profit = oneMonthData.incomes - oneMonthData.expenses;
            String nameMonth = "";

            if (oneMonthData.month == 1) {
                nameMonth = "Январь";
            } else if (oneMonthData.month == 2) {
                nameMonth = "Февраль";
            } else if (oneMonthData.month == 3) {
                nameMonth = "Март";
            }

            System.out.println("Прибыль за " + nameMonth + " составила: " + profit); /// добавить месяц.
        }
    }
}



