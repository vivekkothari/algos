package org.example.ds;

import java.util.Iterator;
import java.util.List;

/**
 * Design an iterator that supports the peek operation on an existing iterator in addition to the
 * hasNext and the next operations.
 *
 * <p>Implement the PeekingIterator class:
 *
 * <p>PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator
 * iterator. int next() Returns the next element in the array and moves the pointer to the next
 * element. boolean hasNext() Returns true if there are still elements in the array. int peek()
 * Returns the next element in the array without moving the pointer. Note: Each language may have a
 * different implementation of the constructor and Iterator, but they all support the int next() and
 * boolean hasNext() functions.
 *
 * <p>Example 1:
 *
 * <p>Input ["PeekingIterator", "next", "peek", "next", "next", "hasNext"] [[[1, 2, 3]], [], [], [],
 * [], []] Output [null, 1, 2, 2, 3, false]
 *
 * <p>Explanation PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next(); // return 1, the pointer moves to the next element [1,2,3].
 * peekingIterator.peek(); // return 2, the pointer does not move [1,2,3]. peekingIterator.next();
 * // return 2, the pointer moves to the next element [1,2,3] peekingIterator.next(); // return 3,
 * the pointer moves to the next element [1,2,3] peekingIterator.hasNext(); // return False
 *
 * <p>Constraints:
 *
 * <p>1 <= nums.length <= 1000 1 <= nums[i] <= 1000 All the calls to next and peek are valid. At
 * most 1000 calls will be made to next, hasNext, and peek.
 */
class PeekingIterator implements Iterator<Integer> {
  private final Iterator<Integer> delegate;
  private Integer head;

  public PeekingIterator(Iterator<Integer> iterator) {
    head = iterator.hasNext() ? iterator.next() : null;
    delegate = iterator;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    return head;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    var temp = head;
    head = delegate.hasNext() ? delegate.next() : null;
    return temp;
  }

  @Override
  public boolean hasNext() {
    return head != null;
  }

  public static void main(String[] args) {
    PeekingIterator peekingIterator = new PeekingIterator(List.of(1, 2, 3).iterator());
    System.out.println(
        peekingIterator.next()); // return 1, the pointer moves to the next element [1,2,3].
    System.out.println(peekingIterator.peek()); // return 2, the pointer does not move [1,2,3].
    System.out.println(
        peekingIterator.next()); // return 2, the pointer moves to the next element [1,2,3]
    System.out.println(
        peekingIterator.next()); // return 3, the pointer moves to the next element [1,2,3]
    System.out.println(peekingIterator.hasNext()); // return False
  }
}
