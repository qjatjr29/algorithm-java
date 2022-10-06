package baekJoon.graph.bfs;

import static java.lang.Math.pow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 전쟁전투 {

  private static int N, M;
  private static boolean[][] visited;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};
  private static char[][] map;
  private static int white, blue;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    visited = new boolean[M][N];
    map = new char[M][N];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      String str = st.nextToken();
      for (int j = 0; j < str.length(); j++) {
        char c = str.charAt(j);
        map[i][j] = c;
      }
    }

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (!visited[i][j]) {
          int count = bfs(map[i][j], i, j);
          if (map[i][j] == 'W')
            white += pow(count, 2);
          else
            blue += pow(count, 2);
        }
      }
    }

    bw.write(String.valueOf(white) + " " + String.valueOf(blue));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static int bfs(char c, int x, int y) {

    int rtn = 1;
    Queue<Node> q = new LinkedList<>();
    q.add(new Node(x, y));
    visited[x][y] = true;

    while (true) {
      if (q.isEmpty())
        break;

      Node here = q.poll();
      int cx = here.x;
      int cy = here.y;

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || nx >= M || ny < 0 || ny >= N)
          continue;
        if (visited[nx][ny])
          continue;
        if (c != map[nx][ny])
          continue;

        visited[nx][ny] = true;
        q.add(new Node(nx, ny));
        rtn++;
      }
    }
    return rtn;
  }
  private static class Node {
    int x, y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}



