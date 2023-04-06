package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3067
public class Coins {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < T; testcase++) {

      st = new StringTokenizer(br.readLine());
      int coinCount = Integer.parseInt(st.nextToken());
      int[] coins = new int[coinCount + 1];

      st = new StringTokenizer(br.readLine());
      for(int i = 1; i <= coinCount; i++) {
        coins[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      int target = Integer.parseInt(st.nextToken());

      int[] result = new int[target + 1];

      result[0] = 1;

      for(int i = 1; i <= coinCount; i++) {
        for(int j = coins[i]; j <= target; j++) {

          // i 번째 동전을 사용해서 j의 값을 내는 방법
          // j 의 값을 내는 방법
          result[j] += result[j - coins[i]];

        }
      }
      bw.write(String.valueOf(result[target]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
  }

}
