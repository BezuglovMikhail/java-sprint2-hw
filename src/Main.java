
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = 2021;
        int month = 1;
        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                MonthlyReport reportMonthOne = new MonthlyReport(month);
                reportMonthOne.allMonthReport();
                reportMonthOne.printReport();
            } else if (command == 2) {
                YearlyReport yearlyReport = new YearlyReport(year);
                yearlyReport.calculationYearlyReport();
                System.out.println(yearlyReport.report());
            } else if (command == 3) {
                ReconciliationReport reconciliationReport = new ReconciliationReport();
                reconciliationReport.reconciliationReport();
            } else if (command == 4) {
                MonthlyReport reportMonthOne = new MonthlyReport(month);
                reportMonthOne.allMonthReport();
                reportMonthOne.printInfoMonthlyReport();
            } else if (command == 5) {
                YearlyReport reportYear = new YearlyReport(year);
                reportYear.calculationYearlyReport();
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


