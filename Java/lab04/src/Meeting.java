import java.time.LocalTime;

public final class Meeting extends AbstractEntry {
    private Priority priority;

    public Meeting(String description, LocalTime startDate, LocalTime endDate, Priority priority) throws Exception {
        super(description, startDate, endDate);
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String entryDetails() {
        return String.format("Priority: %s", priority);
    }
}
