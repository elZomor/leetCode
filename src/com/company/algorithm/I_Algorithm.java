package com.company.algorithm;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


/**
    &&& PROBLEMS IN THIS FILE &&&
 * // Binary Search //
 * 704. Binary Search
 * 278. First Bad Version
 * 35. Search Insert Position
 * // Two Pointers //
 * 977. Squares of a Sorted Array
 * 189. Rotate Array
 * 283. Move Zeroes
 * 167. Two Sum II - Input Array Is Sorted
 */
public class I_Algorithm {
    public static void main(String[] args) {

    }
    // To get the average of two int to avoid int overflow (Sum is big int)
    static int getAvg(int a, int b) {
        return (a / 2) + (b / 2) +
                ((a % 2 + b % 2) / 2);
    }


    // 704. Binary Search
    public static int search(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]) return -1;
        int start = 0;
        int last = nums.length - 1;
        int avg ;

        while (last >= start) {
            avg = (start + last) / 2;
            if (target == nums[avg]) {
                return avg;
            } else if (target > nums[avg]) {
                start = avg + 1 ;
            } else {
                last = avg - 1 ;
            }
        }

        return -1;
    }

    // 278. First Bad Version - Solved
    public static int firstBadVersion(int n) {
        int start = 0;
        int last = n;
        int avg = 0;
        boolean avgBadStatus ;
        int bugVersion = 0;
        if (n == 1) return isBadVersion(1) ? 1 : 0;
        while (last >= start) {
            avg = (last / 2) + (start / 2) +
                    ((last % 2 + start % 2) / 2);
            avgBadStatus = isBadVersion(avg);
            if (! avgBadStatus) {
                start = avg + 1;
            } else {
                last = avg - 1;
                bugVersion = avg;
            }

        }
        return bugVersion;
    }

    public static boolean isBadVersion(int x) {
        return x >= 1702766719;
    }


    // 35. Search Insert Position - Solved
    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int last = nums.length - 1;
        if (target <= nums[0]) return 0;
        if (target > nums[nums.length - 1]) return nums.length;
        if (target == nums[nums.length - 1]) return nums.length - 1;
        int avg = 0 ;
        boolean low = false;
        while (last >= start) {
            avg = (last + start) / 2;
            if (target == nums[avg]) {
                return avg ;
            } else if (target < nums[avg]){
                last = avg - 1;
                low = true;
            } else if (target > nums[avg]) {
                start = avg + 1;
                low = false;
            }
        }

        return low ? avg : avg + 1 ;


    }

    // 977. Squares of a Sorted Array - Solved
    public static int[] sortedSquares(int[] nums) {
        int[] sortedList = new int[nums.length];
        if (nums[0] >= 0) {
            for (int i = 0; i < nums.length; i++) {
                sortedList[i] = nums[i] * nums[i];
            }
            return sortedList;
        }
        if (nums[nums.length - 1] <= 0) {
            for (int i = 0; i < nums.length; i++) {
                sortedList[i] = nums[nums.length - 1 - i] * nums[nums.length - 1 - i];
            }
            return sortedList;
        }
        int changeSignIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                changeSignIndex = Math.abs(nums[i - 1]) < nums[i] ? i - 1 : i;
                break;
            }
        }
        int i = 1;
        int j = 1;
        int k = 1;
        sortedList[0] = nums[changeSignIndex] * nums[changeSignIndex];
        while (changeSignIndex - j >= 0 || changeSignIndex + i < nums.length) {
            if (changeSignIndex - j < 0) {
                sortedList[k] = nums[changeSignIndex + i] * nums[changeSignIndex + i];
                i++;
            } else if (changeSignIndex + i >= nums.length) {
                sortedList[k] = nums[changeSignIndex - j] * nums[changeSignIndex - j];
                j++;
            } else if (Math.abs(nums[changeSignIndex + i]) < Math.abs(nums[changeSignIndex - j])) {
                sortedList[k] = nums[changeSignIndex + i] * nums[changeSignIndex + i];
                i++;
            } else {
                sortedList[k] = nums[changeSignIndex - j] * nums[changeSignIndex - j];
                j++;
            }
            k++;
        }
        return sortedList;
    }

    // Another solution with streams
    public static int[] sortedSquaresStreams(int[] nums) {
        int[] sortedArray = Arrays.stream(nums)
                .map( x -> x*x)
                .sorted()
                .toArray();
        Arrays.stream(sortedArray).forEach(System.out::println);
        return sortedArray;
    }

    // Another solution with two pointers
    public static int[] sortedSquaresAnotherSolution(int[] nums) {
        int[] finalArr = new int[nums.length];
        int start = 0;
        int last = nums.length - 1;
        for (int i = last; i >= 0 ; i--) {
            if (Math.abs(nums[last]) > Math.abs(nums[start])) {
                finalArr[i] = Math.abs(nums[last]) * Math.abs(nums[last]);
                last--;
            } else {
                finalArr[i] = Math.abs(nums[start]) * Math.abs(nums[start]);
                start++;
            }
        }
        return finalArr;
    }


    // 189. Rotate Array - Solved
    public static void rotate(int[] nums, int k) {
        int[] newArr = new int[nums.length];
        int j ;
        for (int i = 0; i < nums.length ; i++) {
            j = i + k > nums.length - 1
                    ? (i + k) % nums.length
                    : i + k;
            newArr[j] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, newArr.length);
    }

    // 283. Move Zeroes - Solved
    public static void moveZeroes(int[] nums) {
        if (nums.length == 1) return;
        int i = 0;
        int innerZero = 0;
        for (int j = 0; j < nums.length - 1; j++) {
            if (nums[j] == 0 && nums[j+1] != 0) {
                innerZero++;
            }
        }
        if (innerZero == 0) return;
        while (i < nums.length - 1) {
            if (nums[i] == 0) {
                nums[i] = nums[i+1];
                nums[i+1] = 0;
            }
            i++;
        }
        moveZeroes(nums);
    }
    // Another solution with two pointers
    public static void moveZeroesAnotherSolution(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
            j++;
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }

    // 167. Two Sum II - Input Array Is Sorted
    public static int[] twoSum(int[] numbers, int target)   {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int x = numbers[i] + numbers[j];
            if (x < target) {
                i++;
            } else if (x > target) {
                j--;
            } else {
                return new int[] {i, j};
            }
        }
        return null;
    }
}
