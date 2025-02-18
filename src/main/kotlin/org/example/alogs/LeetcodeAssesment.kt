package org.example.alogs

class LeetcodeAssesment {
    /**
     * Given an integer x, return true if x is a palindrome, and false otherwise.
     *
     * Example 1:
     *
     * Input: x = 121
     * Output: true
     * Explanation: 121 reads as 121 from left to right and from right to left.
     * Example 2:
     *
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left,
     * it becomes 121-. Therefore it is not a palindrome.
     * Example 3:
     *
     * Input: x = 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     */
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false
        var num = x
        var rev = 0
        while (num != 0) {
            rev = rev * 10 + num % 10
            num /= 10
        }
        return x == rev
    }

    /**
     * Given two non-negative integers, num1 and num2 represented as
     * string, return the sum of num1 and num2 as a string.
     *
     * You must solve the problem without using any built-in library for
     * handling large integers (such as BigInteger). You must also
     * not convert the inputs to integers directly.
     *
     * Example 1:
     *
     * Input: num1 = "11", num2 = "123"
     * Output: "134"
     * Example 2:
     *
     * Input: num1 = "456", num2 = "77"
     * Output: "533"
     * Example 3:
     *
     * Input: num1 = "0", num2 = "0"
     * Output: "0"
     *
     *
     * Constraints:
     *
     * 1 <= num1.length, num2.length <= 104
     * num1 and num2 consist of only digits.
     * num1 and num2 don't have any leading zeros except for the zero itself.
     */
    fun addStrings(num1: String, num2: String): String {
        var i = num1.length - 1
        var j = num2.length - 1
        val res = StringBuilder()
        var carry = 0
        while (i >= 0 && j >= 0) {
            val sum = carry + (num1[i] - '0') + (num2[j] - '0')
            res.append(sum % 10)
            carry = sum / 10
            i--
            j--
        }
        while (i >= 0) {
            val sum = carry + (num1[i] - '0')
            res.append(sum % 10)
            carry = sum / 10
            i--
        }
        while (j >= 0) {
            val sum = carry + (num2[j] - '0')
            res.append(sum % 10)
            carry = sum / 10
            j--
        }
        if (carry != 0) res.append(carry)
        return res.reverse().toString()
    }


    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val set = HashSet<List<Int>>()
        for (i in nums.indices) {
            var j = i + 1
            var k = nums.size - 1
            while (j < k) {
                val sum = nums[i] + nums[j] + nums[k]
                if (sum == 0) {
                    set.add(listOf(nums[i], nums[j], nums[k]))
                    j++
                    k--
                } else if (sum < 0) {
                    j++
                } else {
                    k--
                }
            }
        }
        return set.toList();
    }
}