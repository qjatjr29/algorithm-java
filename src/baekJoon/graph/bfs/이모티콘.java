package baekJoon.graph.bfs;

import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 이모티콘 {

  private static final int INF = 987654321;
  private static int S;
  private static int[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    S = Integer.parseInt(st.nextToken());

    dp = new int[2222][2222];

    for(int i = 0; i < 2222; i++) {
      for(int j = 0; j < 2222; j++) {
        dp[i][j] = -1;
      }
    }

    int answer = bfs(0, 1);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static int bfs(int clipBoard, int cnt) {
    if(cnt > 2 * S || clipBoard > 2 * S) return INF;
    if(cnt < 0) return INF;
    if(cnt == S) return 0;

    if(dp[clipBoard][cnt] != -1) return dp[clipBoard][cnt];
    dp[clipBoard][cnt] = INF;

    // 복사 (덮어쓰기)
    dp[clipBoard][cnt] = min(dp[clipBoard][cnt], bfs(cnt, cnt) + 1);
    // 하나 삭제
    dp[clipBoard][cnt] = min(dp[clipBoard][cnt], bfs(clipBoard, cnt - 1) + 1);
    // 붙여넣기
    dp[clipBoard][cnt] = min(dp[clipBoard][cnt], bfs(clipBoard, cnt + clipBoard) + 1);

    return dp[clipBoard][cnt];
  }

}
