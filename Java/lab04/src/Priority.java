public enum Priority {
    LOW, MEDIUM, HIGH;

    public static Priority transfromPriority(int i) throws Exception {
        switch (i) {
            case 1 -> { return Priority.LOW; }
            case 2 -> { return Priority.MEDIUM; }
            case 3 -> { return Priority.HIGH; }
            default -> throw new Exception("Priority can be between 1 (LOW) to 3 (HIGH)");
        }
    }
}
