package org.example.llmquiz;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Q4: Implement a custom LRUCache<K, V> using LinkedHashMap.
 *
 * <pre>
 *   class LRUCache<K, V> extends LinkedHashMap<K, V> {
 *     private final int capacity;
 *
 *     public LRUCache(int capacity) {
 *         super(capacity, 0.75f, true);
 *         this.capacity = capacity;
 *     }
 *
 *     @Override
 *     protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
 *         // Implement this
 *     }
 * }
 * </pre>
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {
  private final int capacity;

  public LRUCache(int capacity) {
    super(capacity, 0.75f, true);
    this.capacity = capacity;
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > capacity;
  }
}
