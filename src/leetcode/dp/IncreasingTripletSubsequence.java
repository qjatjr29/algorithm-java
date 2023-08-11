package leetcode.dp;

import java.util.Arrays;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {

        int[] dp = new int[nums.length];

        int size = 0;
        boolean isSatisfied = false;

        for(int i = 0; i < nums.length; i++) {
            int pos = Arrays.binarySearch(dp, 0, size, nums[i]);
            if(pos < 0) pos = -pos - 1;
            dp[pos] = nums[i];
            if(pos == size) size += 1;
        }
        if(size >= 3) isSatisfied = true;

        return isSatisfied;
    }
}
