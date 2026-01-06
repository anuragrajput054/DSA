package SubSequence;

public class SubsetSum {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        int target = 9;
        System.out.println(recursion(nums.length - 1, nums, target));
        System.out.println(memo(nums.length - 1, nums, target, new boolean[nums.length + 1][target + 1]));
    }

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
}
