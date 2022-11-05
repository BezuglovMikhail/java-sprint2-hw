
    public class ReconciliationReport {

        public static void reconciliationReport() {
            String yearPath = "resources/y.2021.csv";
            int year = 2021;
            YearlyReport reportYear = new YearlyReport(year, yearPath);
            MonthlyReport reportMonthTree = new MonthlyReport(1);
            reportMonthTree.monthlyReportInfo();

            String yearContents = String.valueOf(reportYear.report());
            String allMonthContents = String.valueOf(reportMonthTree.monthContents);

            boolean yearContentsFalls = yearContents.equals(null);
            boolean monthContentsFalls = allMonthContents.equals(null);


            if ((yearContentsFalls || monthContentsFalls)) {
                System.out.println("Отчёты не загружены. Загрузите отчёты!");
                // break;
            } else {

                for (int month = 1; month <= MonthlyReport.MONTHS_COUNT; month++) {
                    String nameMonth;

                    if (reportYear.monthsData.get(month).incomes != reportMonthTree.sumIncomes.get(month)) {
                        if (month == 1) {
                            nameMonth = "Январь";
                        } else if (month == 2) {
                            nameMonth = "Февраль";
                        } else if (month == 3) {
                            nameMonth = "Март";
                            System.out.println("Ошибка в " + nameMonth + " Проверь отчет за " + nameMonth + "!");
                        }
                    }
                }
                System.out.println("Ошибок не обнаружено! Сверка успешно завершина!");
            }
        }
    }
