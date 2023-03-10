package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PuyoPuyo {

  private static final int ROW = 12;
  private static final int COL = 6;
  private static final char EMPTY_SPACE = '.';
  private static char[][] board;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int answer = 0;
    board = new char[ROW][COL];

    for(int i = 0; i < ROW; i++) {
      st = new StringTokenizer(br.readLine());
      String rowStr = st.nextToken();
      for(int j = 0; j < COL; j++) {
        board[i][j] = rowStr.charAt(j);
      }
    }

    // 모든 뿌요 떨어지고 -> 터지고
    // 안 터질때 까지 반복
    while(true) {

      // 뿌요 떨어진다아
      fallDown();

      int cnt = 0;
      // 뿌요 터진다아
      cnt += burst('R');
      cnt += burst('G');
      cnt += burst( 'B');
      cnt += burst( 'P');
      cnt += burst('Y');

      if(cnt == 0) break;
      answer++;
    }
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Puyo implements Comparable<Puyo> {
    int x, y;

    public Puyo(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Puyo o) {
      if(this.x == o.x) return this.y - o.y;
      return o.x - this.x;
    }
  }

  private static void fallDown() {

    for(int i = 0; i < COL; i++) {
      for(int j = ROW - 1; j >= 0; j--) {
        char c = board[j][i];
        if(c != EMPTY_SPACE) {
          board[j][i] = EMPTY_SPACE;
          int nx = j;
          for(; nx < ROW; nx++) {
            if(nx + 1 == ROW) break;
            if(board[nx + 1][i] != EMPTY_SPACE) break;
          }
          board[nx][i] = c;
        }
      }
    }
  }

  private static int burst(char puyoType) {

    boolean[][] visited = new boolean[ROW][COL];
    int cnt = 0;

    for(int i = 0; i < ROW; i++) {
      for(int j = 0; j < COL; j++) {

        if(board[i][j] == puyoType && !visited[i][j]) {
          if(bfs(i, j, puyoType, visited)) cnt++;
        }
      }
    }

    return cnt;
  }

  private static boolean bfs(int x, int y, char puyoType, boolean[][] visited) {

    Queue<Puyo> puyos = new LinkedList<>();
    Queue<Puyo> removes = new LinkedList<>();
    puyos.add(new Puyo(x, y));
    removes.add(new Puyo(x, y));

    visited[x][y] = true;

    int cnt = 0;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    while(!puyos.isEmpty()) {

      Puyo puyo = puyos.poll();
      int cx = puyo.x;
      int cy = puyo.y;
      cnt++;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= ROW || ny < 0 || ny >= COL) continue;
        if(visited[nx][ny]) continue;
        if(board[nx][ny] != puyoType) continue;

        visited[nx][ny] = true;
        puyos.add(new Puyo(nx, ny));
        removes.add(new Puyo(nx, ny));
      }
    }

    // 터짐.
    if(cnt >= 4) {
      while(!removes.isEmpty()) {
        Puyo remove = removes.poll();
        board[remove.x][remove.y] = EMPTY_SPACE;
      }
      return true;
    }

    return false;
  }

}
