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
                    case 1 -> printAndAdd(scanner, calendar);
                    case 2 -> printAndDelete(scanner, calendar);
                    case 3 -> printAndDeleteEntryFromWholeDay(scanner, calendar);
                    case 4 -> printAndCheckFromDay(scanner, calendar);
                    case 5 -> printCheckEntryFromDayWithExtra(scanner, calendar);
                    case 6 -> printCheckEntryFromDayAndTime(scanner, calendar);
                    case 7 -> printCheckMeetingFromDayAndBetweenTimes(scanner, calendar);
                    case 8 -> printCheckEntryFromDayExtraAndTime(scanner, calendar);
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
                1 - Add new entry
                2 - Delete entry
                3 - Delete entries from whole day
                4 - Get all entries by day
                5 - Get all entries by day and extra
                6 - Get all entries by day and after start hour
                7 - Get all meetings by day and between start and end hour
                8 - Get all entries by day, after start hour and extra
                9 - Exit
                """);
    }

    private static void printAndAdd(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("1 - Meeting\n2 - Task");
        int option = scanner.nextInt();
        System.out.println("Add a day");
        int day = scanner.nextInt();
        System.out.println("Add description");
        String desc = scanner.next();
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.println("Add end date, for example 10:00:00");
        LocalTime end = LocalTime.parse(scanner.next());
        switch (option) {
            case 1 -> {
                System.out.println("Add priority (1(LOW)-3(HIGH))");
                Priority priority = Priority.transfromPriority(scanner.nextInt());
                calendar.addMeeting(day, desc, start, end, priority);
            }
            case 2 -> {
                System.out.println("Add status (1-4)");
                Status status = Status.transfromStatus(scanner.nextInt());
                calendar.addTask(day, desc, start, end, status);
            }
            default -> System.out.println("Invalid option!");
        }
    }

    private static void printAndDelete(Scanner scanner, Calendar calendar) {
        System.out.println("1 - Meeting\n2 - Task");
        int option = scanner.nextInt();
        System.out.println("Add a day");
        int day = scanner.nextInt();
        switch (option) {
            case 1 -> {
                printCheckMeetingFromDay(day, calendar);
                System.out.println("Add ID of meeting");
                int id = scanner.nextInt();
                calendar.deleteEntryFromDay(day, id);
            }
            case 2 -> {
                printCheckTaskFromDay(day, calendar);
                System.out.println("Add ID of task");
                int id = scanner.nextInt();
                calendar.deleteEntryFromDay(day, id);
            }
            default -> System.out.println("Invalid option!");
        }
    }

    private static void printCheckTaskFromDay(int day, Calendar calendar) {
        Predicate<AbstractEntry> func = (meeting) -> true;
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        taskPrinter(arr);
    }

    private static void printCheckMeetingFromDay(int day, Calendar calendar) {
        Predicate<AbstractEntry> func = (meeting) -> true;
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        meetingPrinter(arr);
    }

    private static void printAndDeleteEntryFromWholeDay(Scanner scanner, Calendar calendar) {
        System.out.println("Add a day");
        int day = scanner.nextInt();
        calendar.deleteEntriesFromWholeDay(day);
        System.out.println("Ok");
    }

    private static void printAndCheckFromDay(Scanner scanner, Calendar calendar) {
        System.out.println("1 - Meeting\n2 - Task");
        int option = scanner.nextInt();
        System.out.println("Add a day");
        int day = scanner.nextInt();
        switch (option) {
            case 1 -> printCheckMeetingFromDay(calendar, day);
            case 2 -> printCheckTaskFromDay(calendar, day);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void printCheckMeetingFromDay(Calendar calendar, int day) {
        Predicate<AbstractEntry> func = (meeting) -> meeting instanceof Meeting;
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        entryPrinter(arr);
    }

    private static void printCheckTaskFromDay(Calendar calendar, int day) {
        Predicate<AbstractEntry> func = (task) -> task instanceof Task;
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        entryPrinter(arr);
    }

    private static void printCheckEntryFromDayWithExtra(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("1 - Meeting\n2 - Task");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> printCheckMeetingFromDayAndPriority(scanner, calendar);
            case 2 -> printCheckTaskFromDayAndStatus(scanner, calendar);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void printCheckMeetingFromDayAndPriority(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("Number of priority (1-3)");
        Priority priority = Priority.transfromPriority(scanner.nextInt());
        Predicate<AbstractEntry> func = (meeting) ->
                meeting instanceof Meeting && ((Meeting) meeting).getPriority() == priority;
        System.out.println("Number of day");
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(scanner.nextInt(), func);
        entryPrinter(arr);
    }

    private static void printCheckTaskFromDayAndStatus(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("Number of status (1-4)");
        Status status = Status.transfromStatus(scanner.nextInt());
        Predicate<AbstractEntry> func = (task) ->
                task instanceof Task && ((Task) task).getStatus() == status;
        System.out.println("Number of day");
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(scanner.nextInt(), func);
        entryPrinter(arr);
    }

    private static void printCheckEntryFromDayAndTime(Scanner scanner, Calendar calendar) {
        System.out.println("1 - Meeting\n2 - Task");
        int option = scanner.nextInt();
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.println("Add a day");
        int day = scanner.nextInt();
        switch (option) {
            case 1 -> printCheckMeetingFromDayAndTime(start, day, calendar);
            case 2 -> printCheckTaskFromDayAndTime(start, day, calendar);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void printCheckMeetingFromDayAndTime(LocalTime start, int day, Calendar calendar) {
        Predicate<AbstractEntry> func = (meeting) -> meeting instanceof Meeting && meeting.getStartDate().isAfter(start);
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        entryPrinter(arr);
    }

    private static void printCheckTaskFromDayAndTime(LocalTime start, int day, Calendar calendar) {
        Predicate<AbstractEntry> func = (task) -> task instanceof Task && task.getStartDate().isAfter(start);
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        entryPrinter(arr);
    }

    private static void printCheckMeetingFromDayAndBetweenTimes(Scanner scanner, Calendar calendar) {
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.println("Add end date, for example 10:00:00");
        LocalTime end = LocalTime.parse(scanner.next());
        System.out.println("Number of day");
        Predicate<AbstractEntry> func = (meeting) ->
                meeting.getStartDate().isAfter(start) && meeting.getEndDate().isBefore(end);
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(scanner.nextInt(), func);
        entryPrinter(arr);
    }

    private static void printCheckEntryFromDayExtraAndTime(Scanner scanner, Calendar calendar) throws Exception {
        System.out.println("1 - Meeting\n2 - Task");
        int option = scanner.nextInt();
        System.out.println("Add start date, for example 10:00:00");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.println("Add a day");
        int day = scanner.nextInt();
        switch (option) {
            case 1 -> printCheckMeetingFromDayPriorityAndTime(scanner, calendar, start, day);
            case 2 -> printCheckTaskFromDayStatusAndTime(scanner, calendar, start, day);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void printCheckMeetingFromDayPriorityAndTime(Scanner scanner, Calendar calendar, LocalTime start, int day) throws Exception {
        System.out.println("Number of priority (1-3)");
        Priority priority = Priority.transfromPriority(scanner.nextInt());
        Predicate<AbstractEntry> func = (meeting) ->
                meeting instanceof Meeting &&
                        meeting.getStartDate().isAfter(start) && ((Meeting) meeting).getPriority() == priority;
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        entryPrinter(arr);
    }

    private static void printCheckTaskFromDayStatusAndTime(Scanner scanner, Calendar calendar, LocalTime start, int day) throws Exception {
        System.out.println("Number of status (1-4)");
        Status status = Status.transfromStatus(scanner.nextInt());
        Predicate<AbstractEntry> func = (task) ->
                task instanceof Task &&
                        task.getStartDate().isAfter(start) && ((Task) task).getStatus() == status;
        ArrayList<AbstractEntry> arr = calendar.checkEntryFromDay(day, func);
        entryPrinter(arr);
    }

    private static void taskPrinter(ArrayList<AbstractEntry> arr) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) instanceof Task) {
                output.append("ID: %s, ".formatted(i));
                output.append(arr.get(i).toString());
                output.append("\n");
            }
        }

        System.out.println(output);
    }

    private static void meetingPrinter(ArrayList<AbstractEntry> arr) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) instanceof Meeting) {
                output.append("ID: %s, ".formatted(i));
                output.append(arr.get(i).toString());
                output.append("\n");
            }
        }

        System.out.println(output);
    }

    private static void entryPrinter(ArrayList<AbstractEntry> arr) {
        for (AbstractEntry abstractEntry : arr) {
            System.out.println(abstractEntry.toString() + "\n");
        }
    }
}