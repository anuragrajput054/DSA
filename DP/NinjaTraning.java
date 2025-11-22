import java.util.Arrays;

public class NinjaTraning {
    public static void main(String[] args) {
        int[][] task = {
                { 10, 40, 70 },
                { 20, 50, 80 },
                { 30, 60, 90 }
        };
        System.out.println(ninja(task.length - 1, 3, task));
        int[][] dp = new int[task.length][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println(ninjaMemo(task.length - 1, 3, task, dp));
        System.out.println(ninjaTabu(task));
        System.out.println(ninjaSpace(task));
    }

    public static int ninja(int day, int lastIndex, int[][] task) {
        if (day == 0) {
            int maxi = 0;
            for (int i = 0; i < task[day].length; i++) {
                if (i != lastIndex) {
                    maxi = Math.max(maxi, task[0][i]);
                }
            }
            return maxi;
        }
        int maxi = 0;
        for (int i = 0; i < task[day].length; i++) {
            if (i != lastIndex) {
                maxi = Math.max(task[day][i] + ninja(day - 1, i, task), maxi);
            }
        }
        return maxi;
    }

    public static int ninjaMemo(int day, int lastIndex, int[][] task, int[][] dp) {
        if (day == 0) {
            if (dp[day][lastIndex] != -1) {
                return dp[day][lastIndex];
            }
            int maxi = 0;
            for (int i = 0; i < task[day].length; i++) {
                if (i != lastIndex) {
                    maxi = Math.max(maxi, task[0][i]);
                }
            }
            return dp[day][lastIndex] = maxi;
        }

        if (dp[day][lastIndex] != -1) {
            return dp[day][lastIndex];
        }
        int maxi = 0;
        for (int i = 0; i < task[day].length; i++) {
            if (i != lastIndex) {
                maxi = Math.max(task[day][i] + ninjaMemo(day - 1, i, task, dp), maxi);
            }
        }
        return dp[day][lastIndex] = maxi;
    }

    public static int ninjaTabu(int[][] task) {
        int n = task.length;
        int[][] dp = new int[n][4];

        dp[0][0] = Math.max(task[0][1], task[0][2]);
        dp[0][1] = Math.max(task[0][0], task[0][2]);
        dp[0][2] = Math.max(task[0][0], task[0][1]);
        dp[0][3] = Math.max(task[0][0], Math.max(task[0][1], task[0][2]));

        for (int day = 1; day < n; day++) {
            for (int lastIndex = 0; lastIndex < 4; lastIndex++) {
                dp[day][lastIndex] = 0;
                for (int taskIndex = 0; taskIndex < 3; taskIndex++) {
                    if (taskIndex != lastIndex) {
                        int point = task[day][taskIndex] + dp[day - 1][taskIndex];
                        dp[day][lastIndex] = Math.max(dp[day][lastIndex], point);
                    }
                }
            }
        }
        return dp[n - 1][3];
    }

    public static int ninjaSpace(int[][] task) {
        int n = task.length;
        int[] prev = new int[4];
        prev[0] = Math.max(task[0][1], task[0][2]);
        prev[1] = Math.max(task[0][0], task[0][2]);
        prev[2] = Math.max(task[0][0], task[0][1]);
        prev[3] = Math.max(task[0][0], Math.max(task[0][1], task[0][2]));
        for (int day = 1; day < n; day++) {
            int[] curr = new int[4];
            for (int lastIndex = 0; lastIndex < 4; lastIndex++) {
                curr[lastIndex] = 0;
                for (int taskIndex = 0; taskIndex < 3; taskIndex++) {
                    if (taskIndex != lastIndex) {
                        int point = task[day][taskIndex] + prev[taskIndex];
                        curr[lastIndex] = Math.max(curr[lastIndex], point);
                    }
                }
            }
            prev = curr;
        }
        return prev[3];
    }
}
