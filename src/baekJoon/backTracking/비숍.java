package baekJoon.backTracking;

// https://www.acmicpc.net/problem/1799

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 비숍 {

  // 대각선 이동
  private static int[] dx = {-1, -1, 1, 1};
  private static int[] dy = {-1, 1, -1, 1};
  private static int[][] board;
  private static int b_cnt, w_cnt;
  private static int size;

  // 비숍을 체스판에서 놓는다는것 - 검정, 흰색 자리끼리 영향을 주지 않는다.
  // 검정, 흰색 경우를 따로 계산해 더한다.
  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    size = Integer.parseInt(st.nextToken());
    board = new int[size][size];

    for(int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < size; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 체스판의 (0, 0)을 검정색으로 본다.
    boolean[][] b_visited = new boolean[size][size];
    putBlack(0, 0, 0, b_visited);
    // 체스판의 (0, 1)을 흰색으로 본다.
    boolean[][] w_visited = new boolean[size][size];
    putWhite(0, 1, 0, w_visited);

    bw.write(String.valueOf(b_cnt + w_cnt));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static boolean isCan(int x, int y, boolean[][] visited) {

    // 놓을 수 없음.
    if(board[x][y] == 0) return false;

    for(int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      while (nx >= 0 && nx < size && ny >= 0 && ny < size) {
        if (visited[nx][ny])
          return false;
        nx += dx[i];
        ny += dy[i];
      }
    }
    return true;
  }

  private static void putBlack(int x, int y, int cnt, boolean[][] visited) {

    // 검정 자리의 놓을 수 있는 최대 비숍수 갱신
    b_cnt = Math.max(b_cnt, cnt);

    // 가로가 체스판 크기 넘은 경우
    // 세로 값이 짝수인 경우 -> 다음 y 값은 1 아니면 0
    if(y >= size) {
      x += 1;
      y = (x % 2 == 0) ? 0 : 1;
    }

    if(x >= size) return;

    // 비숍을 놓을 수 있는 경우
    if(isCan(x, y, visited)) {
      visited[x][y] = true;
      // 다음 검정 칸으로 이동하기 위해 2칸씩 이동
      putBlack(x, y + 2, cnt + 1, visited);
      visited[x][y] = false;
    }

    // 비숍을 놓지 않고 그냥 넘어감
    putBlack(x, y + 2, cnt, visited);
  }

  private static void putWhite(int x, int y, int cnt, boolean[][] visited) {

    // 흰색 자리의 놓을 수 있는 최대 비숍수 갱신
    w_cnt = Math.max(w_cnt, cnt);

    // 가로가 체스판 크기 넘은 경우
    // 세로 값이 짝수인 경우 -> 다음 y 값은 1 아니면 0
    if(y >= size) {
      x += 1;
      y = (x % 2 == 0) ? 1 : 0;
    }

    if(x >= size) return;

    // 비숍을 놓을 수 있는 경우
    if(isCan(x, y, visited)) {
      visited[x][y] = true;
      // 다음 칸으로 이동하기 위해 2칸씩 이동
      putWhite(x, y + 2, cnt + 1, visited);
      visited[x][y] = false;
    }

    // 비숍을 놓지 않고 그냥 넘어감
    putWhite(x, y + 2, cnt, visited);
  }


}
