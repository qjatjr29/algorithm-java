package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2206
public class 벽부수고이동하기 {

  private static final int WALL = 1;
  private static final int EMPTY = 0;

  private static int[][] board;
  private static boolean[][][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int answer = -1;

    board = new int[N][M];
    visited = new boolean[2][N][M];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      String str = st.nextToken();
      for(int j = 0; j < M; j++) {
        board[i][j] = (int) str.charAt(j) - '0';
      }
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    visited[0][0][0] = true;
    visited[1][0][0] = true;

    PriorityQueue<Position> queue = new PriorityQueue<>();
    queue.add(new Position(0, 0, 1, 1));

    while(!queue.isEmpty()) {

      Position here = queue.poll();
      int cx = here.x;
      int cy = here.y;
      int canBreak = here.canBreak;
      int cnt = here.count;

      if(cx == N - 1 && cy == M - 1) {
        answer = cnt;
        break;
      }

      for(int i = 0; i < 4; i++) {

        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

        if(board[nx][ny] == WALL && canBreak == 1) {
          if(!visited[0][nx][ny]) {
            visited[0][nx][ny] = true;
            queue.add(new Position(nx, ny, 0,  cnt + 1));
          }
        }

        if(board[nx][ny] == EMPTY && !visited[canBreak][nx][ny]) {
          visited[canBreak][nx][ny] = true;
          queue.add(new Position(nx, ny, canBreak, cnt + 1));
        }

      }

    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Position implements Comparable<Position> {
    int x, y;
    // 0 이면 이미 벽을 깬 경우, 1은 아직 안 꺤 경우
    int canBreak;
    int count;

    public Position(int x, int y, int canBreak, int count) {
      this.x = x;
      this.y = y;
      this.canBreak = canBreak;
      this.count = count;
    }

    @Override
    public int compareTo(Position o) {
      return this.count - o.count;
    }
  }

}
