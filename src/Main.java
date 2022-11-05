import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String yearPath = "resources/y.2021.csv";
        int year = 2021;
        YearlyReport reportYear = new YearlyReport(year, yearPath);
        MonthlyReport reportMonthOne = new MonthlyReport(1);

        while (true) {

            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                reportMonthOne.allMonthReport();
            }
            else if (command == 2) {
               System.out.println(reportYear.report());
            }
            else if (command == 3) {
                ReconciliationReport.reconciliationReport();
            }
            else if (command == 4) {
                reportMonthOne.printInfoMonthlyReport();
            }
            else if (command == 5) {
                reportYear.printInfoYearlyReport();
            }
            else if (command == 0) {
                break;
            }
            else {
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


