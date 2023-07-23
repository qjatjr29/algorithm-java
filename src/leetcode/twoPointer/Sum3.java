package leetcode.twoPointer;
import java.util.*;

// https://leetcode.com/problems/3sum/description/
public class Sum3 {

  private static final int TARGET = 0;

  public List<List<Integer>> threeSum(int[] nums) {

    Arrays.sort(nums);

    List<List<Integer>> answer = new ArrayList<>();

    // 맨 처음 수를 고정한 후, 2,3 번째 수의 포인터를 이동시키면서 TARGET의 수와 같은 경우를 찾는다.
    for(int i = 0; i < nums.length - 1; i++) {

      // 중복되는 값 skip
      // 이미 체크한 수 -> skip 해야함.
      if(i > 0 && nums[i] == nums[i - 1]) continue;

      int left = i;
      int right = nums.length - 1;
      int mid = i + 1;

      while(true) {

        if(mid >= right) break;

        int sum = nums[left] + nums[mid] + nums[right];

        if(sum == TARGET) {
          answer.add(Arrays.asList(nums[left], nums[mid], nums[right]));

          // 중복되는 값 찾기
          while(mid < right && nums[mid] == nums[mid + 1]) mid++;
          while(mid < right && nums[right] == nums[right - 1]) right--;

          // 중복되는 인덱스를 벗어나기 위해 포인터 이동
          mid++;
          right--;
        }

        else if(sum < TARGET) mid++;

        else right--;
      }
    }
    return answer;
  }

}
