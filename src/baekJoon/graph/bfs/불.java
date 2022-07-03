package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {

  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};
  private static int w, h, answer;
  private static char[][] map;
  private static boolean[][] visited;
  private static Queue<Board> people;
  private static Queue<Board> fires;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int t = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < t; testcase++) {

      st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());

      map = new char[h][w];
      fires = new LinkedList<>();
      people = new LinkedList<>();
      visited = new boolean[h][w];
      answer = -1;

      for(int i = 0; i < h; i++) {
        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        for(int j = 0; j < w; j++) {
          map[i][j] = s.charAt(j);
          visited[i][j] = true;
          if(map[i][j] == '@') {
            people.add(new Board(i, j));
          } else if(map[i][j] == '*') {
            fires.add(new Board(i, j));
          } else if(map[i][j] == '.') {
            visited[i][j] = false;
          }
        }
      }

      sol();
      if(answer == -1) bw.write("IMPOSSIBLE");
      else bw.write(String.valueOf(answer + 1));
      bw.newLine();
    }
    bw.flush();
    bw.close();
  }

  private static void sol() {
    int result = 0;
    while(true) {
      if(answer != -1) break;
      if(people.isEmpty()) break;
      moveFire();
      move(result);
      result++;
    }
  }

  private static void move(int count) {
    int size = people.size();
    for(int i = 0; i < size; i++) {
      if(people.isEmpty()) break;
      Board here = people.poll();
      int cx = here.x;
      int cy = here.y;
      if(isAnswer(cx, cy)) {
        answer = count;
        break;
      }

      for(int j = 0; j < 4; j++) {
        int nx = cx + dx[j];
        int ny = cy + dy[j];
        if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
        if(visited[nx][ny]) continue;
        if(isAnswer(nx, ny)) {
          answer = count + 1;
          return;
        }
        visited[nx][ny] = true;
        people.add(new Board(nx, ny));
      }
    }
  }

  private static void moveFire() {
    int size = fires.size();
    for(int i = 0; i < size; i++) {
      if(fires.isEmpty()) break;
      Board fire = fires.poll();
      int fx = fire.x;
      int fy = fire.y;

      for(int j = 0; j < 4; j++) {
        int nx = fx + dx[j];
        int ny = fy + dy[j];
        if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
        if(visited[nx][ny]) continue;
        visited[nx][ny] = true;
        fires.add(new Board(nx, ny));
      }
    }
  }

  private static boolean isAnswer(int x, int y) {
    if(x == 0 || x == h - 1) return true;
    return y == 0 || y == w - 1;
  }

  private static class Board {
    int x, y;

    public Board(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
