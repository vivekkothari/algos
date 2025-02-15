package org.example.llmquiz;

import java.util.concurrent.ConcurrentHashMap;

class SafeStore<K, V> {
  private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

  public void put(K key, V value) {
    map.put(key, value);
  }

  public V get(K key) {
    return map.get(key);
  }
}
