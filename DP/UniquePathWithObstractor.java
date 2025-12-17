public class UniquePathWithObstractor {
    public static void main(String[] args) {
        int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        UniquePathWithObstractor upo = new UniquePathWithObstractor();
        int result = upo.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(result);

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // return dfs(0,0,obstacleGrid,new
        // int[obstacleGrid.length+1][obstacleGrid[0].length+1]);
        // return tebu(obstacleGrid.length,obstacleGrid[0].length, obstacleGrid);
        // return tebulation(obstacleGrid.length,obstacleGrid[0].length, obstacleGrid);
        return spaceOptimization(obstacleGrid.length, obstacleGrid[0].length, obstacleGrid);
    }

    // recursive with memoization time complexity O(m*n) space complexity O(m*n)
    public static int dfs(int i, int j, int[][] grid, int[][] dp) {
        if (i >= grid.length || j >= grid[0].length || grid[i][j] == 1)
            return 0;
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return 1;
        if (dp[i][j] != 0)
            return dp[i][j];
        return dp[i][j] = dfs(i + 1, j, grid, dp) + dfs(i, j + 1, grid, dp);
    }

    // tabulation time complexity O(m*n) space complexity O(m*n)
    public static int tebu(int m, int n, int[][] grid) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            if (grid[i][0] != 1)
                dp[i][0] = 1;
            else
                dp[i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            if (grid[0][i] != 1)
                dp[0][i] = 1;
            else
                dp[0][i] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] != 1)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else
                    dp[i][j] = 0;
            }
        }
        return dp[m - 1][n - 1];
    }

    // tabulation time complexity O(m*n) space complexity O(m*n)
    public static int tebulation(int m, int n, int[][] grid) {
        int[][] dp = new int[m + 1][n + 1];
        if (grid[0][0] == 1)
            return 0;
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (grid[i][0] == 0)
                dp[i][0] = dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            if (grid[0][i] == 0)
                dp[0][i] = dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // space optimization time complexity O(m*n) space complexity O(n)
    public static int spaceOptimization(int m, int n, int[][] grid) {
        int[] dp = new int[n];
        dp[0] = grid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
