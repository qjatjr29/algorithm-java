package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 일루미네이션 {

  private static final int HOME = 1;

  private static int[][] map;
  private static boolean[][] visited;
  private static int answer;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int W = Integer.parseInt(st.nextToken());
    int H = Integer.parseInt(st.nextToken());

    map = new int[H + 2][W + 2];
    visited = new boolean[H + 2][W + 2];

    for(int i = 1; i <= H; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j <= W; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    findWall(0, 0);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void findWall(int x, int y) {

    int[] dx1 = {-1, -1, 0, 0, 1, 1};
    int[] dy1 = {0, 1, -1, 1, 0, 1};

    int[] dx2 = {-1, -1, 0, 0, 1, 1};
    int[] dy2 = {-1, 0, -1, 1, -1, 0};

    Queue<Grid> queue = new LinkedList<>();
    queue.add(new Grid(x, y));
    visited[x][y] = true;

    while(!queue.isEmpty()) {

      Grid grid = queue.poll();
      int cx = grid.x;
      int cy = grid.y;

      for(int i = 0; i < 6; i++) {
        int nx;
        int ny;
        if(cx % 2 == 1) {
          nx = cx + dx1[i];
          ny = cy + dy1[i];
        }

        else {
          nx = cx + dx2[i];
          ny = cy + dy2[i];
        }

        if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
        if(map[nx][ny] == HOME) {
          answer++;
          continue;
        }
        if(visited[nx][ny]) continue;
        visited[nx][ny] = true;
        queue.add(new Grid(nx, ny));
      }
    }
  }

  private static class Grid {
    int x, y;

    public Grid(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
