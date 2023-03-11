package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4179
public class 불_4179 {

  private static char[][] map;
  private static final Character WALL = '#';
  private static final Character FIRE = 'F';
  private static final Character CAN_MOVE = '.';
  private static final Character JIHOON = 'J';

  private static Queue<Position> fireQueue;
  private static Queue<Position> jihoonQueue;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    map = new char[R][C];
    fireQueue = new LinkedList<>();
    jihoonQueue = new LinkedList<>();

    for(int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      String str = st.nextToken();
      for(int j = 0 ; j < C; j++) {
        map[i][j] = str.charAt(j);
        if(map[i][j] == JIHOON) jihoonQueue.add(new Position(i, j));
        if(map[i][j] == FIRE) fireQueue.add(new Position(i, j));
      }
    }

    int time = 0;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    // fire, jihoon의 queue는 매시간마다 움직인다.
    // 최근에 움직인 것만 생각하면 된다.
    // =>  queue에서 poll 해버린다.
    while(true) {
      time++;

      int fireSize = fireQueue.size();
      int jhSize =  jihoonQueue.size();

      while(fireSize > 0) {
        fireSize--;
        Position fire = fireQueue.poll();
        int x = fire.x;
        int y = fire.y;

        for(int i = 0; i < 4; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
          if(map[nx][ny] == JIHOON || map[nx][ny] == CAN_MOVE) {
            fireQueue.add(new Position(nx, ny));
            map[nx][ny] = FIRE;
          }
        }
      }

      while(jhSize > 0) {

        jhSize--;
        Position jihoon = jihoonQueue.poll();

        int x = jihoon.x;
        int y = jihoon.y;

        for(int i = 0; i < 4; i++) {

          int nx = x + dx[i];
          int ny = y + dy[i];
          // 탈출!
          if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
            bw.write(String.valueOf(time));
            bw.newLine();
            bw.flush();
            bw.close();
            br.close();
            return;
          }
          if(map[nx][ny] == CAN_MOVE) {
            jihoonQueue.add(new Position(nx, ny));
            map[nx][ny] = JIHOON;
          }
        }
      }

      if(jihoonQueue.isEmpty()) {
        bw.write("IMPOSSIBLE");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
        return;
      }
    }
  }

  private static class Position {
    int x, y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
