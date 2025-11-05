class FrogJump{
     public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        int[] nums = {10,20,30,10};
        System.out.println(rec(3,nums));
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp,-1);
        System.out.println(memo(3,dp, nums));
        System.out.println(space(3,nums));
    }
    public static int rec(int n ,int[] nums){
          if(n == 0) return 0;
          int fs = rec(n-1,nums) + Math.abs(nums[n] - nums[n-1]);
          int ss = Integer.MAX_VALUE;
          if(n > 1){
              ss = rec(n-2,nums) + Math.abs(nums[n] - nums[n-2]);
          }
          return Math.min(fs,ss);
    }
    public static int memo(int n , int[] dp, int[] nums){
        if(n == 0) return 0;
        if(dp[n] != -1) return dp[n];
        int left = memo(n-1,dp,nums) + Math.abs(nums[n] - nums[n-1]);
        int right = Integer.MAX_VALUE;
        if(n > 1){
            right = memo(n-2,dp,nums) + Math.abs(nums[n] - nums[n-2]);
        }
        return dp[n] = Math.min(left, right);
    }
    public static int space(int n , int[] nums){
        int pre = 0 ;
        int pre2 = 0;
        for(int i = 1 ; i < n ; i++){
            int left = pre + Math.abs(nums[i] - nums[i-1]);
            int right = Integer.MAX_VALUE;
            if( i > 1 ){
                right = pre2 + Math.abs(nums[i] - nums[i-2]);
            }
            int current = Math.min(left,right);
            pre2 = pre;
            pre = current;
        }
        return pre;
    }
}