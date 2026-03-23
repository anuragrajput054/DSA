package SubSequence;

import java.util.Arrays;

public class SubSetSum2 {
    public static void main(String[] args) {
        int[] nums = { 1, 1, 2, 3 };
        int target = 3;
        int[][] dp = new int[nums.length][target + 1];
        System.out.println(recursionSolution(nums.length - 1, target, nums));
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println(memo(nums.length - 1, target, nums, dp));
        System.out.println(tabulation(target, nums));

    }

    public static int recursionSolution(int ind, int target, int[] nums) {
        if (target == 0)
            return 1;
        if (ind == 0)
            return (nums[ind] == target) ? 1 : 0;
        int nonPick = recursionSolution(ind - 1, target, nums);
        int pick = 0;
        if (nums[ind] <= target)
            pick = recursionSolution(ind - 1, target - nums[ind], nums);
        return pick + nonPick;
    }

    public static int memo(int ind, int target, int[] nums, int[][] dp) {
        if (target == 0)
            return 1;
        if (ind == 0)
            return nums[ind] == target ? 1 : 0;
        if (dp[ind][target] != -1)
            return dp[ind][target];
        int nonPick = memo(ind - 1, target, nums, dp);
        int pick = 0;
        if (nums[ind] <= target) {
            pick = memo(ind - 1, target - nums[ind], nums, dp);
        }
        return dp[ind][target] = pick + nonPick;
    }

    public static int tabulation(int target, int[] nums) {
        int[][] dp = new int[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }
        if (target >= nums[0])
            dp[0][nums[0]] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = 0; sum <= target; sum++) {
                int nonPick = dp[i - 1][sum];
                int pick = 0;
                if (target >= nums[i]) {
                    pick = dp[i - 1][target - nums[i]];
                }
                dp[i][sum] = pick + nonPick;
            }
        }
        return dp[nums.length - 1][target];
    }

}
