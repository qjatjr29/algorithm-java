package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11909
public class 배열탈출 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int size = Integer.parseInt(st.nextToken());
    int[][] cost = new int[size][size];
    int[][] map = new int[size][size];

    for(int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < size; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        cost[i][j] = Integer.MAX_VALUE;
      }
    }

    // 이동은 오른쪽, 아래쪽으로만 갈 수 있다.
    int[] dx = {0, 1};
    int[] dy = {1, 0};


    // 시작지점부터 cost 배열을 갱신하자
    // map[i][j]을 가기 위한 최소 비용과 최소 비용을 얻기 위한 값을 저장.
    cost[0][0] = 0;
    for(int x = 0; x < size; x++) {
      for(int y = 0; y < size; y++) {
        // 오른쪽, 아래로 이동
        for(int i = 0; i < 2; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          // 범위를 넘어간 경우 (base case)
          if(nx >= size || ny >= size) continue;

          // 이동을 하기 위해 필요한 비용
          int gap = map[nx][ny] - map[x][y] + 1;
          if(gap <= 0) gap = 0;

          cost[nx][ny] = Math.min(cost[nx][ny], cost[x][y] + gap);
        }
      }
    }

    bw.write(String.valueOf(cost[size - 1][size - 1]));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }
}
