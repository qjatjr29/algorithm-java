package leetcode.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];

        for(int i = 0; i < nums.length; i++) dp[i] = -10001;
        int size = 0;

        for(int i = 0; i < nums.length; i++) {

            int number = nums[i];
            int pos = Arrays.binarySearch(dp, 0, size, number);

            if(pos < 0) pos = -pos - 1;

            dp[pos] = number;
            if(pos == size) size += 1;
        }

        return size;
    }
}
