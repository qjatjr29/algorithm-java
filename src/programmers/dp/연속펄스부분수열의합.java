package programmers.dp;

public class 연속펄스부분수열의합 {
  public long solution(int[] sequence) {

    long answer = 0;
    int length = sequence.length;

    long[][] dp = new long[2][length];

    dp[0][0] = sequence[0];
    dp[1][0] = sequence[0] * -1;
    answer = Math.max(dp[0][0], dp[1][0]);

    int type = -1;
    for(int i = 1; i < length; i++) {

      // 1 -1 1... 순으로 진행 중인 경우의 현재 펄스 값
      int value = type * sequence[i];

      // 이전까지 진행된 펄스 부분 수열 dp[i - 1] + value 와
      // 현재 펄스 값의 크기를 비교
      dp[0][i] = Math.max(value, dp[0][i - 1] + value);

      type *= -1;

      // -1 1 -1 ... 순으로 진행
      value = type * sequence[i];
      dp[1][i] = Math.max(value, dp[1][i - 1] + value);

      answer = Math.max(answer, Math.max(dp[0][i], dp[1][i]));
    }

    return answer;
  }

}
