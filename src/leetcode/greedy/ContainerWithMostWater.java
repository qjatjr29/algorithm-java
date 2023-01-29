package leetcode.greedy;

public class ContainerWithMostWater {

  public int maxArea(int[] height) {

    int l_point = 0;
    int r_point = height.length - 1;

    int answer = 0;

    while(l_point < r_point) {
      int w = r_point - l_point;
      int h = Math.min(height[l_point], height[r_point]);

      int area = w * h;

      answer = Math.max(answer, area);

      if(height[l_point] > height[r_point]) r_point--;
      else {
        l_point++;
      }
    }

    return answer;
  }


}
