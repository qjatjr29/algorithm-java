package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


// https://www.acmicpc.net/problem/15683
public class 감시 {

  private static final int EMPTY = 0;
  private static final int WALL = 6;
  private static final int CHECK = 7;

  // 위, 오른쪽, 아래, 왼쪽
  private static int[] dx = {-1, 0, 1, 0};
  private static int[] dy = {0, 1, 0, -1};

  private static int[][][] rotate = {
      {{0}},
      {{1}, {2}, {3}, {0}}, // 1
      {{1, 3}, {0, 2}},  // 2
      {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3
      {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}}, // 4
      {{0, 1, 2, 3}} // 5
  };

  private static Cctv[] cctvs;
  private static int cctvCnt;
  private static int answer, N, M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][M];

    cctvs = new Cctv[8];

    answer = Integer.MAX_VALUE;
    int temp = N * M;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int number = Integer.parseInt(st.nextToken());

        map[i][j] = number;

        // CCTV
        if (number != EMPTY && number != WALL) {
          cctvs[cctvCnt++] = new Cctv(i, j, number);
        }
        else if(number == WALL) temp--;
      }
    }

    find(0, temp - cctvCnt, map);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  // cctv로 감시할 영역 찾기
  private static void find(int cnt, int remain, int[][] map) {

    // 사각지대 최소 값으로 갱신
    if(cnt == cctvCnt) {
      answer = Math.min(answer, remain);
      return;
    }

    int[][] newMap = new int[N][M];
    copyMap(newMap, map);

    Cctv cctv = cctvs[cnt];

    // 90도 회전
    for(int i = 0; i < rotate[cctv.number].length; i++) {
      int temp = 0;
      for(int j = 0; j < rotate[cctv.number][i].length; j++) {
        // 감시할 방향
        int dir = rotate[cctv.number][i][j];
        // 감시할 수 있는 영역 수 + 감시한 영역 표시
        temp += checkCount(cctv.x, cctv.y, dir, newMap);
      }
      // 다음 cctv로 넘어감
      find(cnt + 1, remain - temp, newMap);
      copyMap(newMap, map);
    }
  }

  // cctv의 각 방향으로 감시할 곳 찾기
  private static int checkCount(int x, int y, int dir, int[][] map) {

    int count = 0;

    while(true) {

      x += dx[dir];
      y += dy[dir];

      if(x < 0 || x >= N || y < 0 || y >= M) break;
      if(map[x][y] == WALL) break;
      if(map[x][y] == EMPTY) {
        count++;
        map[x][y] = CHECK;
      }
    }
    return count;
  }

  private static void copyMap(int[][] newMap, int[][] map) {
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < M; j++) {
        newMap[i][j] = map[i][j];
      }
    }
  }


  private static class Cctv {
    int x, y, number;

    public Cctv(int x, int y, int number) {
      this.x = x;
      this.y = y;
      this.number = number;
    }
  }

}
