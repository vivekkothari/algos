package org.example.lld.deploy_notif;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class DeploymentNotificationService implements AutoCloseable {

  private final int commitThreshold;
  private final Duration waitDuration = Duration.ofHours(1);
  private final Queue<Commit> buffer = new ConcurrentLinkedQueue<>();
  private final AtomicInteger currentSize = new AtomicInteger();
  private final ScheduledExecutorService service;
  private final Consumer<Deployment> consumer;
  private final DeploymentService deploymentService;
  private final Lock lock = new ReentrantLock();

  public DeploymentNotificationService(DeploymentService deploymentService, int commitThreshold) {
    this.commitThreshold = commitThreshold;
    this.deploymentService = deploymentService;
    this.consumer = this::notifyDeployment;
    this.service = Executors.newScheduledThreadPool(1);
    service.scheduleAtFixedRate(
        this::triggerDeployment, 0, waitDuration.toMillis(), TimeUnit.MILLISECONDS);
  }

  public void mergeCommit(Commit commit) {
    buffer.offer(commit);
    if (currentSize.incrementAndGet() >= commitThreshold) {
      triggerDeployment();
    }
  }

  /** Triggers deployment if x commits are accumulated or y time is passed. */
  private void triggerDeployment() {
    lock.lock();
    try {
      if (buffer.isEmpty()) {
        return;
      }
      var batch = new ArrayList<Commit>();
      while (batch.size() <= commitThreshold) {
        var item = buffer.poll();
        if (item == null) break;
        batch.add(item);
      }

      if (!batch.isEmpty()) {
        currentSize.addAndGet(-batch.size()); // Update count
        consumer.accept(
            new Deployment(
                ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE), batch, Instant.now()));
      }
    } finally {
      lock.unlock();
    }
  }

  void notifyDeployment(Deployment deployment) {
    deploymentService.deploymentStarted(deployment);
    Runnable runnable =
        () ->
            deploymentService.deploymentFinished(
                deployment, ThreadLocalRandom.current().nextBoolean());
    service.schedule(runnable, ThreadLocalRandom.current().nextLong(60), TimeUnit.SECONDS);
  }

  @Override
  public void close() throws InterruptedException {
    service.shutdown();
    service.awaitTermination(10, TimeUnit.SECONDS);
  }
}
