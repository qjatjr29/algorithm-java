package leetcode.구현;

public class MaximumSumCircularSubarray {
  private static final int PO_INF = 9 * 100000000;
  private static final int NE_INF = - 9 * 100000000;

  public int maxSubarraySumCircular(int[] nums) {

    int size = nums.length;

    int sum = 0;
    int maxSum = NE_INF;
    int minSum = PO_INF;
    int cMax = NE_INF;
    int cMin = PO_INF;

    for(int i = 0; i < size; i++) {

      cMax = Math.max(nums[i], cMax + nums[i]);
      maxSum = Math.max(cMax, maxSum);

      cMin = Math.min(nums[i], cMin + nums[i]);
      minSum = Math.min(cMin, minSum);

      sum += nums[i];
    }

    if(maxSum < 0) return maxSum;
    return Math.max(maxSum, sum - minSum);
  }
}
