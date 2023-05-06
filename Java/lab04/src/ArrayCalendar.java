import java.time.LocalTime;
import java.util.ArrayList;

public class ArrayCalendar {
    private final ArrayList<ArrayList<Meeting>> calendar;

    public ArrayCalendar() {
        this(7);
    }

    public ArrayCalendar(int numberOfDays) {
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

    public void deleteMeetingFromDay(int day, int Id) {
        ArrayList<Meeting> meetings = calendar.get(day - 1);
        meetings.remove(Id );
    }

    public void deleteMeetingFromWholeDay(int day) {
        calendar.get(day - 1).clear();
    }

    public String checkMeetingFromDay(int day) {
        StringBuilder output = new StringBuilder();
        ArrayList<Meeting> dayOfMeeting = calendar.get(day - 1);

        for (int i = 0; i < dayOfMeeting.size(); i++) {
            output.append("ID: %s, ".formatted(i));
            output.append(dayOfMeeting.get(i).toString());
            output.append("\n");
        }

        return output.toString();
    }

    public String checkMeetingFromDayAndPriority(int day, Priority priority) {
        StringBuilder output = new StringBuilder();
        ArrayList<Meeting> dayOfMeeting = calendar.get(day - 1);

        for (int i = 0; i < dayOfMeeting.size(); i++) {
            if (dayOfMeeting.get(i).getPriority() == priority) {
                output.append("ID: %s, ".formatted(i));
                output.append(dayOfMeeting.get(i).toString());
                output.append("\n");
            }
        }

        return output.toString();
    }
}
