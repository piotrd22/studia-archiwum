import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = new Calendar();
        int option;
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            option = scanner.nextInt();
            try {
                switch (option) {
                    case 1 -> System.out.println(calendar.checkMeetingFromDay(Day.transformDay(scanner.nextInt())));
                    case 2 -> System.out.println(calendar.checkMeetingFromDayAndPriority(Day.transformDay(scanner.nextInt()),
                            Priority.transfromPriority(scanner.nextInt())));
                    case 3 -> printAndAddToCalendar(calendar, scanner);
                    case 4 -> printAndDeleteMeeting(calendar, scanner);
                    case 5 -> calendar.deleteMeetingFromWholeDay(Day.transformDay(scanner.nextInt()));
                    case 6 -> isRunning = false;
                    default -> System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                Menu:\s
                1 - Get meetings by day (1-7)
                2 - Get meeting by day (1-7) and priority (1(LOW)-3(HIGH))
                3 - Add new meeting
                4 - Delete meeting
                5 - Delete meeting by day (1-7)
                6 - Exit
                """);
    }

    private static void printAndAddToCalendar(Calendar calendar, Scanner scanner) throws Exception {
        System.out.println("Add a day (1-7)");
        Day day = Day.transformDay(scanner.nextInt());
        System.out.println("Add description");
        String desc = scanner.next();
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.println("Add end date, for example 10:00:00");
        LocalTime end = LocalTime.parse(scanner.next());
        System.out.println("Add priority (1(LOW)-3(HIGH))");
        Priority priority = Priority.transfromPriority(scanner.nextInt());

        calendar.addMeeting(day, desc, start, end, priority);
    }

    private static void printAndDeleteMeeting(Calendar calendar, Scanner scanner) throws Exception {
        System.out.println("Add a day (1-7)");
        Day day = Day.transformDay(scanner.nextInt());
        System.out.println("Add ID of meeting (seen in option 1)");
        int id = scanner.nextInt();

        calendar.deleteMeetingFromDay(day, id);
    }
}