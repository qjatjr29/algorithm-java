package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9184
public class 신나는함수실행 {

  private static final int EXIT_NUMBER = -1;
  private static final int NUMBER = 20;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[][][] dp = new int[NUMBER + 1][NUMBER + 1][NUMBER + 1];

    for(int i = 0; i <= NUMBER; i++) {

    }

    for(int i = 0; i <= NUMBER; i++) {
      for(int j = 0; j <= NUMBER; j++) {
        for(int z = 0; z <= NUMBER; z++) {
          if(i == 0 || j == 0 || z == 0) {
            dp[i][j][z] = 1;
            continue;
          }
          if(i < j && j < z) {
            dp[i][j][z] = dp[i][j][z - 1] + dp[i][j - 1][z - 1] - dp[i][j - 1][z];
          }
          else {
            dp[i][j][z] = dp[i - 1][j][z]  + dp[i - 1][j - 1][z] + dp[i - 1][j][z - 1] - dp[i - 1][j - 1][z - 1];
          }
        }
      }
    }


    while(true) {

      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      if(a == EXIT_NUMBER && b == EXIT_NUMBER && c == EXIT_NUMBER) break;

      StringBuilder sb = new StringBuilder();
      sb.append("w(");
      sb.append(a);
      sb.append(", ");
      sb.append(b);
      sb.append(", ");
      sb.append(c);
      sb.append(") = ");
      bw.write(sb.toString());
      if(a <= 0 || b <= 0 || c <= 0) {
        bw.write(String.valueOf(dp[0][0][0]));
        bw.newLine();
      }

      else if(a > NUMBER || b > NUMBER || c > NUMBER) {
        bw.write(String.valueOf(dp[NUMBER][NUMBER][NUMBER]));
        bw.newLine();
      }

      else {
        bw.write(String.valueOf(dp[a][b][c]));
        bw.newLine();
      }

    }

    bw.flush();
    bw.close();
    br.close();

  }

}
