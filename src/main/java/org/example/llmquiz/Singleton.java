package org.example.llmquiz;

/**
 * Q5: Implement a thread-safe singleton class in Java.
 *
 * <pre>
 *   public class Singleton {
 *     private static Singleton instance;
 *
 *     private Singleton() {
 *         // Prevent instantiation
 *     }
 *
 *     public static Singleton getInstance() {
 *         // Implement this
 *     }
 * }
 * </pre>
 */
public class Singleton {
  private static volatile Singleton instance;

  private Singleton() {
    // Prevent instantiation
  }

  public static Singleton getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (Singleton.class) {
      if (instance != null) {
        return instance;
      }
      instance = new Singleton();
      return instance;
    }
  }
}
