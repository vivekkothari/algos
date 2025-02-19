package org.example.ds;

/**
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * <p>Update the value of an element in nums. Calculate the sum of the elements of nums between
 * indices left and right inclusive where left <= right. Implement the NumArray class:
 *
 * <p>NumArray(int[] nums) Initializes the object with the integer array nums. void update(int
 * index, int val) Updates the value of nums[index] to be val. int sumRange(int left, int right)
 * Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left]
 * + nums[left + 1] + ... + nums[right]).
 *
 * <p>Example 1:
 *
 * <p>Input ["NumArray", "sumRange", "update", "sumRange"] [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output [null, 9, null, 8]
 *
 * <p>Explanation NumArray numArray = new NumArray([1, 3, 5]); numArray.sumRange(0, 2); // return 1
 * + 3 + 5 = 9 numArray.update(1, 2); // nums = [1, 2, 5] numArray.sumRange(0, 2); // return 1 + 2 +
 * 5 = 8
 *
 * <p>Constraints:
 *
 * <p>1 <= nums.length <= 3 * 104 -100 <= nums[i] <= 100 0 <= index < nums.length -100 <= val <= 100
 * 0 <= left <= right < nums.length At most 3 * 104 calls will be made to update and sumRange.
 */
class NumArray {

  static class SegmentTreeNode {
    int start, end;
    SegmentTreeNode left, right;
    int sum;

    public SegmentTreeNode(int start, int end) {
      this.start = start;
      this.end = end;
      this.left = null;
      this.right = null;
      this.sum = 0;
    }
  }

  private final SegmentTreeNode root;

  public NumArray(int[] nums) {
    root = buildTree(nums, 0, nums.length - 1);
  }

  private SegmentTreeNode buildTree(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    } else {
      SegmentTreeNode ret = new SegmentTreeNode(start, end);
      if (start == end) {
        ret.sum = nums[start];
      } else {
        int mid = start + (end - start) / 2;
        ret.left = buildTree(nums, start, mid);
        ret.right = buildTree(nums, mid + 1, end);
        ret.sum = ret.left.sum + ret.right.sum;
      }
      return ret;
    }
  }

  void update(int i, int val) {
    update(root, i, val);
  }

  void update(SegmentTreeNode root, int pos, int val) {
    if (root.start == root.end) {
      root.sum = val;
    } else {
      int mid = root.start + (root.end - root.start) / 2;
      if (pos <= mid) {
        update(root.left, pos, val);
      } else {
        update(root.right, pos, val);
      }
      root.sum = root.left.sum + root.right.sum;
    }
  }

  public int sumRange(int i, int j) {
    return sumRange(root, i, j);
  }

  public int sumRange(SegmentTreeNode root, int start, int end) {
    if (root.end == end && root.start == start) {
      return root.sum;
    } else {
      int mid = root.start + (root.end - root.start) / 2;
      if (end <= mid) {
        return sumRange(root.left, start, end);
      } else if (start >= mid + 1) {
        return sumRange(root.right, start, end);
      } else {
        return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
      }
    }
  }
}
