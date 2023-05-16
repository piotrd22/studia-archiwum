import java.time.LocalTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Calendar {
    private ArrayList<ArrayList<AbstractEntry>> calendar;

    public Calendar() {
        this(7);
    }

    public Calendar(int numberOfDays) {
        calendar = new ArrayList<>(numberOfDays);
        for (int i = 0; i < numberOfDays; i++) {
            calendar.add(new ArrayList<>());
        }
    }

    public void addMeeting(int day, String desc, LocalTime startTime, LocalTime endTime, Priority priority) throws Exception {
        Meeting meeting = new Meeting(desc, startTime, endTime, priority);
        ArrayList<AbstractEntry> meetings = calendar.get(day - 1);
        meetings.add(meeting);
    }

    public void addTask(int day, String desc, LocalTime startTime, LocalTime endTime, Status status) throws Exception {
        Task task = new Task(desc, startTime, endTime, status);
        ArrayList<AbstractEntry> meetings = calendar.get(day - 1);
        meetings.add(task);
    }

    public void deleteEntryFromDay(int day, int id) {
        ArrayList<AbstractEntry> meetings = calendar.get(day - 1);
        meetings.remove(id);
    }

    public void deleteEntriesFromWholeDay(int day) {
        calendar.get(day - 1).clear();
    }

    public ArrayList<AbstractEntry> checkEntryFromDay(int day, Predicate<AbstractEntry> func) {
        ArrayList<AbstractEntry> dayOfMeetings = calendar.get(day - 1);
        ArrayList<AbstractEntry> output = new ArrayList<>();

        for (AbstractEntry meeting : dayOfMeetings) {
            if (func.test(meeting)) {
                output.add(meeting);
            }
        }

        return output;
    }
}
