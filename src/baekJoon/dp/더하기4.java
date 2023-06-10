package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15989
public class 더하기4 {

  private static final int MAX_NUMBER = 10000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] dp = new int[MAX_NUMBER + 1];

    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 3;

    for(int i = 4; i <= MAX_NUMBER; i++) {
      dp[i] = dp[i - 3] + i / 2 + 1;
    }

    int T = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < T; testcase++) {

      st = new StringTokenizer(br.readLine());
      int target = Integer.parseInt(st.nextToken());

      bw.write(String.valueOf(dp[target]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();

  }

}
