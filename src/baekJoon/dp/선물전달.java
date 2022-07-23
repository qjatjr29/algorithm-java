package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 선물전달 {

  private static final int DIVIDE = 1000000000;
  private static int N;
  private static long[] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    dp = new long[1000001];
    dp[2] = 1;
    dp[3] = 2;
    for(int i = 4; i <= N; i++) {
      dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
      dp[i] %= DIVIDE;
    }

    bw.write(String.valueOf(dp[N]));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
