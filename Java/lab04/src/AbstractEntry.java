import java.time.LocalTime;

sealed abstract class AbstractEntry permits Task, Meeting {
    public static final LocalTime MIN_HOUR = LocalTime.of(8, 0);

    private String description;
    private LocalTime startDate;
    private LocalTime endDate;

    public AbstractEntry(String description, LocalTime startDate, LocalTime endDate) throws Exception {
        if (startDate.isBefore(MIN_HOUR)) throw new Exception("Start date is too early");
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalTime getStartDate() { return startDate; }

    public LocalTime getEndDate() { return endDate; }

    public String getDescription() { return description; }

    public abstract String entryDetails();

    @Override
    public String toString() {
        return String.format("Description: %s, Start: %s, End: %s, %s",
                description, startDate, endDate, entryDetails());
    }
}
