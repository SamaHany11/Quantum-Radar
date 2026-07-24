# QuRadar

A simple, rule-based traffic radar system built in Java. It takes vehicle observations (plate number, date, car type, speed, seatbelt status) and evaluates them against a configurable set of traffic rules, issuing fines for any violations found.

## Overview

`QuRadar` is the core of the system. It doesn't know anything about how the data was captured — it simply receives an `Observation` and checks it against every registered `Rule`. If one or more rules are broken, it creates a `Fine` containing all the violations and their fees.

The design is built around a single extension point: the `Rule` interface. Adding a new traffic rule (e.g. a lane discipline rule, or a rule for a new vehicle type) only requires writing a class that implements `Rule` and registering it with `addRule()` — the `QuRadar` class itself never needs to change.

## Project structure

| File | Responsibility |
|---|---|
| `Observation.java` | Data captured for a single vehicle (plate, date, car type, speed, seatbelt status) |
| `CarType.java` | Enum of recognized vehicle types (PRIVATE, TRUCK, BUS) |
| `Rule.java` | Interface implemented by every traffic rule |
| `SpeedLimitRule.java` | Checks a vehicle's speed against the max allowed for its car type |
| `SeatbeltRule.java` | Checks whether the seatbelt is fastened |
| `Fine.java` | Groups the violations found for one observation and prints the ticket |
| `QuRadar.java` | Evaluates observations against all registered rules and tracks issued fines |
| `Main.java` | Demo entry point showing the system in action |

## Rules currently implemented

- Truck speed must not exceed 60
- Private car speed must not exceed 80
- Bus speed must not exceed 70
- Seatbelt must be fastened

## How to run

```bash
javac *.java
java Main
```

## Example output

```
Traffic for car ABC1234
Total amount: 400 EGP
Violations:
- Seatbelt not fastned : 100 EGP
- speed of 94 exceeded max allowed 80 : 300 EGP
```

## API

- `addRule(Rule rule)` — registers a new rule with the radar
- `processObservation(Observation obs)` — evaluates one observation, prints and returns a `Fine` if any violation occurred (or `null` otherwise)
- `getAllPossibleFines()` — returns the total fees issued per plate number
- `getAllViolatedRules()` — returns how many times each rule has been violated

