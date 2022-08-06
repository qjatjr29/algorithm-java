package codeTest.카카오엔터프라이즈;

import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 놀러와요구름의숲 {

  private static Character[][] forest;
  private static boolean[][] visited;
  private static int answer;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, 1, -1};

  private static Position start, end;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    forest = new Character[10][10];
    visited = new boolean[10][10];
    answer = 100;

    StringTokenizer st;
    for(int i = 0; i < 10; i++) {
      st = new StringTokenizer(br.readLine());
      String row = st.nextToken();

      for(int j = 0; j < row.length(); j++) {
        char state = row.charAt(j);
        forest[i][j] = state;
        if(state == 'H') {
          start = new Position(i, j);
        }
        else if(state == 'M') {
          end = new Position(i, j);
        }
        else if(state == 'R') {
          visited[i][j] = true;
        }
      }
    }

    move(start, end);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.readLine();
  }

  private static class Position {
    int x, y;
    int count;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
      count = 0;
    }

    public Position(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }

  private static void move(Position start, Position end) {
    Queue<Position> q = new LinkedList<>();

    q.add(start);
    visited[start.x][start.y] = true;

    while(true) {
      if(q.isEmpty()) break;

      Position here = q.poll();
      int currentX = here.x;
      int currentY = here.y;
      int currentCount = here.count;

      for(int i = 0; i < 4; i ++) {
        int nextX = currentX + dx[i];
        int nextY = currentY + dy[i];

        if(nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10) continue;
        if(visited[nextX][nextY]) continue;

        if(nextX == end.x && nextY == end.y) {
          answer = min(answer, currentCount);
          continue;
        }
        visited[nextX][nextY] = true;
        q.add(new Position(nextX, nextY, currentCount + 1));
      }
    }
  }

}
