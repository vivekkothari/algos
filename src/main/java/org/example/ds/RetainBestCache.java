package org.example.ds;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * A RetainBestCache is a caching mechanism that keeps track of the best N elements based on a
 * ranking function while removing the least valuable items when the cache exceeds its capacity.
 * Here’s how you can implement it in Java.
 *
 * <p>Implementation Strategy Use a HashMap for fast lookups and retrieval. Use a PriorityQueue
 * (Min-Heap) to track the elements based on their ranking, ensuring efficient eviction of the least
 * valuable element when the cache exceeds its size.
 */
public class RetainBestCache<K, V> {
  private final int capacity;
  private final RankingFunction<V> rankingFunction;
  private final Map<K, V> cache;
  private final TreeMap<Integer, K> rankingMap;

  public RetainBestCache(int capacity, RankingFunction<V> rankingFunction) {
    this.capacity = capacity;
    this.rankingFunction = rankingFunction;
    this.cache = new HashMap<>();
    this.rankingMap = new TreeMap<>();
  }

  public void put(K key, V value) {
    if (cache.containsKey(key)) {
      // Remove old ranking from TreeMap before updating
      Integer oldRanking = rankingFunction.getRanking(cache.get(key));
      rankingMap.remove(oldRanking);
    }
    cache.put(key, value);
    var newRanking = rankingFunction.getRanking(value);
    rankingMap.put(newRanking, key);

    if (cache.size() > capacity) {
      evictLowest();
    }
  }

  public V get(K key) {
    return cache.get(key);
  }

  private void evictLowest() {
    // The lowest-ranked element is the first entry in the TreeMap
    var lowestRank = rankingMap.firstKey();
    var lowestRankedKey = rankingMap.get(lowestRank);
    if (lowestRankedKey != null) {
      cache.remove(lowestRankedKey);
    }
  }

  @FunctionalInterface
  public interface RankingFunction<V> {
    int getRanking(V value);
  }

  public static void main(String[] args) {
    RankingFunction<String> rankingFunction = String::length; // Example: ranking by string length
    var cache = new RetainBestCache<Integer, String>(3, rankingFunction);

    cache.put(1, "A"); // Ranking: 1
    cache.put(2, "Hello"); // Ranking: 5
    cache.put(3, "Java"); // Ranking: 4

    cache.put(4, "World"); // Ranking: 5 (Evicts "A" because it has the lowest ranking)

    System.out.println(cache.get(1)); // Should print null since "A" was evicted
    System.out.println(cache.get(2)); // Should print "Hello"
    System.out.println(cache.get(3)); // Should print "Java"
    System.out.println(cache.get(4)); // Should print "World"
  }
}
