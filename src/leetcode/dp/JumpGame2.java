package leetcode.dp;

// https://leetcode.com/problems/jump-game-ii/
public class JumpGame2 {
  public int jump(int[] nums) {

    int[] dp = new int[nums.length];

    for(int i = 0; i < nums.length; i++) {
      dp[i] = 10001;
    }
    dp[nums.length - 1] = 0;

    for(int i = nums.length - 2; i >= 0; i--) {
      int jump = nums[i];

      for(int j = 1; j <= jump; j++) {
        if(i + j >= nums.length) continue;
        dp[i] = Math.min(dp[i], dp[i + j] + 1);
      }
    }
    return dp[0];
  }
}
