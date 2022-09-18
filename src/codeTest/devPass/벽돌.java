package codeTest.devPass;

import static java.util.Arrays.sort;

import java.util.ArrayList;
import java.util.List;

public class 벽돌 {

  private static List<Integer> result;
  public static int[] solution(int[] bricks) {
    int[] answer = {};
    result = new ArrayList<>();

    int target = 0;
    for(int i = 0; i < bricks.length; i++){
      target += bricks[i];
      sol(target, i + 1, bricks, 1);
    }

    answer = new int[result.size()];
    for(int i = 0; i < result.size(); i++) {
      answer[i] = result.get(i);
    }
    sort(answer);
    return answer;
  }

  private static void sol(int length, int index, int[] bricks, int count) {

    if(index >= bricks.length) {
      result.add(count);
      return;
    }

    int sum = 0;
    for(int i = index; i < bricks.length; i++) {
      sum += bricks[i];
      if(sum > length) return;
      else if(sum == length) sol(length, i + 1, bricks, count + 1);
    }

  }

  public static void main(String[] args) {

//    int[] bricks = {2, 1, 1, 2};
//    int[] bricks = {1, 2, 3, 2, 1};
    int[] bricks = {1, 1, 1, 1, 1, 1};
    int[] answers = solution(bricks);

    for (int answer : answers) {
      System.out.print(answer + " ");
    }
    System.out.println();
  }

}
