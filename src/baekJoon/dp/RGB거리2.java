package baekJoon.dp;

// https://www.acmicpc.net/problem/17404

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class RGB거리2 {

  // I 번째 집에 j 번째 색으로 색칠했을 때 최소값.
  private static int[][] dp;
  private static int[][] houses;
  private static final int INF = 1000 * 1000;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int house_cnt = Integer.parseInt(st.nextToken());
    dp = new int[house_cnt + 1][3];
    houses = new int[house_cnt + 1][3];
    int answer = INF;

    for(int i = 1; i <= house_cnt; i++) {
      st = new StringTokenizer(br.readLine());

      for(int j = 0; j < 3; j++) {
        houses[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // RED - GREEN - BLUE
    for(int start = 0; start < 3; start++) {
      for(int i = 0; i < 3; i++) {
        // 해당 색으로 칠하는 경우가 아니면 큰 값으로 초기화
        if(i == start) dp[1][i] = houses[1][i];
        else dp[1][i] = INF;
      }

      for(int i = 2; i <= house_cnt; i++) {
        dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + houses[i][0];
        dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + houses[i][1];
        dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + houses[i][2];
      }

      for(int i = 0; i < 3; i++) {
        if(i != start) answer = Math.min(answer, dp[house_cnt][i]);
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
