public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = { 2, 3, 2 };
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(helper(nums, 0, nums.length - 1), helper(nums, 1, nums.length));

    }

    public static int helper(int[] nums, int start, int end) {
        int pre1 = 0;
        int pre2 = 0;
        for (int i = start; i < end; i++) {
            int current = Math.max(nums[i] + pre2, pre1);
            pre2 = pre1;
            pre1 = current;
        }
        return pre1;
    }
}
