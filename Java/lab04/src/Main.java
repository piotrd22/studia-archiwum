import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

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
                    case 1 -> printAndAddToCalendar(scanner, calendar);
                    case 2 -> printAndDeleteMeeting(scanner, calendar);
                    case 3 -> printAndDeleteMeetingFromWholeDay(scanner, calendar);
                    case 4 -> printCheckMeetingFromDay(scanner, calendar);
                    case 5 -> printCheckMeetingFromDayAndPriority(scanner, calendar);
                    case 6 -> printCheckMeetingFromDayAndTime(scanner, calendar);
                    case 7 -> printCheckMeetingFromDayAndBetweenTimes(scanner, calendar);
                    case 8 -> printCheckMeetingFromDayPriorityAndTime(scanner, calendar);
                    case 9 -> isRunning = false;
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
                1 - Add new meeting
                2 - Delete meeting
                3 - Delete meetings from whole day
                4 - Get all meetings by day
                5 - Get all meetings by day and priority (1(LOW)-3(HIGH))
                6 - Get all meetings by day and after start hour
                7 - Get all meetings by day and between start and end hour
                8 - Get all meetings by day, after start hour and priority
                9 - Exit
                """);
    }

    private static void printAndAddToCalendar(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("Add a day");
        int day = scanner.nextInt();
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

    private static void printAndDeleteMeeting(Scanner scanner, Calendar calendar) {
        System.out.println("Add a day");
        int day = scanner.nextInt();
        printCheckMeetingFromDay(day, calendar);
        System.out.println("Add ID of meeting");
        int id = scanner.nextInt();
        calendar.deleteMeetingFromDay(day, id);
        System.out.println("Ok");
    }

    private static void printCheckMeetingFromDay(int day, Calendar calendar) {
        Predicate<Meeting> func = (meeting) -> true;
        ArrayList<Meeting> arr = calendar.checkMeetingFromDay(day, func);
        meetingsPrinter(arr);
    }

    private static void printAndDeleteMeetingFromWholeDay(Scanner scanner, Calendar calendar) {
        System.out.println("Add a day");
        int day = scanner.nextInt();
        calendar.deleteMeetingFromWholeDay(day);
        System.out.println("Ok");
    }

    private static void printCheckMeetingFromDay(Scanner scanner, Calendar calendar) {
        System.out.println("Add a day");
        Predicate<Meeting> func = (meeting) -> true;
        ArrayList<Meeting> arr = calendar.checkMeetingFromDay(scanner.nextInt(), func);
        meetingsPrinter(arr);
    }

    private static void printCheckMeetingFromDayAndPriority(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("Number of priority (1-3)");
        Priority priority = Priority.transfromPriority(scanner.nextInt());
        Predicate<Meeting> func = (meeting) -> meeting.getPriority() == priority;
        System.out.println("Number of day");
        ArrayList<Meeting> arr = calendar.checkMeetingFromDay(scanner.nextInt(), func);
        meetingsPrinter(arr);
    }

    private static void printCheckMeetingFromDayAndTime(Scanner scanner, Calendar calendar) {
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        Predicate<Meeting> func = (meeting) -> meeting.getStartDate().isAfter(start);
        System.out.println("Number of day");
        ArrayList<Meeting> arr = calendar.checkMeetingFromDay(scanner.nextInt(), func);
        meetingsPrinter(arr);
    }

    private static void printCheckMeetingFromDayAndBetweenTimes(Scanner scanner, Calendar calendar) {
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.println("Add end date, for example 10:00:00");
        LocalTime end = LocalTime.parse(scanner.next());
        System.out.println("Number of day");
        Predicate<Meeting> func = (meeting) ->
                meeting.getStartDate().isAfter(start) && meeting.getEndDate().isBefore(end);
        ArrayList<Meeting> arr = calendar.checkMeetingFromDay(scanner.nextInt(), func);
        meetingsPrinter(arr);
    }

    private static void printCheckMeetingFromDayPriorityAndTime(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.println("Number of priority (1-3)");
        Priority priority = Priority.transfromPriority(scanner.nextInt());
        System.out.println("Number of day");
        Predicate<Meeting> func = (meeting) ->
                meeting.getStartDate().isAfter(start) && meeting.getPriority() == priority;
        ArrayList<Meeting> arr = calendar.checkMeetingFromDay(scanner.nextInt(), func);
        meetingsPrinter(arr);
    }

    private static void meetingsPrinter(ArrayList<Meeting> arr) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arr.size(); i++) {
            output.append("ID: %s, ".formatted(i));
            output.append(arr.get(i).toString());
            output.append("\n");
        }

        System.out.println(output);
    }
}