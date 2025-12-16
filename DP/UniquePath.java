public class UniquePath {
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        uniquePaths(m,n);      
        memo(m, n, new int[m+1][n+1]) ;
        tebulation(m,n);
        spaceOptimization(m,n);   
        
    }
    // recursive approach time complexity O(2^(m+n)) space complexity O(m+n
     public static int uniquePaths(int m, int n) {
        if( m == 1 && n == 1) return 1;
        if(m < 0 || n < 0) return 0;
        int up = uniquePaths(m-1,n);
        int left = uniquePaths(m,n-1);
        return up + left;

    }
    // top down approach.  time complexity O(m*n) space complexity O(m*n)
    public static int memo(int m, int n, int[][] dp){
        if(m == 1 && n == 1) return 1;
        if(m < 1 || n < 1) return 0;
        if(dp[m][n] != 0) return dp[m][n];
        return dp[m][n] = memo(m-1,n,dp) + memo(m,n-1,dp);
    }
    // tabulation bottum up approach time complexity O(m*n) space complexity O(m*n)
    public static int tebulation(int m, int n){
        int[][] dp = new int[m+1][n+1];
        for(int i = 0 ; i < m ; i++) dp[i][0] = 1;
        for(int i = 0 ; i < n ; i++) dp[0][i] = 1;
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                  dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    // space optimization time complexity O(m*n) space complexity O(n)
    public static int spaceOptimization(int m, int n){
        int[] dp = new int[n];
        for(int i = 0 ; i < n ; i++){
            dp[i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }

}
