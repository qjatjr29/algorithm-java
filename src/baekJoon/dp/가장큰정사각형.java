package baekJoon.dp;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 가장큰정사각형 {

  private static int N, M, answer;
  private static int[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    dp = new int[N + 1][M + 1];
    answer = 0;
    if(N == 1 && M == 1) answer = 1;

    for(int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      String[] str = st.nextToken().split("");
      for(int j = 1; j <= M; j++) {
        int num = Integer.parseInt(str[j - 1]);
        if(i == 1 && j == 1) dp[i][j] = num;
        else {
          if(num == 1) {
            dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
            answer = max(answer, dp[i][j]);
          }
        }
      }
    }
    answer = answer * answer;

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
  }

}
