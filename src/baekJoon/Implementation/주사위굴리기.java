package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 주사위굴리기 {

  private static int N, M, K, x, y, top, right, up;
  private static int[] dice;
  private static int[][] map;
  private static int[] dx = {0, 0, -1, 1};
  private static int[] dy = {1, -1, 0, 0};
  private static BufferedWriter bw;

  public static void main(String[] args) throws IOException {

    bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    // 위 아래 - 0, 5
    // 서 동 -> 2, 3
    // 북 남 -> 1, 4
    dice = new int[6];
    map = new int[N][M];
    top = 0;
    right = 2;
    up = 1;

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++) {
        int number = Integer.parseInt(st.nextToken());
        map[i][j] = number;
      }
    }

    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < K; i++) {
      int cmd = Integer.parseInt(st.nextToken());
      move(cmd);
    }

    bw.flush();
    bw.close();
    br.close();
  }

  private static void move(int cmd) throws IOException {

    int nx = x + dx[cmd - 1];
    int ny = y + dy[cmd - 1];

    if(nx < 0 || nx >= N || ny < 0 || ny >= M) return;

    x = nx;
    y = ny;
    // 오른쪽 이동시 -> top : 5 - right, right : top, up : up
    // 왼쪽 이동시 - > top : right, right : 5 - top, up : up      위로 top : 4, right : 0
    // 위로 이동시 -> top : 5 - up, right : right, up: top
    // 아래로 이동시 -> top : up, right : right, up : 5 - top

    int cTop = top;
    int cRight = right;
    int cUp = up;

    switch (cmd) {
      case 1:
        top = 5 - cRight;
        right = cTop;
        break;
      case 2:
        top = cRight;
        right = 5 - cTop;
        break;
      case 3:
        top = 5 - cUp;
        up = cTop;
        break;
      case 4:
        top = cUp;
        up = 5 - cTop;
        break;
      default:
        break;
    }

    if(map[nx][ny] == 0) {
      map[nx][ny] = dice[5 - top];
    }
    else {
      dice[5 - top] = map[nx][ny];
      map[nx][ny] = 0;
    }

    bw.write(String.valueOf(dice[top]));
    bw.newLine();
  }

}
