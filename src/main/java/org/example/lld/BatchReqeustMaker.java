package org.example.lld;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;

record Request(int id) {}

class BatchReqeustMaker implements AutoCloseable {
  private final int maxItemsPerBatch;
  private final Queue<Request> buffer = new ConcurrentLinkedQueue<>();
  private final AtomicInteger currentSize = new AtomicInteger();
  private final ScheduledExecutorService service;
  private final Consumer<List<Request>> requestConsumer;

  BatchReqeustMaker(
      Duration maxBatchWindow, int maxItemsPerBatch, Consumer<List<Request>> requestConsumer) {
    this.maxItemsPerBatch = maxItemsPerBatch;
    this.requestConsumer = requestConsumer;
    this.service = Executors.newScheduledThreadPool(1);
    service.scheduleAtFixedRate(this::flush, 0, maxBatchWindow.toMillis(), TimeUnit.MILLISECONDS);
  }

  public void makeRequest(Request request) {
    buffer.offer(request);
    if (currentSize.incrementAndGet() >= maxItemsPerBatch) {
      flush();
    }
  }

  private void flush() {
    if (buffer.isEmpty()) {
      return;
    }
    var batch = new ArrayList<Request>();
    while (batch.size() <= maxItemsPerBatch) {
      var item = buffer.poll();
      if (item == null) break;
      batch.add(item);
    }

    if (!batch.isEmpty()) {
      currentSize.addAndGet(-batch.size()); // Update count
      requestConsumer.accept(batch);
    }
  }

  @Override
  public void close() {
    service.close();
  }

  public static void main(String[] args) {
    try (var maker =
        new BatchReqeustMaker(
            Duration.ofSeconds(1),
            5,
            reqs ->
                System.out.printf(
                    "[%s] Making requests %s%n",
                    Instant.now(), reqs.stream().map(Request::id).toList()))) {
      IntStream.rangeClosed(0, 100)
          .forEach(
              i -> {
                maker.makeRequest(new Request(i));
                if (i % 4 == 0) {
                  sleep(100);
                }
                if (i % 8 == 0) {
                  sleep(300);
                }
                System.out.printf("[%s] Enqueued request %d%n", Instant.now(), i);
              });
    }
  }

  private static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
