package leetcode.greedy;

import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons {

  public static int findMinArrowShots(int[][] points) {

    Arrays.sort(points, (o1, o2) -> Integer.compare(o1[1], o2[1]));

    int arrows = 1;
    int prev = points[0][1];

    for(int i = 0; i < points.length; i++) {
      if(points[i][0] <= prev) continue;
      else {
        arrows++;
        prev = points[i][1];
      }
    }

    return arrows;
  }

  public static void main(String[] args) {
    int[][] p = {
        {10, 16},
        {2, 8},
        {1, 6},
        {7, 12}
    };
    System.out.println(findMinArrowShots(p));
  }


}
