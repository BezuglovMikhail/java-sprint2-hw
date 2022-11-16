import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public static final int MONTHS_COUNT = 12;
    HashMap<Integer, InfoMonthlyReport> monthsReportData = new HashMap<>();
    int month;

    static String[] nameMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    public MonthlyReport(int month) {
        this.month = month;
    }

    public void allMonthReport() {
        for (int month = 1; month <= MONTHS_COUNT; month++) {
            InfoMonthlyReport oneMonthData = new InfoMonthlyReport();
            String monthPath = "resources/m.20210" + month + ".csv";
            String content = ReadFile.ReadFileContentsOrNull(monthPath);
            ArrayList<Integer> sumIncomeMonth;
            ArrayList<Integer> sumExpenseMonth;
            int sumIncome = 0;
            int sumExpense = 0;
            int maxIncomeItem = 0;
            String nameMaxIncomeItem = "";
            int maxExpenseItem = 0;
            String nameMaxExpenseItem = "";

            if (content == null) {
                //System.out.println("Добавьте отчет за " + nameMonth[month - 1]);
                //System.out.println();
            } else {
                oneMonthData.monthContents = content;
                monthsReportData.put(month, oneMonthData);
            }

            if (oneMonthData.monthContents != null) {
                String[] lines = oneMonthData.monthContents.split("\r?\n");
                sumIncomeMonth = new ArrayList<>();
                ArrayList<String> nameIncome = new ArrayList<>();
                sumExpenseMonth = new ArrayList<>();
                ArrayList<String> nameExpense = new ArrayList<>();

                for (int i = 1; i < lines.length; i++) {
                    String line = lines[i];
                    String[] parts = line.split(",");
                    String itemName = parts[0];
                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int sumOfOne = Integer.parseInt(parts[3]);

                    if (!isExpense) {
                        int allIncomeItem = sumOfOne * quantity;
                        sumIncomeMonth.add(allIncomeItem);
                        nameIncome.add(itemName);
                        sumIncome += allIncomeItem;
                    }
                    if (isExpense) {
                        int allExpenseItem = sumOfOne * quantity;
                        sumExpenseMonth.add(allExpenseItem);
                        nameExpense.add(itemName);
                        sumExpense += allExpenseItem;
                    }
                }

                for (int income : sumIncomeMonth) {
                    if (income > maxIncomeItem) {
                        maxIncomeItem = income;
                        nameMaxIncomeItem = nameIncome.get(sumIncomeMonth.indexOf(maxIncomeItem));
                    }
                }

                for (int expanse : sumExpenseMonth) {
                    if (expanse > maxExpenseItem) {
                        maxExpenseItem = expanse;
                        nameMaxExpenseItem = nameExpense.get(sumExpenseMonth.indexOf(maxExpenseItem));
                    }
                }
                oneMonthData.sumIncomeMonth = sumIncome;                                                        // Артём, спасибо! С отладчиком, гораздо проще отлавливать ошибки!
                oneMonthData.sumExpenseMonth = sumExpense;
                oneMonthData.maxIncomeMonth = maxIncomeItem;
                oneMonthData.maxExpenseMonth = maxExpenseItem;
                oneMonthData.nameMaxIncomeMonth = nameMaxIncomeItem;
                oneMonthData.nameMaxExpenseMonth = nameMaxExpenseItem;
                monthsReportData.put(month, oneMonthData);
            }
        }
    }

    public void printReport() {
        for (int month = 1; month <= monthsReportData.size(); month++) {  //monthsReportData.size()

            if (monthsReportData == null) {
            } else {
                String content = monthsReportData.get(month).monthContents;
                System.out.println(content);
            }
        }
    }

    public void printInfoMonthlyReport() {
        for (int month = 1; month <= monthsReportData.size(); month++) {
            int maxIncomeMonth = monthsReportData.get(month).maxIncomeMonth;
            String nameMaxIncome = monthsReportData.get(month).nameMaxIncomeMonth;
            int maxExpense = monthsReportData.get(month).maxExpenseMonth;
            String nameMaxExpense = monthsReportData.get(month).nameMaxExpenseMonth;

            System.out.println(nameMonth[month - 1]);
            System.out.println("Самый прибыльный товар: " + nameMaxIncome + " - продан на сумму: " + maxIncomeMonth);
            System.out.println("Самая большая трата: " + nameMaxExpense + " - потрачено: " + maxExpense);
            System.out.println();
        }
    }
}


