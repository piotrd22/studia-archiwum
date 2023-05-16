import java.time.LocalTime;

public final class Task extends AbstractEntry {
    private Status status;

    public Task(String description, LocalTime startDate, LocalTime endDate, Status status) throws Exception {
        super(description, startDate, endDate);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("%s, Status: %s",
                super.toString(), status);
    }
}
