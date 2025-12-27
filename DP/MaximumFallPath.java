public class MaximumFallPath {
    public static void main(String[] args) {
        int[][] matrix = {
                { 2, 1, 3 },
                { 6, 5, 4 },
                { 7, 8, 9 }
        };
        MaximumFallPath m = new MaximumFallPath();
        int result = m.minFallingPathSum(matrix);
        System.out.println(result);

    }

    // time complexity is O(n^2) and space complexity O(n^2)
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        // int ans = Integer.MAX_VALUE;
        // int[][] dp = new int[n][n];
        // for (int j = 0; j < n; j++) {
        // ans = Math.min(ans, helper(0, j, matrix,dp));
        // }
        // return ans;
        return tebulation(matrix, n);
    }

    // time complexity is O(n^2) and space complexity is O(n^2)
    public int helper(int i, int j, int[][] grid, int[][] dp) {
        if (j < 0 || j >= grid.length)
            return Integer.MAX_VALUE;
        if (i == grid.length - 1)
            return grid[i][j];
        if (dp[i][j] != 0)
            return dp[i][j];
        int down = helper(i + 1, j, grid, dp);
        int left = helper(i + 1, j - 1, grid, dp);
        int right = helper(i + 1, j + 1, grid, dp);
        return dp[i][j] = grid[i][j] + Math.min(down, Math.min(left, right));
    }

    // time complexity is O(n^2) and space complexity is O(n^2)
    public int tebulation(int[][] grid, int n) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                } else if (j == n - 1) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j],
                            Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]));
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }
}
