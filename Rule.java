public interface Rule {
    Violation check(Observation obs);
}
