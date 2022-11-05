import java.util.ArrayList;
import java.util.HashMap;

    public class MonthlyReport {
        public static final int MONTHS_COUNT = 3;
        public int month;
        public int expenses;
        public int incomes;

        HashMap<Integer, ArrayList<Integer>> Income = new HashMap<>();
        HashMap<Integer, ArrayList<String>> itemNameIncome = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> Expanse = new HashMap<>();
        HashMap<Integer, ArrayList<String>> itemNameExpanse = new HashMap<>();

        HashMap<Integer, Integer> maxIncomeItem = new HashMap<>();
        HashMap<Integer, String> maxIncomeNameItem = new HashMap<>();
        HashMap<Integer, Integer> maxExpenseItem = new HashMap<>();
        HashMap<Integer, String> maxExpenseNameItem = new HashMap<>();

        HashMap<Integer, Integer> sumIncomes = new HashMap<>();
        HashMap<Integer, Integer> sumExpenses = new HashMap<>();

        HashMap<Integer, String> monthContents = new HashMap<>();

        public MonthlyReport(int month) {
            this.month = month;
        }
        public void monthlyReportInfo() {
             HashMap<Integer, MonthlyReport> monthsReportData = new HashMap<>();
             monthContents = new HashMap<>();

            for (int month = 1; month <= MONTHS_COUNT; month++) {
                String monthPath = "resources/m.20210" + month + ".csv";
                String content = ReadFile.ReadFileContentsOrNull(monthPath);
                monthContents.put(month, content);
                String[] lines = content.split("\r?\n");
                ArrayList<Integer> sumIncomeMonth = new ArrayList<>();
                ArrayList<String> nameIncome = new ArrayList<>();
                ArrayList<Integer> sumExpanseMonth = new ArrayList<>();
                ArrayList<String> nameExpanse = new ArrayList<>();
                int expensesMonth = 0;
                int incomesMonth = 0;

                for (int i = 1; i < lines.length; i++) {
                    String line = lines[i];
                    String[] parts = line.split(",");
                    String itemName = parts[0];
                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int sumOfOne = Integer.parseInt(parts[3]);

                    if (!monthsReportData.containsKey(month)) {
                        monthsReportData.put(month, new MonthlyReport(month));
                    }
                    if (!isExpense) {
                        int allIncomeItem = sumOfOne * quantity;
                        incomesMonth += allIncomeItem;
                        sumIncomeMonth.add(allIncomeItem);
                        nameIncome.add(itemName);
                    }
                    if (isExpense) {
                        int allExpanseItem = sumOfOne * quantity;
                        expensesMonth += allExpanseItem;
                        sumExpanseMonth.add(allExpanseItem);
                        nameExpanse.add(itemName);
                    }
                    Income.put(month, sumIncomeMonth);
                    itemNameIncome.put(month, nameIncome);
                    Expanse.put(month, sumExpanseMonth);
                    itemNameExpanse.put(month, nameExpanse);
                }
                sumIncomes.put(month, incomesMonth);
                sumExpenses.put(month, expensesMonth);
            }
        }

        public void allMonthReport() {
            monthlyReportInfo();
            for (int m = 1; m <= MONTHS_COUNT; m++) {
                System.out.println(monthContents.get(month));
            }
        }

        public void findMaxIncomeItem() {
            maxIncomeItem = new HashMap<>();
            maxIncomeNameItem = new HashMap<>();

            for (Integer month : Income.keySet()) {
                ArrayList<Integer> sumIncomeOneMonth = Income.get(month);
                ArrayList<String> nameSumIncomeOneMonth = itemNameIncome.get(month);
                int maxIncome = 0;
                String nameMaxIncome = "";

                for (Integer income : sumIncomeOneMonth) {
                    if (income > maxIncome) {
                        maxIncome = income;
                        nameMaxIncome = nameSumIncomeOneMonth.get(sumIncomeOneMonth.indexOf(maxIncome));
                    }
                }
                maxIncomeItem.put(month, maxIncome);
                maxIncomeNameItem.put(month, nameMaxIncome);
            }
       }

       public void findMaxExpenseItem() {
            maxExpenseItem = new HashMap<>();
            maxExpenseNameItem = new HashMap<>();

            for (Integer month : Expanse.keySet()) {
                ArrayList<Integer> sumExpanseOneMonth = Expanse.get(month);
                ArrayList<String> nameSumExpanseOneMonth = itemNameExpanse.get(month);

                int maxExpense = 0;
                String nameMaxExpense = "";
                for (Integer income : sumExpanseOneMonth) {
                    if (income > maxExpense) {
                        maxExpense = income;
                        nameMaxExpense = nameSumExpanseOneMonth.get(sumExpanseOneMonth.indexOf(maxExpense));
                        }
                }
                maxExpenseItem.put(month, maxExpense);
                maxExpenseNameItem.put(month, nameMaxExpense);
            }
       }
       public void printInfoMonthlyReport() {
            monthlyReportInfo();
            findMaxIncomeItem();
            findMaxExpenseItem();

            for (Integer month : maxIncomeItem.keySet()) {
                if (month == 1) {
                    System.out.println("Январь");
                }
                if (month == 2) {
                    System.out.println("Февраль");
                }
                if (month == 3) {
                    System.out.println("Март");
                }
                System.out.println("Самый прибыльный товар в этом месяце: " + maxIncomeNameItem.get(month) + " сумма продажи: " + maxIncomeItem.get(month));
                System.out.println("Самая большая трата в этом месяце: " + maxExpenseNameItem.get(month) + " сумма траты: " + maxExpenseItem.get(month));
                System.out.println();
            }
        }
    }
