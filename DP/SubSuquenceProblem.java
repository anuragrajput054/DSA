import java.util.List;

public class SubSuquenceProblem {
    public static void main(String[] args) {
        int[] arr = { 3, 1, 2 };
        printSubSuquence(arr, 0, new java.util.ArrayList<>());
        System.out.println("Maximum Subsequence Sum: " + maxSum);
    }

    static int maxSum = Integer.MIN_VALUE;;

    public static void printSubSuquence(int[] arr, int ind, List<Integer> list) {
        if (ind >= arr.length) {
            int currentSum = 0;
            for (int i = 0; i < list.size(); i++) {
                currentSum += list.get(i);
            }
            if (currentSum >= maxSum) {
                maxSum = currentSum;
            }
            return;
        }
        list.add(arr[ind]);
        printSubSuquence(arr, ind + 1, list);
        list.remove(list.size() - 1);
        printSubSuquence(arr, ind + 1, list);
    }
}
