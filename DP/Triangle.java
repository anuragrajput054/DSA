import java.util.List;

public class Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3));
        Triangle t = new Triangle();
        int result = t.minimumTotal(triangle);
        System.out.println(result);

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // Initialize the top element
        dp[0][0] = triangle.get(0).get(0);

        // Fill the dp array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
            }
        }

        // Find the minimum path sum in the last row
        int minTotal = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minTotal = Math.min(minTotal, dp[n - 1][j]);
        }

        return minTotal;
    }

    public int minimumTotoal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // return helper(0,0,n,triangle, new int[n][n]);
        return tebu(triangle, n);
    }

    public static int helper(int i, int j, int n, List<List<Integer>> list, int[][] dp) {
        if (i == n - 1)
            return list.get(n - 1).get(j);
        if (dp[i][j] != 0)
            return dp[i][j];
        int down = list.get(i).get(j) + helper(i + 1, j, n, list, dp);
        int dg = list.get(i).get(j) + helper(i + 1, j + 1, n, list, dp);
        return dp[i][j] = Math.min(down, dg);
    }

    public static int tebu(List<List<Integer>> list, int n) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = list.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = list.get(i).get(j) + dp[i + 1][j];
                int dg = list.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, dg);
            }
        }
        return dp[0][0];
    }
}
