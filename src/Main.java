import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = 2021;

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                MonthlyReport reportMonthOne = new MonthlyReport();
                reportMonthOne.monthPathContent();
                reportMonthOne.allMonthReport();
            } else if (command == 2) {
                YearlyReport reportYear = new YearlyReport(year);
                System.out.println(reportYear.report());
            } else if (command == 3) {
                MonthlyReport reportMonthOne = new MonthlyReport();
                YearlyReport reportYear = new YearlyReport(year);
                ReconciliationReport reconciliationReport = new ReconciliationReport(reportMonthOne, reportYear);
                reconciliationReport.reconciliationReport();
            } else if (command == 4) {
                MonthlyReport reportMonthOne = new MonthlyReport();
                reportMonthOne.monthPathContent();
                reportMonthOne.infoMonthlyReport();
                reportMonthOne.monthPathContent();
            } else if (command == 5) {
                YearlyReport reportYear = new YearlyReport(year);
                reportYear.printInfoYearlyReport();
            } else if (command == 0) {
                break;
            } else {
                System.out.println("Такой команды пока нет(");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}


