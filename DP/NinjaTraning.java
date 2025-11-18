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
        Arrays.fill(dp[0], -1);
        System.out.println(ninjaMemo(task.length - 1, 3, task, dp));
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
}
