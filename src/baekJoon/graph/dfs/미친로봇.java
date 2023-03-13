package baekJoon.graph.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1405
public class 미친로봇 {

  private static int N;
  private static double[] percents;
  private static double answer;
  private static boolean[][] visited;
  // 동-서-남-북 이동
  private static int[] dx = {0, 0, 1, -1};
  private static int[] dy = {1, -1, 0, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    percents = new double[4];

    N = Integer.parseInt(st.nextToken());

    for(int i = 0; i < 4; i++) {
      percents[i] = Double.parseDouble(st.nextToken()) * 0.01;
    }

    // 중앙 좌표 - (N, N)
    // 중앙에서 사방으로 이동 => 배열의 크기를 두배씩으로 잡는다.
    visited = new boolean[2 * N + 1][2 * N + 1];
    visited[N][N] = true;

    dfs(0, N, N, 1);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void dfs(int count, int x, int y, double percent) {

    // 로봇이 모두 이동한 경우
    if(count == N) {
      answer += percent;
      return;
    }

    visited[x][y] = true;

    // 현재 위치에서 동 - 서 - 남 - 북 이동
    for(int i = 0; i < 4; i++) {

      if(percents[i] == 0) continue;
      int nx = x + dx[i];
      int ny = y + dy[i];

      // 범위를 벗어나는 경우
      if(nx < 0 || nx >= 2 * N + 1 || ny < 0 || ny >=  2 * N + 1) continue;
      // 해당 위치로 이동할 경우 단순하지 않은 경우이므로 이동하지 않는다.
      if(visited[nx][ny]) continue;
      visited[nx][ny] = true;
      dfs(count + 1, nx, ny, percent * percents[i]);
      visited[nx][ny] = false;
    }

  }

}
