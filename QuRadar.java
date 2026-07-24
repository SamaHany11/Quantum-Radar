import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class QuRadar {
    private List<Rule> rules = new ArrayList<>();
    private List<Fine> fines = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public Fine processObservation(Observation obs) {
        List<Violation> violations = new ArrayList<>();
        for (Rule r : rules) {
            Violation v = r.check(obs);
            if (v != null) violations.add(v);
        }

        if (violations.isEmpty()) return null;

        Fine fine = new Fine(obs.getPlateNumber(), violations);
        fines.add(fine);
        fine.print();
        return fine;
    }


    public Map<String, Double> getAllPossibleFines() {
        Map<String, Double> totals = new LinkedHashMap<>();
        for (Fine f : fines) {
            totals.merge(f.getPlateNumber(), f.getTotalAmount(), Double::sum);
        }
        return totals;
    }


    public Map<String, Integer> getAllViolatedRules() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Fine f : fines) {
            for (Violation v : f.getViolations()) {
                counts.merge(v.ruleName, 1, Integer::sum);
            }
        }
        return counts;
    }
}
