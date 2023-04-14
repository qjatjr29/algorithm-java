package baekJoon.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2468
public class 안전영역 {

  private static int[][] map;
  private static int answer = 1;
  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    map = new int[N][N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 1; i <= 100; i++) {
      findRegion(i);
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void bfs(int sx, int sy, int height) {

    Queue<Node> queue = new LinkedList<>();
    visited[sx][sy] = true;
    queue.add(new Node(sx, sy));

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    while(!queue.isEmpty()) {
      Node node = queue.poll();

      int cx = node.x;
      int cy = node.y;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= map.length || ny < 0 || ny >= map.length) continue;
        if(visited[nx][ny]) continue;
        if(map[nx][ny] <= height) continue;
        visited[nx][ny] = true;
        queue.add(new Node(nx, ny));
      }
    }
  }

  private static void findRegion(int minHeight) {

    visited = new boolean[map.length][map.length];
    int count = 0;

    for(int i = 0; i < map.length; i++) {
      for(int j = 0; j < map.length; j++) {
        if(map[i][j] > minHeight && !visited[i][j]) {
          count++;
          bfs(i, j, minHeight);
        }
      }
    }

    answer = Math.max(answer, count);

  }

  private static class Node {
    int x, y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }

  }

}
