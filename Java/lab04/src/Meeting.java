import java.time.LocalTime;

public class Meeting {
    static final LocalTime MIN_HOUR = LocalTime.of(8, 0);

    private final String description;
    private final LocalTime startDate;
    private final LocalTime endDate;
    private final Priority priority;

    public Meeting(String description, LocalTime startDate, LocalTime endDate, Priority priority) throws Exception {
        if (startDate.isBefore(MIN_HOUR)) throw new Exception("Start date is too early");
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalTime getStartDate() { return startDate; }

    public LocalTime getEndDate() { return endDate; }

    @Override
    public String toString() {
        return "Description: %s, Start: %s, End: %s, Priority: %s".formatted(description, startDate, endDate, priority);
    }
}
