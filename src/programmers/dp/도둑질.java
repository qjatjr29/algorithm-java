package programmers.dp;

// https://school.programmers.co.kr/learn/courses/30/lessons/42897
public class 도둑질 {

  public int solution(int[] money) {
    int answer = 0;

    // 첫번째 집 훔친 경우 -> 마지막집과 인접해있음.
    int[] dp1 = new int[money.length];

    // 안 훔친 경우
    int[] dp2 = new int[money.length];

    if(money.length == 3) {
      answer = Math.max(money[1], money[0] + money[2]);
      return answer;
    }

    // 첫번째 집만 훔친경우
    dp1[0] = money[0];
    dp2[0] = 0;

    // 두번째 집 (첫번째 집과 인접해있음)
    dp1[1] = money[0];
    dp2[1] = money[1];


    // 현재 위치의 집을 도둑질 하거나 안하거나로 나누어 계산
    // 현재 집을 도둑질 - dp[i - 2] + money[i];
    // 현재 집을 도둑질 하지 않음 - dp[i - 1];
    for(int i = 2; i < money.length; i++) {
      dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
      dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
    }

    answer = Math.max(dp1[money.length - 2], dp2[money.length - 1]);

    return answer;
  }

}
