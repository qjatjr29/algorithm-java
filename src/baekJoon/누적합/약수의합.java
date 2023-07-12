package baekJoon.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 약수의합 {

  private static final int NATURAL_NUMBER = 1000000;
  private static long[] measure;
  private static long[] dp;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());

    measure = new long[NATURAL_NUMBER + 1];
    dp = new long[NATURAL_NUMBER + 1];

    Arrays.fill(measure, 1);
    setDivisors();
    setSumOfDivisors();

    for(int testcase = 0; testcase < T; testcase++) {

      st = new StringTokenizer(br.readLine());
      int naturalNumber = Integer.parseInt(st.nextToken());
      bw.write(String.valueOf(dp[naturalNumber]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();
  }

  private static void setDivisors() {
    for(int i = 2; i <= NATURAL_NUMBER; i++) {
      for(int j = 1; i * j <= NATURAL_NUMBER; j++) {
        measure[i * j] += i;
      }
    }
  }

  private static void setSumOfDivisors() {
    for(int i = 1; i <= NATURAL_NUMBER; i++) {
      dp[i] = dp[i - 1] + measure[i];
    }
  }

}

// 1 -> 4 -> 8 -> 15
// 1 = 1
// 1 + 2 = 3
// 1 + 3 = 4
// 1 + 2 + 4 = 7
// 1 + 5  =  6
// 1 + 2 + 3 + 6 = 12
//
