Design and implement a system that records lap times for Formula 1 drivers and identifies the **"Last Lap Hero"** – the
driver who showed the **biggest improvement in their last lap** compared to their **own average lap time** (excluding
the last lap).

### 1. Core Requirements

- Accept **driver name** and **lap time** as input (in milliseconds or precise numeric format).
- Track lap times per driver.
- When requested, return the **Last Lap Hero**:
    - The driver whose **last lap** shows the **maximum improvement** (i.e., lower time) compared to their **average of
      all their laps**.
    - Improvement can be measured in:
        - **Absolute terms**: `avg - last`
        - **Percentage terms**: `(avg - last) / avg`

### 2. Example Input

```
Input: [
  ("D1", 100), 
  ("D1", 110),
  ("D2", 90),
  ("D2", 95),
  ("D3", 70),
  ("D3", 50)
]
```

**D1**:

- Avg of all: (100)
- Last: 110
- Gain: `110 - 100 = +10` (slower)

**D2**:

- Avg of all: (90)
- Last: 95
- Gain: `95 - 90 = +5` (slower)

**D3**:

- Avg of all: (70)
- Last: 50
- Gain: `50 - 70 = -20` (faster)

🔔 **Last Lap Hero** = D3 (improved the most with -20ms gain)

### Alternative Calculation (Including All Laps in Average):

If we were to include all laps in the average calculation:

- **D1**: last lap gain = 110 - (100 + 110) / 2 = +5 (slower)
- **D2**: last lap gain = 95 - (90 + 95) / 2 = +2.5 (slower)
- **D3**: last lap gain = 50 - (50 + 70) / 2 = -10 (faster)

**Note**: The problem statement requires comparing against **"average of all previous laps"** (excluding the last lap),
not all laps including the last one.

### 3. Design Considerations

- **What if a driver has only one lap?**  
  → Ignore them from consideration.

- **How is improvement measured?**  
  → Up to you: absolute time or percentage-based. Discuss both.

- **How much data to store?**  
  → You don’t need to store all laps, just:
    - `totalTime`
    - `lapCount`
    - `lastLapTime`

- **Avoid floating-point inaccuracies**  
  → Use `int`, `long`, or `double` (avoid `float`), prefer **millisecond precision**.

### 4. API Design & Function Signatures

- `recordLap(driverName: String, lapTimeMillis: Int, isPitStop: Boolean = false)`
- `getLastLapHero(includePitStops: Boolean = true): String?`

---

## 🛠️ Scale-Up 1: Pit Stop Handling

- Pit stops cause **longer lap times** and must be handled:
    - **Exclude pit stop laps** from average lap time calculations.
    - But **still include** them as valid last laps.

#### Example:

```
Driver | Lap Time | Pit Stop?
D1     |   100    |  No
D1     |   120    |  No
D1     |   130    |  Yes   ← Last lap

D2     |    80    |  No
D2     |   100    |  Yes
D2     |   100    |  No
D2     |    90    |  Yes   ← Last lap
```

**Calculating with pit stops**:

- Use all lap times (pit or not) to compute the average.

**Calculating without pit stops**:

- Exclude pit stop laps from average.

This requires you to **modify your data structures** to:

- Track which laps were pit stops.
- Still retain the ability to compute correct averages both ways.

---

## 🚥 Scale-Up 2: Realtime Telemetry / Live Tracking

- Add ability to **track and report** whenever the "Last Lap Hero" **changes dynamically** during the race.

Approaches:

- Observer pattern
- Publisher-Subscriber
- Logging to standard out

Example: After each `recordLap()`, emit who the current Last Lap Hero is.

---

## ⚠️ Advanced Considerations

- **Thread safety** – if multiple threads record lap data.
- **Avoid division by zero** when computing averages.
- **Tie-breakers** when multiple drivers have the same improvement.
- Use of **constants**, properly named and cased.
- Choose appropriate data structures to balance time and memory efficiency.
- Ensure testability: use TDD, write unit tests.

---

## 🧩 Problem Statement

> Design and implement a system that:
>
> - Accepts lap times (with optional pit stop indicators) for Formula 1 race drivers.
> - Computes and returns the **"Last Lap Hero"** — the driver whose last lap showed the **biggest improvement** compared
    to their average lap time (excluding the last lap).
> - Supports both **absolute** and **percentage-based** comparisons.
> - Can optionally **exclude pit stop laps** from average calculation.
> - Is capable of **reporting telemetry** in real-time as drivers improve.
> - Handles edge cases such as:
    >
- Drivers with only one lap
>   - Ties
>   - Outliers
>   - High-precision timing
>   - Thread safety
> - Can scale to race-size data, handle concurrency, and ensure performance.
