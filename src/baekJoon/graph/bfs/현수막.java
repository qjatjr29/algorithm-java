package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 현수막 {

  private static final int EMPTY = 0;
  private static final int CHARACTER = 1;

  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = 0;
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());

    int[][] map = new int[row][col];
    visited = new boolean[row][col];

    for(int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < col; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        if(map[i][j] == CHARACTER && !visited[i][j]) {
          bfs(i, j, map);
          answer++;
        }
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void bfs(int r, int c, int[][] map) {

    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[] {r, c});

    visited[r][c] = true;

    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    while(!queue.isEmpty()) {
      int[] here = queue.poll();

      int cx = here[0];
      int cy = here[1];

      for(int i = 0; i < 8; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
        if(visited[nx][ny]) continue;
        if(map[nx][ny] == EMPTY) continue;

        queue.add(new int[] {nx, ny});
        visited[nx][ny] = true;
      }

    }

  }

}
