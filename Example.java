import java.util.ArrayList;
import java.util.List;

public class Example {
    public static int maximumSumIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] prevIndex = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            prevIndex[i] = -1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + nums[i]) {
                    dp[i] = dp[j] + nums[i];
                    prevIndex[i] = j;
                }
            }
        }

        int maxSum = dp[0];
        int endIndex = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > maxSum) {
                maxSum = dp[i];
                endIndex = i;
            }
        }

        List<Integer> subsequence = new ArrayList<>();
        int currentIndex = endIndex;
        while (currentIndex != -1) {
            subsequence.add(nums[currentIndex]);
            currentIndex = prevIndex[currentIndex];
        }
        reverseList(subsequence);

        System.out.println("Subsequence: " + subsequence); // Print the subsequence

        return maxSum;
    }

    private static void reverseList(List<Integer> list) {
        int start = 0;
        int end = list.size() - 1;

        while (start < end) {
            int temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11};
        int maxSum = maximumSumIncreasingSubsequence(nums);
        System.out.println("Maximum Sum: " + maxSum);
    }
}
