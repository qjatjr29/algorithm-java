package baekJoon.graph;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 다리만들기 {

  private static int N, answer, landCnt;
  private static int[][] map;
  private static boolean[][] visited;
  private static List<Check> land;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, 1, -1};

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    visited = new boolean[N][N];
    land = new ArrayList<>();
    answer = Integer.MAX_VALUE;

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        if(map[i][j] == 1 && !visited[i][j]) {
          bfs(i, j);
        }
      }
    }


    for(int i = 0; i < land.size(); i++) {
      int number = land.get(i).where;
      int cx = land.get(i).x;
      int cy = land.get(i).y;

      for(int j = i + 1; j < land.size(); j++) {
        int cmp = land.get(j).where;
        int cmpX = land.get(j).x;
        int cmpY = land.get(j).y;
        if(number == cmp) continue;
        int dist = abs(cx - cmpX) + abs(cy - cmpY) - 1;
        answer = min(answer, dist);
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
  private static void bfs(int x, int y) {
    Queue<Point> q = new LinkedList<>();
    q.add(new Point(x, y));
    visited[x][y] = true;
    landCnt++;
    while(true) {
      if(q.isEmpty()) break;
      Point here = q.poll();
      int cx = here.x;
      int cy = here.y;
      boolean chk = false;
      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
        if(map[nx][ny] == 0) {
          chk = true;
          continue;
        }
        if(visited[nx][ny]) continue;
        visited[nx][ny] = true;
        q.add(new Point(nx, ny));
      }
      if(chk) {
        land.add(new Check(cx, cy, landCnt));
      }
    }

  }
  private static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static class Check {
    int x, y, where;

    public Check(int x, int y, int where) {
      this.x = x;
      this.y = y;
      this.where = where;
    }
  }
}
