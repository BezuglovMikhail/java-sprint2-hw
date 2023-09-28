# java-sprint2-hw
## Это репозиторий проекта "Счетчик калорий".
Приложение имеет консольный интерфейс и **умеет**
1. Считывать месячные и годовые отчеты из файлов и переводить данные в объекты приложения. 
2. Выполнять сверку данных по месячным и годовым отчетам.
3. Выводить информацию о месячных и годовых отчетах.

Приложение написано на Java. Пример кода:
```java
public class Main {

    public static void main(String[] args) {
        int year = 2021;
        int month = 1;
        MonthlyReport monthlyReport = new MonthlyReport(month);
        YearlyReport yearlyReport = new YearlyReport(year);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReport.allMonthReport();
                monthlyReport.printReport();
            } else if (command == 2) {
                yearlyReport.calculationYearlyReport();
                yearlyReport.report();
                System.out.println(yearlyReport.monthsData.get(month).yearlyContent);
            } else if (command == 3) {
                ReconciliationReport reconciliationReport = new ReconciliationReport();
                reconciliationReport.reconciliationReport(yearlyReport, monthlyReport);
            } else if (command == 4) {
                monthlyReport.printInfoMonthlyReport();
            } else if (command == 5) {
                yearlyReport.printInfoYearlyReport();
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
```
------
О том, как научиться создавать такие приложения, можно узнать в [Яндекс-Практикуме](https://practicum.yandex.ru/java-developer/ "Тут учат Java!")