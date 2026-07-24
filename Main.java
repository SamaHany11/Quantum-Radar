import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        QuRadar radar = new QuRadar();
        radar.addRule(new SeatbeltRule(100));
        radar.addRule(new SpeedLimitRule(CarType.TRUCK, 60, 300));
        radar.addRule(new SpeedLimitRule(CarType.PRIVATE, 80, 300));
        radar.addRule(new SpeedLimitRule(CarType.BUS, 70, 300));

        Observation o1 = new Observation("ABC1234", LocalDate.now(), CarType.PRIVATE, 94, false);
        Observation o2 = new Observation("XYZ987", LocalDate.now(), CarType.TRUCK, 55, true);
        Observation o3 = new Observation("TRK555", LocalDate.now(), CarType.TRUCK, 75, false);
        Observation o4 = new Observation("BUS111", LocalDate.now(), CarType.BUS, 65, true);

        radar.processObservation(o1);
        System.out.println();
        radar.processObservation(o2);
        radar.processObservation(o3);
        System.out.println();
        radar.processObservation(o4);

        System.out.println();
        System.out.println("All fines per plate:");
        for (Map.Entry<String, Double> e : radar.getAllPossibleFines().entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue().intValue() + " EGP");
        }

        System.out.println();
        System.out.println("Violated rules count:");
        for (Map.Entry<String, Integer> e : radar.getAllViolatedRules().entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}