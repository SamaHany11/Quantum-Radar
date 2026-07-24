public class SpeedLimitRule implements Rule {
    private CarType carType;
    private double maxSpeed;
    private double fee;

    public SpeedLimitRule(CarType carType, double maxSpeed, double fee) {
        this.carType = carType;
        this.maxSpeed = maxSpeed;
        this.fee = fee;
    }

    public Violation check(Observation obs) {
        if (obs.getCarType() != carType || obs.getSpeed() <= maxSpeed) {
            return null;
        }
        String desc = "speed of " + (int) obs.getSpeed() + " exceeded max allowed " + (int) maxSpeed;
        return new Violation("Speed Limit Rule (" + carType + ")", desc, fee);
    }
}
