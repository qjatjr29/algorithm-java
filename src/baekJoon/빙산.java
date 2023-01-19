package baekJoon;

// https://www.acmicpc.net/problem/2573

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산 {

  private static int row, col;
  private static boolean[][] visited;
  private static Iceberg[][] icebergs;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    icebergs = new Iceberg[row][col];
    visited = new boolean[row][col];

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        icebergs[i][j] = new Iceberg();
      }
    }

    for(int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < col; j++) {
        int ice = Integer.parseInt(st.nextToken());
        icebergs[i][j].x = i;
        icebergs[i][j].y = j;
        icebergs[i][j].height = ice;
        if(ice == 0) {
          for(int z = 0; z < 4; z++) {
            int nx = i + dx[z];
            int ny = j + dy[z];
            if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
            icebergs[nx][ny].borderOnSeaCnt++;
          }
        }
      }
    }

    int time = -1;
    int chunk = 0;
    while(true) {
      visited = new boolean[row][col];
      chunk = 0;
      for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
          if(icebergs[i][j].height != 0 && !visited[i][j]) {
            melt(i, j);
            chunk++;
          }
        }
      }

      time++;
      if(chunk == 0) {
        time = 0;
        break;
      }
      if(chunk >= 2) break;
    }

    bw.write(String.valueOf(time));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void melt(int x, int y) {
    Queue<Iceberg> icebergQueue = new LinkedList<>();
    Queue<Node> removeIce = new LinkedList<>();
    icebergQueue.add(icebergs[x][y]);
    visited[x][y] = true;

    while(!icebergQueue.isEmpty()) {

      Iceberg here = icebergQueue.poll();
      int c_x = here.x;
      int c_y = here.y;
      int c_height = here.height;
      int c_seaCnt = here.borderOnSeaCnt;

      int n_height = Math.max(c_height - c_seaCnt, 0);
      here.height = n_height;
      if(n_height == 0) removeIce.add(new Node(c_x, c_y));

      for(int i = 0; i < 4; i++) {
        int nx = c_x + dx[i];
        int ny = c_y + dy[i];
        if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
        if(visited[nx][ny]) continue;
        if(icebergs[nx][ny].height == 0) continue;

        visited[nx][ny] = true;
        icebergQueue.add(icebergs[nx][ny]);
      }
    }
    while (!removeIce.isEmpty()) {
      Node remove = removeIce.poll();
      for(int i = 0; i < 4; i++) {
        int nx = remove.x + dx[i];
        int ny = remove.y + dy[i];
        if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
        icebergs[nx][ny].borderOnSeaCnt++;
      }
    }
  }

  private static class Node {
    int x, y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static class Iceberg {
    int x, y;
    int height;
    int borderOnSeaCnt;

    public Iceberg() {
      this.height = 0;
      this.borderOnSeaCnt = 0;
    }
  }

}
