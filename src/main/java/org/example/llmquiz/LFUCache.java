package org.example.llmquiz;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache<K, V> {
  private final int capacity;
  private int minFrequency;
  private final Map<K, V> cache;
  private final Map<K, Integer> keyFrequency;
  private final Map<Integer, LinkedHashSet<K>> frequencyList;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    this.minFrequency = 0;
    this.cache = new HashMap<>();
    this.keyFrequency = new HashMap<>();
    this.frequencyList = new HashMap<>();
  }

  public V get(K key) {
    if (!cache.containsKey(key)) {
      return null;
    }
    int frequency = keyFrequency.get(key);
    keyFrequency.put(key, frequency + 1);
    // remove the element with the current freq as we now need to bump it up for this key
    frequencyList.get(frequency).remove(key);
    // If no element exists for that, remove it and update minFreq
    if (frequencyList.get(frequency).isEmpty()) {
      frequencyList.remove(frequency);
      if (minFrequency == frequency) {
        minFrequency++;
      }
    }

    frequencyList.computeIfAbsent(frequency + 1, k -> new LinkedHashSet<>()).add(key);
    return cache.get(key);
  }

  public void put(K key, V value) {
    if (capacity <= 0) {
      return;
    }

    if (cache.containsKey(key)) {
      cache.put(key, value);
      get(key); // Update the frequency of the key
      return;
    }

    if (cache.size() >= capacity) {
      K evict = frequencyList.get(minFrequency).getFirst();
      frequencyList.get(minFrequency).remove(evict);
      if (frequencyList.get(minFrequency).isEmpty()) {
        frequencyList.remove(minFrequency);
      }
      cache.remove(evict);
      keyFrequency.remove(evict);
    }

    cache.put(key, value);
    keyFrequency.put(key, 1);
    minFrequency = 1;
    frequencyList.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
  }

  public static void main(String[] args) {
    LFUCache<Integer, String> lfuCache = new LFUCache<>(3);

    lfuCache.put(1, "one");
    lfuCache.put(2, "two");
    lfuCache.put(3, "three");
    System.out.println("Cache after adding 1, 2, 3: " + lfuCache.cache);

    lfuCache.get(1);
    lfuCache.get(1);
    lfuCache.get(2);
    System.out.println("Cache after accessing 1 twice and 2 once: " + lfuCache.cache);

    lfuCache.put(4, "four");
    System.out.println("Cache after adding 4 (should evict key 3): " + lfuCache.cache);

    lfuCache.get(4);
    lfuCache.get(4);
    lfuCache.put(5, "five");
    System.out.println("Cache after adding 5 (should evict key 2): " + lfuCache.cache);
  }
}
