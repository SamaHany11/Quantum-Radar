public class SeatbeltRule implements Rule {
    private double fee;

    public SeatbeltRule(double fee) {
        this.fee = fee;
    }

    public Violation check(Observation obs) {
        if (obs.isSeatbeltFastened()) return null;
        return new Violation("Seatbelt Rule", "Seatbelt not fastned", fee);
    }
}
