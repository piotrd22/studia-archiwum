import java.time.LocalTime;

public final class Task extends AbstractEntry {
    private Status status;

    public Task(String description, LocalTime startDate, LocalTime endDate, Status status) throws Exception {
        super(description, startDate, endDate);
        this.status = status;
    }

    @Override
    public String entryDetails() {
        return String.format("Status: %s", status);
    }
}
