package baekJoon.graph;

import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 점프점프 {

  private static int N;
  private static int[] A;
  private static int[] dp;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    A = new int[N];
    dp = new int[N];
    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
      dp[i] = 987654321;
    }

    dp[0] = 0;

    for(int i = 0; i < N; i++) {
      int next = A[i];
      for(int j = 1; j <= next; j++) {
        if(i + j >= N) break;
        dp[i + j] = min(dp[i + j], dp[i] + 1);
      }
    }

    int answer = dp[N - 1];

    answer = answer == 987654321 ? -1 : answer;

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
