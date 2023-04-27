package programmers.dp;

import java.util.ArrayList;
import java.util.List;

public class 억억단을외우자 {

  public int[] solution(int e, int[] starts) {
    List<Integer> answer = new ArrayList<>();

    // 등장하는 횟수
    int[] count = new int[e + 1];

    // 가장 많이 등장..
    int[] dp = new int[e + 1];

    for(int number = 1; number <= e; number++) {
      for(int i = 1; i <= (e / number); i++) {
        count[number * i]++;
      }
    }

    int maxCount = 0;
    int maxNumber = 0;

    for(int i = e; i >= 1; i--) {
      maxNumber = maxCount > count[i] ? maxNumber : i;
      maxCount = Math.max(maxCount, count[i]);
      dp[i] = maxNumber;
    }

    for(int start : starts) answer.add(dp[start]);

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }

}
