package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9084
public class 동전 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < T; testcase++) {

      st = new StringTokenizer(br.readLine());
      int coinCount = Integer.parseInt(st.nextToken());

      int[] coins = new int[coinCount];

      st = new StringTokenizer(br.readLine());
      for(int i = 0; i < coinCount; i++) {
        coins[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      int targetValue = Integer.parseInt(st.nextToken());

      int[] dp = new int[targetValue + 1];
      dp[0] = 1;

      for(int i = 0; i < coinCount; i++) {
        for(int j = coins[i]; j <= targetValue; j++) {
          dp[j] += dp[j - coins[i]];
        }
      }

      bw.write(String.valueOf(dp[targetValue]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();
  }

}
