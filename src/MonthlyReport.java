import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public static final int MONTHS_COUNT = 12;
    public int month;
    public int expenses;
    public int incomes;

    String[] nameMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    HashMap<Integer, Integer> incomeMonth = new HashMap<>();
    HashMap<Integer, Integer> expanseMonth = new HashMap<>();
    HashMap<Integer, String> monthContents = new HashMap<>();

    public MonthlyReport() {
    }

    public MonthlyReport(int month) {
        this.month = month;
    }

    public HashMap<Integer, String> monthPathContent() {
        HashMap<Integer, MonthlyReport> monthsReportData = new HashMap<>();
        monthContents = new HashMap<>();

        for (int month = 1; month <= MONTHS_COUNT; month++) {
            String monthPath = "resources/m.20210" + month + ".csv";

            String content = ReadFile.ReadFileContentsOrNull(monthPath);
            if (content == null) {

            } else {
                monthContents.put(month, content);
            }
        }
        return monthContents;
    }

    public void allMonthReport() {
        for (int month = 1; month <= MONTHS_COUNT; month++) {
            if (monthContents.get(month) == null) {
            } else {
                System.out.println(monthContents.get(month));
            }
        }
    }

    public void infoMonthlyReport() {
        incomeMonth = new HashMap<>();
        expanseMonth = new HashMap<>();
        ArrayList<Integer> sumIncomeMonth;
        ArrayList<Integer> sumExpenseMonth;
        int sumIncome = 0;
        int sumExpense = 0;

        for (int month = 1; month <= MONTHS_COUNT; month++) {

            if (monthContents.get(month) != null) {

                String[] lines = monthContents.get(month).split("\r?\n");
                sumIncomeMonth = new ArrayList<>();
                ArrayList<String> nameIncome = new ArrayList<>();
                sumExpenseMonth = new ArrayList<>();
                ArrayList<String> nameExpense = new ArrayList<>();
                int maxIncomeItem = 0;
                String nameMaxIncomeItem = "";
                int maxExpenseItem = 0;
                String nameMaxExpenseItem = "";

                for (int i = 1; i < lines.length; i++) {
                    String line = lines[i];
                    String[] parts = line.split(",");
                    String itemName = parts[0];
                    sumIncome = 0;
                    sumExpense = 0;

                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int sumOfOne = Integer.parseInt(parts[3]);

                    if (!isExpense) {
                        int allIncomeItem = sumOfOne * quantity;
                        sumIncomeMonth.add(allIncomeItem);
                        nameIncome.add(itemName);
                    }
                    if (isExpense) {
                        int allExpenseItem = sumOfOne * quantity;

                        sumExpenseMonth.add(allExpenseItem);
                        nameExpense.add(itemName);
                    }
                }
                for (int income : sumIncomeMonth) {
                    maxIncomeItem = 0;
                    nameMaxIncomeItem = "";
                    if (income > maxIncomeItem) {
                        maxIncomeItem = income;
                        nameMaxIncomeItem = nameIncome.get(sumIncomeMonth.indexOf(maxIncomeItem));
                    }
                }
                for (int expanse : sumExpenseMonth) {
                    maxExpenseItem = 0;
                    nameMaxExpenseItem = "";
                    if (expanse > maxExpenseItem) {
                        maxExpenseItem = expanse;
                        nameMaxExpenseItem = nameExpense.get(sumExpenseMonth.indexOf(maxExpenseItem));
                    }
                }
                System.out.println(nameMonth[month - 1]);
                System.out.println("Самый прибыльный товар: " + nameMaxIncomeItem + " - продан на сумму: " + maxIncomeItem);
                System.out.println("Самая большая трата: " + nameMaxExpenseItem + " - потрачено: " + maxExpenseItem);
                System.out.println();
            }
        }
    }

    public void sumIncomeExpanse() {                                //Артем, спасибо тебе за возможность понять материал глубже!
        incomeMonth = new HashMap<>();                              //На сегодня это моё лучшее решение, Я застрял и не могу сдвинуться.
        expanseMonth = new HashMap<>();                             //Жду обратную связь!
        ArrayList<Integer> sumIncomeMonth;
        ArrayList<Integer> sumExpenseMonth;
        int sumIncome = 0;
        int sumExpense = 0;

        for (int month = 1; month <= MONTHS_COUNT; month++) {

            if (monthContents.get(month) != null) {

                String[] lines = monthContents.get(month).split("\r?\n");
                sumIncomeMonth = new ArrayList<>();
                sumExpenseMonth = new ArrayList<>();

                for (int i = 1; i < lines.length; i++) {
                    String line = lines[i];
                    String[] parts = line.split(",");
                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int sumOfOne = Integer.parseInt(parts[3]);
                    sumIncome = 0;
                    sumExpense = 0;

                    if (!isExpense) {
                        int allIncomeItem = sumOfOne * quantity;
                        sumIncomeMonth.add(allIncomeItem);
                    }
                    if (isExpense) {
                        int allExpenseItem = sumOfOne * quantity;
                        sumExpenseMonth.add(allExpenseItem);
                    }

                    for (int income : sumIncomeMonth) {
                        sumIncome += income;
                    }
                    for (int expanse : sumExpenseMonth) {
                        sumExpense += expanse;
                    }
                }
            }
            incomeMonth.put(month, sumIncome);
            expanseMonth.put(month, sumExpense);
        }
    }
}


