package baekJoon.graph.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 적록색약 {

  private static Character[][] zone;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};
  private static int size;
  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    size = Integer.parseInt(st.nextToken());

    zone = new Character[size][size];
    visited = new boolean[size][size];

    for(int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      String row = st.nextToken();
      for(int j = 0; j < size; j++) {
        zone[i][j] = row.charAt(j);
      }
    }

    int normal = 0;
    int color_weakness = 0;

    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        if(!visited[i][j]) {
          normal++;
          normalDfs(i, j, zone[i][j]);
        }
      }
    }
    visited = new boolean[size][size];
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        if(!visited[i][j]) {
          color_weakness++;
          weaknessDfs(i, j, zone[i][j]);
        }
      }
    }

    bw.write(String.valueOf(normal) + " ");
    bw.write(String.valueOf(color_weakness));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
  private static void normalDfs(int x, int y, char target) {

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx < 0 || nx >= size || ny < 0 || ny >= size)
        continue;
      if (visited[nx][ny] || target != zone[nx][ny])
        continue;

      visited[nx][ny] = true;
      normalDfs(nx, ny, target);
    }
  }
  private static void weaknessDfs(int x, int y, char target) {

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx < 0 || nx >= size || ny < 0 || ny >= size)
        continue;
      if (visited[nx][ny]) continue;
      if((target == zone[nx][ny] )
          || (target == 'R' && zone[nx][ny] == 'G')
          || (target == 'G' && zone[nx][ny] == 'R')) {
        visited[nx][ny] = true;
        weaknessDfs(nx, ny, target);
      }
    }
  }


}
