public class ReconciliationReport {

    public YearlyReport reportYear;
    public MonthlyReport reportMonth;

    ReconciliationReport(MonthlyReport monthlyReport, YearlyReport reportYear) {
        this.reportMonth = monthlyReport;
        this.reportYear = reportYear;

    }

    public void reconciliationReport() {
        String yearContents = String.valueOf(reportYear.report());
        String allMonthContents = String.valueOf(reportMonth.monthPathContent());

        reportMonth.sumIncomeExpanse();

        if (yearContents == null || allMonthContents == null) {
            System.out.println("Отчёты не загружены. Загрузите отчёты!");
        } else {
            for (int month = 1; month <= MonthlyReport.MONTHS_COUNT; month++)
                if (reportMonth.monthContents.get(month) == null) {
                    //System.out.println("Загрузите отчет за " + reportMonth.nameMonth[month - 1] + "!");
                } else if ((reportYear.monthsData.get(month).incomes != reportMonth.incomeMonth.get(month)) || (reportYear.monthsData.get(month).expenses != reportMonth.expanseMonth.get(month))) {
                    System.out.println("Ошибка в отчете за " + reportMonth.nameMonth[month - 1] + "!");
                }

            System.out.println("Ошибок не обнаружено! Сверка успешно завершина!");
        }
    }
}
