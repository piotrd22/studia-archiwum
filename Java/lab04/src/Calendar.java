import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Calendar {
    private final HashMap<Day, ArrayList<Meeting>> calendar;

    public Calendar() {
        this.calendar = new HashMap<>();
        this.calendar.put(Day.MONDAY, new ArrayList<>());
        this.calendar.put(Day.TUESDAY, new ArrayList<>());
        this.calendar.put(Day.WEDNESDAY, new ArrayList<>());
        this.calendar.put(Day.THURSDAY, new ArrayList<>());
        this.calendar.put(Day.FRIDAY, new ArrayList<>());
        this.calendar.put(Day.SATURDAY, new ArrayList<>());
        this.calendar.put(Day.SUNDAY, new ArrayList<>());
    }

    public void addMeeting(Day dayOfTheWeek, String description, LocalTime startDate, LocalTime endDate, Priority priority) throws Exception {
        Meeting meeting = new Meeting(description, startDate, endDate, priority);
        calendar.get(dayOfTheWeek).add(meeting);
    }

    public void deleteMeetingFromDay(Day dayOfTheWeek, int id) {
        calendar.get(dayOfTheWeek).remove(id);
    }

    public void deleteMeetingFromWholeDay(Day dayOfTheWeek) {
        calendar.get(dayOfTheWeek).clear();
    }

    public String checkMeetingFromDay(Day dayOfTheWeek) {
        StringBuilder output = new StringBuilder();
        ArrayList<Meeting> dayOfMeeting = calendar.get(dayOfTheWeek);

        for (int i = 0; i < dayOfMeeting.size(); i++) {
            output.append("ID: %s, ".formatted(i));
            output.append(dayOfMeeting.get(i).toString());
            output.append("\n");
        }

        return output.toString();
    }

    public String checkMeetingFromDayAndPriority(Day dayOfTheWeek, Priority priority) {
        StringBuilder output = new StringBuilder();
        ArrayList<Meeting> dayOfMeeting = calendar.get(dayOfTheWeek);

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
