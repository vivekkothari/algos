package org.example;

import java.util.HashMap;
import java.util.Map;

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
 * </pre>
 */
public class Logger {
  private final Map<String, Integer> messageMap;

  public Logger() {
    messageMap = new HashMap<>();
  }

  public boolean shouldPrintMessage(int timestamp, String message) {
    if (messageMap.containsKey(message)) {
      int lastTimestamp = messageMap.get(message);
      if (timestamp - lastTimestamp < 10) {
        return false;
      }
    }
    messageMap.put(message, timestamp);
    return true;
  }

  public static void main(String[] args) {
    Logger logger = new Logger();
    System.out.println(logger.shouldPrintMessage(1, "foo"));
    System.out.println(logger.shouldPrintMessage(1, "foo"));
  }
}
