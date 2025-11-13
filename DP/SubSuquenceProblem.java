import java.util.ArrayList;
import java.util.List;

public class SubSuquenceProblem {
    public static void main(String[] args) {
        int[] arr = { 3, 1, 2 };
        // printSubSuquence(arr, 0, new java.util.ArrayList<>());
        // System.out.println("Maximum Subsequence Sum: " + maxSum);
        // System.out.println("Maximum Subsequence Sum: " + maxSum);
        printS(arr, 0, 3, new ArrayList<>());
        raj(arr, 0, 3, 0, new ArrayList<>());
    }

    // static int maxSum = Integer.MIN_VALUE;;

    // public static void printSubSuquence(int[] arr, int ind, List<Integer> list) {
    // if (ind >= arr.length) {
    // int currentSum = 0;
    // for (int i = 0; i < list.size(); i++) {
    // currentSum += list.get(i);
    // }
    // if (currentSum >= maxSum) {
    // maxSum = currentSum;
    // }
    // return;
    // }
    // list.add(arr[ind]);
    // printSubSuquence(arr, ind + 1, list);
    // list.remove(list.size() - 1);
    // printSubSuquence(arr, ind + 1, list);
    // }

    public static void printS(int[] nums, int ind, int k, List<Integer> list) {
        if (ind == nums.length) {
            if (sum(list) == k) {
                System.out.println(list);
            }
            return;
        }
        list.add(nums[ind]);
        printS(nums, ind + 1, k, list);
        list.remove(list.size() - 1);
        printS(nums, ind + 1, k, list);
    }

    public static int sum(List<Integer> list) {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i);
        }
        return total;
    }

    public static void raj(int[] nums, int ind, int k, int s, List<Integer> list) {
        if (ind == nums.length) {
            if (s == k) {
                System.out.println(list);
            }
            return;
        }
        list.add(nums[ind]);
        s += nums[ind];
        raj(nums, ind + 1, k, s, list);
        list.remove(list.size() - 1);
        s -= nums[ind];
        raj(nums, ind + 1, k, s, list);
    }
}

// class Solution {
// public int rob(int[] nums) {

// Space optimization
// if(nums == null || nums.length == 0) return 0;
// if(nums.length == 1) return nums[0];
// int pre1 = 0;
// int pre2 = 0;
// for(int i = 0; i < nums.length;i++){
// int current = Math.max(nums[i] + pre2, pre1);
// pre2 = pre1;
// pre1 = current;
// }
// return pre1;

// int ind = nums.length - 1;
// int[] dp = new int[nums.length + 1];
// Arrays.fill(dp,-1);
// return helper(nums, ind, dp);

// * tabulation
// if(nums.length == 1) return nums[0];
// int n = nums.length;
// int[] dp = new int[n+1];
// dp[0] = nums[0];
// dp[1] = Math.max(nums[0], nums[1]);
// for(int i = 2; i < n; i++){
// dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
// }
// return dp[n-1];
// }

// Memoization**
// public static int helper(int[] nums , int ind, int[] dp){
// if(ind == 0) return nums[0];
// if(ind < 0) return 0;
// if(dp[ind] != -1) return dp[ind];
// int pick = helper(nums, ind - 2 , dp) + nums[ind];
// int nonPick = helper(nums, ind -1, dp);
// dp[ind] = Math.max(pick,nonPick);
// return dp[ind];
// }
// }
