package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보물섬 {

  private static Character[][] map;
  private static int answer;
  private static boolean[][] visited;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());

    map = new Character[row][col];
    visited = new boolean[row][col];

    for(int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());

      String str = st.nextToken();

      for(int j = 0; j < str.length(); j++) {
        map[i][j] = str.charAt(j);
      }
    }
    sol();

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static class Coordination {
    int cnt, x, y;

    public Coordination(int cnt, int x, int y) {
      this.cnt = cnt;
      this.x = x;
      this.y = y;
    }
  }

  private static void sol() {
    for(int i = 0; i < map.length; i++) {
      for(int j = 0; j < map[0].length; j++) {
        if(map[i][j] == 'L') {
          bfs(i, j);
        }
      }
    }
  }

  private static void bfs(int x, int y) {
    visited = new boolean[map.length][map[0].length];
    visited[x][y] = true;

    Queue<Coordination> cq = new LinkedList<>();
    cq.add(new Coordination(0, x, y));
    while(true) {

      if(cq.isEmpty()) break;
      Coordination here = cq.poll();

      int cx = here.x;
      int cy = here.y;
      int cCount = here.cnt;

      if(answer < cCount) answer = cCount;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
        if(visited[nx][ny]) continue;
        if(map[nx][ny] == 'W') continue;

        visited[nx][ny] = true;
        cq.add(new Coordination(cCount + 1, nx, ny));
      }
    }


  }



}
