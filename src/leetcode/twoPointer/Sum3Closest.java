package leetcode.twoPointer;

import java.util.Arrays;

// https://leetcode.com/problems/3sum-closest/description/
public class Sum3Closest {

  public int threeSumClosest(int[] nums, int target) {

    Arrays.sort(nums);

    int answer = nums[0] + nums[1] + nums[2];

    for(int i = 0; i < nums.length - 2; i++) {
      int left = i;
      int mid = i + 1;
      int right = nums.length - 1;

      while(true) {

        if(mid >= right) break;

        int sum = nums[left] + nums[mid] + nums[right];
        int gap = Math.abs(target - sum);

        if(target == sum) {
          answer = target;
          break;
        }

        // 현재 합산 결과 - target 이 answer에 저장된 값 - target 보다 작다
        // -> 더 근접한 값이다.
        else if(gap < Math.abs(target - answer)) {
          answer = sum;
        }

        // 합산 결과가 target 보다 작다 => 근접하게 하기 위해선 합산결과를 더 크게 만들어야 한다.
        if(sum < target) mid++;
        else right--;
      }
    }

    return answer;
  }
}
