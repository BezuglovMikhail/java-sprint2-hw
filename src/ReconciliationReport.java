
public class ReconciliationReport {

    public void reconciliationReport(YearlyReport yearlyReport, MonthlyReport monthlyReport) {

        boolean comparingExpense = false;
        boolean comparingIncome = false;

        if (monthlyReport.monthsReportData.size() == 0 || yearlyReport.monthsData.size() == 0) {
            System.out.println("Отчёты не загружены. Загрузите отчёты!");

        } else {
            for (int month = 1; month <= monthlyReport.monthsReportData.size(); month++) {
                String monthContents = String.valueOf(monthlyReport.monthsReportData.get(month).monthContents);
                if (monthContents == null) {
                    System.out.println("Загрузите отчет за " + MonthlyReport.nameMonth[month - 1] + "!");
                }
                int incomeYearlyReport = yearlyReport.monthsData.get(month).incomes;
                int incomeMonthlyReport = monthlyReport.monthsReportData.get(month).sumIncomeMonth;
                int expensesYearlyReport = yearlyReport.monthsData.get(month).expenses;
                int expensesMonthlyReport = monthlyReport.monthsReportData.get(month).sumExpenseMonth;
                comparingExpense = expensesYearlyReport != expensesMonthlyReport;
                comparingIncome = incomeYearlyReport != incomeMonthlyReport;

                if (comparingIncome || comparingExpense) {
                    System.out.println("Ошибка в отчете за " + MonthlyReport.nameMonth[month - 1] + "!");
                    System.out.println();
                }
            }
            if (!(comparingIncome || comparingExpense)) {
                System.out.println("Ошибок не обнаружено! Сверка успешно завершена!");
            }
        }
    }
}
