package com.company.dataStructure;

import java.util.*;

/**
 &&& PROBLEMS IN THIS FILE &&&
 * // Array //
 * 217. Contains Duplicate
 * 53. Maximum Subarray
 * 1. Two Sum
 * 88. Merge Sorted Array
 * 350. Intersection of Two Arrays II
 * 121. Best Time to Buy and Sell Stock
 */
public class I_DataStructure {
    public static void main(String[] args) {

    }

    // 217. Contains Duplicate - Solved
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }

    // 53. Maximum Subarray - Solved
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int max_sum = nums[0];
        int cur = 0;
        for (int x : nums) {
            if (cur < 0) cur = 0;
            cur += x;
            max_sum = Math.max(max_sum, cur);
        }
        return max_sum;
    }

    // 1. Two Sum - Solved (May need modifications for performance)
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0 ; i < nums.length ; i++) {
            for (int j = i + 1 ; j < nums.length ; j++ ) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    // 88. Merge Sorted Array - Solved
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums2.length);
            return;
        }
        int i = 0;
        int j = 0;
        int[] newArr = new int[nums1.length];
        for (int k = 0; k < newArr.length; k++) {
            if (j < n) {
                if (nums1[i] < nums2[j] && i < m) {
                    newArr[k] = nums1[i];
                    i++;
                } else {
                    newArr[k] = nums2[j];
                    j++;
                }
            } else {
                newArr[k] = nums1[i];
                i++;
            }
        }
        System.arraycopy(newArr, 0, nums1, 0, newArr.length);

    }

    // 350. Intersection of Two Arrays II - Solved
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int i : nums1) {
            if (myMap.containsKey(i)) {
                myMap.put(i, myMap.get(i) + 1);
            } else {
                myMap.put(i, 1);
            }
        }
        List<Integer> myList = new ArrayList<>();
        for (int j : nums2) {
            if (myMap.containsKey(j)) {
                if (myMap.get(j) != 0) {
                    myMap.put(j, myMap.get(j) - 1);
                    myList.add(j);
                }
            }
        }
        myList.forEach(System.out::println);
        int[] finalArr = new int[myList.size()];
        for (int i = 0; i < myList.size(); i++) {
            finalArr[i] = myList.get(i);
        }
        return finalArr;
    }

    // 121. Best Time to Buy and Sell Stock - Solved
    public static int maxProfit(int[] prices) {
        int profit = 0;
        int buy = prices[0];
        List<Integer> lst = new ArrayList<>();
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= buy) {
                buy = prices[i];
                lst.add(profit);
                profit = 0;
            } else if (prices[i] - buy > profit){
                profit = prices[i] - buy;
            }
        }
        lst.add(profit);
        return lst.stream().mapToInt(v -> v).max().getAsInt();
    }
}
