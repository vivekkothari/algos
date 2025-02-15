package org.example.llmquiz;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * <pre>
 *   You need to design a Task Scheduler that:
 *
 * Accepts tasks with a specified delay.
 * Executes tasks asynchronously after the delay.
 * Supports cancellation of scheduled tasks.
 *
 * Requirements:
 * Implement schedule(Runnable task, long delayMillis) to schedule a task.
 * Implement cancel(UUID taskId) to cancel a scheduled task before execution.
 * Tasks should run in a background thread without blocking the main thread.
 * </pre>
 */
class TaskScheduler {

  enum TaskStatus {
    PENDING,
    RUNNING,
    PAUSED,
    CANCELLED,
    COMPLETED
  }

  static final class PeriodicTask implements Runnable {
    private final Runnable task;
    private final long delayMillis;
    private TaskStatus status;

    PeriodicTask(Runnable task, long delayMillis) {
      this.task = task;
      this.delayMillis = delayMillis;
      this.status = TaskStatus.PENDING;
    }

    @Override
    public void run() {
      status = TaskStatus.RUNNING;
      task.run();
      status = TaskStatus.COMPLETED;
    }
  }

  private final Map<UUID, ScheduledFuture<?>> futureTasks = new ConcurrentHashMap<>();
  private final Map<UUID, PeriodicTask> periodicTaskMap = new ConcurrentHashMap<>();
  private final Map<UUID, TaskStatus> taskStatusMap = new ConcurrentHashMap<>();
  private final ScheduledExecutorService scheduledExecutorService;

  TaskScheduler() {
    scheduledExecutorService = Executors.newScheduledThreadPool(10);
  }

  public UUID schedule(Runnable task, long delayMillis) {
    var id = UUID.randomUUID();
    futureTasks.put(
        id,
        scheduledExecutorService.schedule(
            () -> {
              taskStatusMap.put(id, TaskStatus.RUNNING);
              task.run();
              taskStatusMap.put(id, TaskStatus.COMPLETED);
            },
            delayMillis,
            TimeUnit.MILLISECONDS));
    taskStatusMap.put(id, TaskStatus.PENDING);
    return id;
  }

  TaskStatus getTaskStatus(UUID taskId) {
    return taskStatusMap.getOrDefault(taskId, TaskStatus.CANCELLED);
  }

  public UUID scheduleAtFixedRate(Runnable task, long initialDelay, long periodMillis) {
    UUID id = UUID.randomUUID();
    var periodicTask = new PeriodicTask(task, periodMillis);
    var future =
        scheduledExecutorService.scheduleAtFixedRate(
            periodicTask, initialDelay, periodMillis, TimeUnit.MILLISECONDS);
    futureTasks.put(id, future);
    periodicTaskMap.put(id, periodicTask);
    return id;
  }

  boolean pause(UUID taskId) {
    ScheduledFuture<?> future = futureTasks.get(taskId);
    if (future != null && periodicTaskMap.containsKey(taskId)) {
      future.cancel(false); // Cancel without interrupting
      return true;
    }
    return false;
  }

  boolean resume(UUID taskId) {
    if (!periodicTaskMap.containsKey(taskId)) {
      return false;
    }
    var periodicTask = periodicTaskMap.get(taskId);
    var future =
        scheduledExecutorService.scheduleAtFixedRate(
            periodicTask, 0, periodicTask.delayMillis, TimeUnit.MILLISECONDS);
    futureTasks.put(taskId, future);
    return true;
  }

  public boolean cancel(UUID taskId) {
    ScheduledFuture<?> future = futureTasks.remove(taskId);
    if (future == null) return false;

    periodicTaskMap.remove(taskId);
    return future.cancel(true);
  }

  public void shutdown() {
    scheduledExecutorService.shutdown();
  }
}
