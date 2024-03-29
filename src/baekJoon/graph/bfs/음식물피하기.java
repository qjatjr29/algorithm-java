package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 음식물피하기 {

  private static final int FOOD = 1;

  private static int row, col;
  private static int[][] map;
  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    int numbers = Integer.parseInt(st.nextToken());

    int answer = 0;
    map = new int[row + 1][col + 1];
    visited = new boolean[row + 1][col+ 1];

    for(int i = 0; i < numbers; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      map[r][c] = FOOD;
    }

    for(int i = 1; i <= row; i++) {
      for(int j = 1; j <= col; j++) {
        if(map[i][j] == FOOD && !visited[i][j]) {
          int cnt = bfs(i, j);
          answer = Math.max(answer, cnt);
        }
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static int bfs(int x, int y) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(new Node(x, y));
    visited[x][y] = true;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int cnt = 1;

    while(!queue.isEmpty()) {

      Node here = queue.poll();
      int cx = here.x;
      int cy = here.y;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx <= 0 || nx > row || ny <= 0 || ny > col) continue;
        if(visited[nx][ny]) continue;
        if(map[nx][ny] == FOOD) {
          queue.add(new Node(nx, ny));
          visited[nx][ny] = true;
          cnt++;
        }
      }
    }

    return cnt;
  }

  private static class Node{
    int x, y;
    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
