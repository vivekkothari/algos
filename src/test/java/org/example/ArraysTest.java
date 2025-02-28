package org.example;

import static org.example.Arrays.majorityElement;
import static org.example.Arrays.majorityElementNoMap;
import static org.example.Arrays.reverse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArraysTest {

  @Test
  @DisplayName("Test sortColors")
  void testSortColors() {
    var nums = new int[] {2, 0, 2, 1, 1, 0};
    Arrays.sortColors(nums);
    assertArrayEquals(new int[] {0, 0, 1, 1, 2, 2}, nums);
  }

  @Test
  @DisplayName("Test mergeSort")
  void testMergeSort() {
    var nums = new int[] {2, 0, 2, 1, 1, 0};
    Arrays.sortArray(nums);
    assertArrayEquals(new int[] {0, 0, 1, 1, 2, 2}, nums);
  }

  @Test
  @DisplayName("Test majority element")
  void testMajorityElement() {
    assertEquals(3, majorityElement(new int[] {3, 2, 3}));
    assertEquals(2, majorityElement(new int[] {2, 2, 1, 1, 1, 2, 2}));
    assertEquals(3, majorityElementNoMap(new int[] {3, 2, 3}));
    assertEquals(2, majorityElementNoMap(new int[] {2, 2, 1, 1, 1, 2, 2}));
  }

  @Test
  @DisplayName("Test reverse")
  void testReverse() {
    assertArrayEquals(new int[] {3, 2, 3}, reverse(new int[] {3, 2, 3}));
    assertArrayEquals(new int[] {0, 1, 1, 2, 0, 2}, reverse(new int[] {2, 0, 2, 1, 1, 0}));
    assertArrayEquals(new int[] {0, 1, 1, 2, 0, 2, 5}, reverse(new int[] {5, 2, 0, 2, 1, 1, 0}));
    assertArrayEquals(new int[] {2, 2, 1, 1, 1, 2, 2}, reverse(new int[] {2, 2, 1, 1, 1, 2, 2}));
  }
}
