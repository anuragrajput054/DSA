package SubSequence;

public class SubsetSum {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        int target = 9;
        System.out.println(recursion(nums.length - 1, nums, target));
        System.out.println(memo(nums.length - 1, nums, target, new boolean[nums.length + 1][target + 1]));
    }

    // time complexity
    public static boolean recursion(int ind, int[] nums, int target) {
        if (target == 0)
            return true;
        if (ind == 0)
            return (target == nums[ind]);
        boolean nonTake = recursion(ind - 1, nums, target);
        boolean take = false;
        if (target >= nums[ind]) {
            take = recursion(ind - 1, nums, target - nums[ind]);
        }
        return take || nonTake;
    }

    public static boolean memo(int ind, int[] nums, int target, boolean[][] dp) {
        if (target == 0)
            return true;
        if (ind == 0)
            return (target == nums[ind]);
        boolean nonTake = memo(ind - 1, nums, target, dp);
        boolean take = false;
        if (target >= nums[ind]) {
            take = memo(ind - 1, nums, target - nums[ind], dp);
        }
        return dp[ind][target] = take || nonTake;
    }

    public static boolean tabulation(int ind, int target, int[] nums) {
        boolean[][] dp = new boolean[ind + 1][target + 1];
        for (int i = 0; i <= target; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < ind; i++) {
            for (int t = 1; t <= target; t++) {
                boolean nonTake = dp[i - 1][t];
                boolean take = false;
                if (nums[i] <= t) {
                    take = dp[ind - 1][t - nums[i]];
                }
                dp[i][t] = take || nonTake;
            }
        }
        return dp[ind - 1][target];

    }

    public static boolean spaceOptimization(int ind, int target, int[] nums) {
        boolean[] curr = new boolean[target + 1];
        boolean[] pre = new boolean[target + 1];
        pre[0] = curr[0] = true;
        for (int i = 1; i < ind; i++) {
            for (int t = 1; t <= target; t++) {
                boolean nonTake = pre[t];
                boolean take = false;
                if (nums[i] < t) {
                    take = pre[t - nums[i]];
                }
                curr[t] = take || nonTake;
            }
            pre = curr;
        }
        return pre[target];
    }
}
