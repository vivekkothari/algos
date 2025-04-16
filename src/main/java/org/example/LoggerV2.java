package org.example;

import java.util.*;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/logger-rate-limiter/description/
 *
 * <pre>
 *     Design a logger system that receives a stream of messages along with their timestamps.
 *     Each unique message should only be printed at most every 10 seconds
 *     (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * Implement the Logger class:
 *
 * Logger() Initializes the logger object.
 * bool shouldPrintMessage(int timestamp, string message) Returns true if
 * the message should be printed in the given timestamp, otherwise returns false.
 *
 * Followup:
 *
 * </pre>
 */
public class LoggerV2 {
  private final Queue<Log> queue = new LinkedList<>();
  private final Set<String> messageMap = new HashSet<>();

  record Log(String message, int timestamp) {}

  public boolean shouldPrintMessage(int timestamp, String message) {
    while (!queue.isEmpty() && timestamp - queue.peek().timestamp >= 10) {
      messageMap.remove(queue.poll().message);
    }
    if (messageMap.contains(message)) {
      return false;
    }
    queue.offer(new Log(message, timestamp));
    messageMap.add(message);
    return true;
  }

  public static void main(String[] args) {
    LoggerV2 logger = new LoggerV2();
    System.out.println(logger.shouldPrintMessage(1, "foo"));
    System.out.println(logger.shouldPrintMessage(2, "bar"));
    System.out.println(logger.shouldPrintMessage(3, "foo"));
    System.out.println(logger.shouldPrintMessage(8, "bar"));
    System.out.println(logger.shouldPrintMessage(10, "foo"));
    System.out.println(logger.shouldPrintMessage(11, "foo"));
  }
}
