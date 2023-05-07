package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12852
public class 만들기1로2 {

  private static List<Integer>[] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int number = Integer.parseInt(st.nextToken());

    dp = new List[number + 1];

    for(int i = 0; i <= number; i++) {
      dp[i] = new ArrayList<>();
    }

    dp[1].add(1);

    for(int i = 2; i <= number; i++) {

      dp[i] = new ArrayList<>(dp[i - 1]);
      dp[i].add(i);

      if(i % 3 == 0) {
        if(dp[i].size() > dp[i / 3].size() + 1) {
          dp[i] = new ArrayList<>(dp[i / 3]);
          dp[i].add(i);
        }
      }

      if(i % 2 == 0) {
        if(dp[i].size() > dp[i / 2].size() + 1) {
          dp[i] = new ArrayList<>(dp[i / 2]);
          dp[i].add(i);
        }
      }
    }

    List<Integer> list = dp[number];

    bw.write(String.valueOf(list.size() - 1));
    bw.newLine();
    for(int i = list.size() - 1; i >= 0; i--) {
      bw.write(list.get(i) + " ");
    }
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
}
