
public class ReconciliationReport {
    public YearlyReport reportYear = new YearlyReport(2021);
    public MonthlyReport reportMonth = new MonthlyReport(1);

    public void reconciliationReport() {
        reportMonth.allMonthReport();
        reportYear.calculationYearlyReport();

        boolean yearContentsTrue = reportYear.report() == null;
        boolean monthContentsTrue = false;
        boolean comparingExpense = false;
        boolean comparingIncome = false;

        for (int month = 1; month <= reportMonth.monthsReportData.size(); month++) {
            String contentMonth = String.valueOf(reportMonth.monthsReportData.get(month).monthContents);

            if (contentMonth == null) {
                monthContentsTrue = true;
            } else {
                monthContentsTrue = false;
            }
        }

        if (yearContentsTrue || monthContentsTrue) {
            System.out.println("Отчёты не загружены. Загрузите отчёты!");
        } else {
            for (int month = 1; month <= reportMonth.monthsReportData.size(); month++) {
                String monthContents = String.valueOf(reportMonth.monthsReportData.get(month).monthContents);
                if (monthContents == null) {
                    System.out.println("Загрузите отчет за " + reportMonth.nameMonth[month - 1] + "!");
                }
                int incomeYearlyReport = reportYear.monthsData.get(month).incomes;                                  // эти переменные можно было не использовать, но мне кажется так код выглядит аккуратнее.
                int incomeMonthlyReport = reportMonth.monthsReportData.get(month).sumIncomeMonth;
                int expensesYearlyReport = reportYear.monthsData.get(month).expenses;
                int expensesMonthlyReport = reportMonth.monthsReportData.get(month).sumExpenseMonth;
                comparingExpense = expensesYearlyReport != expensesMonthlyReport;
                comparingIncome = incomeYearlyReport != incomeMonthlyReport;

                if (comparingIncome || comparingExpense) {
                    System.out.println("Ошибка в отчете за " + reportMonth.nameMonth[month - 1] + "!");
                    System.out.println();
                }

            }
            if (!(comparingIncome || comparingExpense)) {
                System.out.println("Ошибок не обнаружено! Сверка успешно завершина!");
            }
        }
    }
}

