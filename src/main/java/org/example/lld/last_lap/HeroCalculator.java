package org.example.lld.last_lap;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HeroCalculator {

  private static final int LOCK_TIMEOUT_SEC = 1;

  private final Map<String, List<Lap>> driverLaps = new HashMap<>();
  private final Map<String, LapStats> driverLapStats = new HashMap<>();
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public void recordLap(String driver, long lapTimeMillis) {
    recordLap(driver, lapTimeMillis, false);
  }

  public void recordLap(String driver, long lapTimeMillis, boolean isPitStop) {
    try {
      if (lock.writeLock().tryLock(LOCK_TIMEOUT_SEC, TimeUnit.SECONDS)) {
        List<Lap> laps = driverLaps.computeIfAbsent(driver, _ -> new ArrayList<>());
        Lap lap = new Lap(driver, Duration.ofMillis(lapTimeMillis), isPitStop);
        laps.add(lap);
        driverLapStats.computeIfAbsent(driver, _ -> new LapStats(driver)).recordLap(lap);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.writeLock().unlock();
    }
  }

  public String getLastLapHero() {
    try {
      if (lock.readLock().tryLock(LOCK_TIMEOUT_SEC, TimeUnit.SECONDS)) {
        return driverLapStats.values().stream()
            .max(Comparator.comparing(LapStats::getLastLapGain))
            .map(LapStats::driver)
            .orElseThrow();
      }
      throw new IllegalStateException("Cannot acquire lock");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.readLock().unlock();
    }
  }

  public String getLastLapHeroExcludingPit() {
    try {
      if (lock.readLock().tryLock(LOCK_TIMEOUT_SEC, TimeUnit.SECONDS)) {
        return driverLapStats.values().stream()
            .max(Comparator.comparing(LapStats::getLastLapGainExcludingPitStops))
            .map(LapStats::driver)
            .orElseThrow();
      }
      throw new IllegalStateException("Cannot acquire lock");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.readLock().unlock();
    }
  }
}
