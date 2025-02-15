package org.example.llmquiz;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class TokenBucketRateLimiter {
  private final int maxRequests;
  private final int refillRate;
  private final long timeWindowMillis;

  private final AtomicInteger tokens;
  private final AtomicLong lastRefillAt;

  public TokenBucketRateLimiter(int maxRequests, int refillRate, long timeWindowMillis) {
    this.maxRequests = maxRequests;
    this.refillRate = refillRate;
    this.timeWindowMillis = timeWindowMillis;
    tokens = new AtomicInteger(maxRequests);
    lastRefillAt = new AtomicLong(System.currentTimeMillis());
  }

  public TokenBucketRateLimiter(int maxRequests, long timeWindowMillis) {
    this(maxRequests, 1, timeWindowMillis);
  }

  public boolean tryAcquire() {
    refillTokens();
    return tokens.getAndUpdate(i -> i > 0 ? i - 1 : i) > 0;
  }

  private void refillTokens() {
    long now = System.currentTimeMillis();
    long lastRefill = lastRefillAt.get();
    if (now - lastRefill > timeWindowMillis) {
      long newLastRefill = lastRefill + ((now - lastRefill) / timeWindowMillis) * timeWindowMillis;
      if (lastRefillAt.compareAndSet(lastRefill, newLastRefill)) {
        int newTokens = (int) ((now - lastRefill) / timeWindowMillis) * refillRate;
        tokens.updateAndGet(current -> Math.min(maxRequests, current + newTokens));
      }
    }
  }
}
