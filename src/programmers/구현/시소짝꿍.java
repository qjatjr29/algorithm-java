package programmers.구현;

import java.util.*;

public class 시소짝꿍 {
  private static int[] distance = {2, 3, 4};
  private static Map<Double, Integer> counts;

  public long solution(int[] weights) {
    long answer = 0;

    counts = new HashMap<>();
    Arrays.sort(weights);

    for(int i = 0; i < weights.length; i++) {
      answer += getPairCount(weights[i]);
    }

    return answer;
  }

  private int getPairCount(int number) {
    int result = 0;
    double number1 = number * 1.0; // 해당 number와 같은 값이 있는 경우
    double number2 = (number * 2.0) / 3.0; // number * 2 == x * 3 이 있는 경우
    double number3 = (number * 1.0) / 2.0; // number == x * 2 가 있는 경우
    double number4 = (number * 3.0) / 4.0; // number * 3 == x * 4 가 있는 경우
    if(counts.containsKey(number1)) result += counts.get(number1);
    if(counts.containsKey(number2)) result += counts.get(number2);
    if(counts.containsKey(number3)) result += counts.get(number3);
    if(counts.containsKey(number4)) result += counts.get(number4);
    counts.put((number * 1.0), counts.getOrDefault((number * 1.0), 0) + 1);

    return result;
  }
}
