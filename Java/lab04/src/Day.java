public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static Day transformDay(int i) throws Exception {
        switch (i) {
            case 1 -> { return Day.MONDAY; }
            case 2 -> { return Day.TUESDAY; }
            case 3 -> { return Day.WEDNESDAY; }
            case 4 -> { return Day.THURSDAY; }
            case 5 -> { return Day.FRIDAY; }
            case 6 -> { return Day.SATURDAY; }
            case 7 -> { return Day.SUNDAY; }
            default -> throw new Exception("Day are between 1 (MONDAY) to 7 (SUNDAY)");
        }
    }
}
