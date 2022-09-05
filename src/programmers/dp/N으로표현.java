package programmers.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class N으로표현 {

  private static List<Set<Integer>> dp;

  public static int solution(int N, int number) {
    int answer = 0;

    dp = new ArrayList<>();

    init(N);

    if(number == N) return 1;

    for(int i = 2; i < 9; i++) {
      Set<Integer> here = dp.get(i);
      for(int j = 1; j <= i / 2; j++) {
        calculate(here, dp.get(i - j), dp.get(j));
        calculate(here, dp.get(j), dp.get(i - j));
      }
      String nStr = String.valueOf(N);
      here.add(Integer.valueOf(nStr.repeat(i)));

      for (Integer cmp : here) {
        if(cmp == number) {
          return i;
        }
      }
    }

    return -1;
  }

  private static void calculate(Set<Integer> main, Set<Integer> a, Set<Integer> b) {
    for (int num1 : a) {
      for (int num2 : b) {
        main.add(num1 + num2);
        main.add(num1 - num2);
        main.add(num1 * num2);
        if(num2 != 0) main.add(num1 / num2);
      }
    }
  }

  private static void init(int N) {
    for(int i = 0; i < 9; i++) {
      dp.add(new HashSet<>());
    }
    dp.get(1).add(N);
  }


  public static void main(String[] args) {
    System.out.println(solution(5, 12));
  }

}
