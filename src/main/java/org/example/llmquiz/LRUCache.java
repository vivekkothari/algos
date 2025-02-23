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
class LRUCache {
  private final Map<Integer, Integer> cache;
  private final int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.cache =
        new LinkedHashMap<>(capacity, 0.75f, true) {
          protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > LRUCache.this.capacity;
          }
        };
  }

  public int get(int key) {
    return cache.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    cache.put(key, value);
  }
}
