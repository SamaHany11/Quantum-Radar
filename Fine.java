import java.util.List;

class Violation {
    String ruleName;
    String description;
    double fee;

    Violation(String ruleName, String description, double fee) {
        this.ruleName = ruleName;
        this.description = description;
        this.fee = fee;
    }
}

public class Fine {
    private String plateNumber;
    private List<Violation> violations;
    private double totalAmount;

    public Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.violations = violations;
        for (Violation v : violations) {
            totalAmount += v.fee;
        }
    }

    public String getPlateNumber() { return plateNumber; }
    public List<Violation> getViolations() { return violations; }
    public double getTotalAmount() { return totalAmount; }

    public void print() {
        System.out.println("Traffic for car " + plateNumber);
        System.out.println("Total amount: " + (int) totalAmount + " EGP");
        System.out.println("Violations:");
        for (Violation v : violations) {
            System.out.println("- " + v.description + " : " + (int) v.fee + " EGP");
        }
    }
}
