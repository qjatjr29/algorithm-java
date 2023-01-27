package leetcode.dp;

// https://leetcode.com/problems/maximum-subarray/

public class MaximumSubarray {

  public int maxSubArray(int[] nums) {

    int[] dp = new int[nums.length + 1];
    int answer = nums[0];

    for(int i = 0; i < nums.length; i++) {
      dp[i] = nums[i];
    }

    for(int i = 1; i < nums.length; i++) {
      if(dp[i] < dp[i - 1] + nums[i]) dp[i] = dp[i - 1] + nums[i];
      answer = Math.max(answer, dp[i]);
    }

    return answer;
  }


}
