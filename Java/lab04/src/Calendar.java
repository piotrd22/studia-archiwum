import java.time.LocalTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Calendar {
    private ArrayList<ArrayList<Meeting>> calendar;

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
        ArrayList<Meeting> meetings = calendar.get(day - 1);
        meetings.add(meeting);
    }

    public void deleteMeetingFromDay(int day, int id) {
        ArrayList<Meeting> meetings = calendar.get(day - 1);
        meetings.remove(id);
    }

    public void deleteMeetingFromWholeDay(int day) {
        calendar.get(day - 1).clear();
    }

    public ArrayList<Meeting> checkMeetingFromDay(int day, Predicate<Meeting> func) {
        ArrayList<Meeting> dayOfMeetings = calendar.get(day - 1);
        ArrayList<Meeting> output = new ArrayList<>();

        for (Meeting meeting : dayOfMeetings) {
            if (func.test(meeting)) {
                output.add(meeting);
            }
        }

        return output;
    }
}
