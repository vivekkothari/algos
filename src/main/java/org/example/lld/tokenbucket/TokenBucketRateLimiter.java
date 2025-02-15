package org.example.lld.tokenbucket;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class TokenBucketRateLimiter {
  private final Clock clock;
  private final int maxTokens;
  private final int refillRate;
  private final long refillIntervalMillis;
  private final AtomicInteger tokens;
  private final AtomicLong lastRefillAt;

  TokenBucketRateLimiter(Clock clock, int maxTokens, int refillRate, Duration refillInterval) {
    this.clock = clock;
    this.maxTokens = maxTokens;
    this.refillRate = refillRate;
    this.refillIntervalMillis = refillInterval.toMillis();
    tokens = new AtomicInteger(maxTokens);
    lastRefillAt = new AtomicLong(clock.instant().toEpochMilli());
  }

  private void refillTokens() {
    long now = clock.millis();
    long lastRefill = lastRefillAt.get();

    // Ensure only one thread performs refill (CAS ensures atomicity)
    if (now - lastRefill >= refillIntervalMillis) {
      long newLastRefill =
          lastRefill + ((now - lastRefill) / refillIntervalMillis) * refillIntervalMillis;
      if (lastRefillAt.compareAndSet(lastRefill, newLastRefill)) {
        int newTokens = (int) ((now - lastRefill) / refillIntervalMillis) * refillRate;
        tokens.updateAndGet(current -> Math.min(maxTokens, current + newTokens));
      }
    }
  }

  public boolean allowRequest() {
    refillTokens();
    return tokens.updateAndGet(i -> i > 0 ? i - 1 : i) > 0;
  }

  public static void main(String[] args) {
    var rateLimiter = new TokenBucketRateLimiter(Clock.systemUTC(), 10, 2, Duration.ofSeconds(1));

    for (int i = 0; i < 15; i++) {
      System.out.println(
          "Request " + (i + 1) + ": " + (rateLimiter.allowRequest() ? "Allowed" : "Rejected"));
      try {
        Thread.sleep(150); // Simulating request bursts
      } catch (InterruptedException e) {
        // recommended because catching InterruptedException clears interrupt flag
        Thread.currentThread().interrupt();
        // you probably want to quit if the thread is interrupted
        return;
      }
    }
  }
}
