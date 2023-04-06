package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14728
public class 벼락치기 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int time = Integer.parseInt(st.nextToken());
    Unit[] units = new Unit[N + 1];
    int[][] dp = new int[N + 1][time + 1];

    for(int i = 1; i <= N; i++) {

      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      int score = Integer.parseInt(st.nextToken());
      units[i] = new Unit(i, t, score);
    }

    for(int i = 1; i < units.length; i++) {
      Unit unit = units[i];
      for(int j = 1; j <= time; j++) {

        if(unit.studyTime <= j) {
          dp[i][j] = Math.max(dp[i - 1][j - unit.studyTime] + unit.score, dp[i - 1][j]);
        }
        else dp[i][j] = dp[i - 1][j];
      }
    }

    bw.write(String.valueOf(dp[N][time]));
    bw.newLine();
    bw.flush();
    bw.close();


  }
  private static class Unit {
    int number;
    int studyTime;
    int score;

    public Unit(int number, int studyTime, int score) {
      this.number = number;
      this.studyTime = studyTime;
      this.score = score;
    }
  }

}
