package baekJoon.graph.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 빵집 {

  private static int R, C, answer;
  private static char[][] map;
  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    map = new char[R + 1][C + 1];
    visited = new boolean[R + 1][C + 1];

    for(int i = 1; i <= R; i++) {
      st = new StringTokenizer(br.readLine());
      String col = st.nextToken();
      for(int j = 1; j <= C; j++) {
        map[i][j] = col.charAt(j - 1);
      }
    }

    for(int i = 1; i <= R; i++) {
        dfs(i, 1);
    }
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }
  private static boolean dfs(int x, int y) {
    // 기저
//    if(x <= 0 || x > R || y > C) return false;
//    if(map[x][y] == 'x') return false;
//    if(visited[x][y]) return false;
    if(y == C) {
      answer++;
      visited[x][y] = true;
      return true;
    }

    for(int i = 0; i < 3; i++) {
      int nx = x + i - 1;
      int ny = y + 1;
      if(nx <= 0 || nx > R || ny > C) continue;
      if(visited[nx][ny]) continue;
      if(map[nx][ny] == 'x') continue;
      if(dfs(x + i - 1, y + 1)) {
        visited[x][y] = true;
        return true;
      }
    }
    visited[x][y] = true;
    return false;
  }

}
