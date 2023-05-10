public enum Status {
    PLANNING, CONFIRMED, REALIZATION, DONE;

    public static Status transfromStatus(int i) throws Exception {
        switch (i) {
            case 1 -> { return Status.PLANNING; }
            case 2 -> { return Status.CONFIRMED; }
            case 3 -> { return Status.REALIZATION; }
            case 4 -> { return Status.DONE; }
            default -> throw new Exception("Status can be: " +
                    "1 - PLANNING" +
                    "2 - CONFIRMED" +
                    "3 - REALIZATION" +
                    "4 - DONE");
        }
    }
}
