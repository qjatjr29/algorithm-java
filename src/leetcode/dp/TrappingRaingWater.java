package leetcode.dp;

// https://leetcode.com/problems/trapping-rain-water/
public class TrappingRaingWater {

  public int trap(int[] height) {

    int length = height.length;
    int answer = 0;

    // i번 인덱스까지 -> 방향 최대 높이
    int[] leftHeight = new int[length];
    // i번 인덱스까지 <- 방향 최대 높이
    int[] rightHeight = new int[length];

    leftHeight[0] = height[0];
    rightHeight[length - 1] = height[length - 1];

    for(int i = 1; i < length; i++) {
      leftHeight[i] = Math.max(height[i], leftHeight[i - 1]);
    }

    for(int i = length - 2; i >= 0; i--) {
      rightHeight[i] = Math.max(height[i], rightHeight[i + 1]);
    }

    // 비가 고이는 조건 (현재 위치를 i라고 했을 경우)
    // i 보다 작은 위치의 높이가 i번째 높이 보다 높아야함. (최대 값을 구해야함) - leftHeight
    // i 보다 큰 위치의 높이가 i번째 높이보다 높아야함. (최대 값을 구해야함) - rightHeight
    // 이 두 값의 최솟값 - 현재 i번째 높이 의 값이 비가 고이는 양
    for(int i = 1; i < length - 1; i++) {
      answer += Math.min(leftHeight[i], rightHeight[i]) - height[i];
    }
    return answer;
  }

}
