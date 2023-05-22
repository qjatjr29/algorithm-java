package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 상범빌딩 {

  private static final Character START_POINT = 'S';
  private static final Character END_POINT = 'E';
  private static final Character EMPTY = '.';
  private static final Character CAN_NOT_POINT = '#';

  private static char[][][] building;
  private static boolean[][][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;


    while(true) {
      st = new StringTokenizer(br.readLine());
      int height = Integer.parseInt(st.nextToken());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());

      if(height == 0 && row == 0 && col == 0) {
        break;
      }
      int answer = 0;
      int[] dh = {-1, 1};
      int[] dx = {-1, 1, 0, 0};
      int[] dy = {0, 0, -1, 1};

      building = new char[height][row][col];
      visited = new boolean[height][row][col];

      Node start = new Node();
      Node end = new Node();

      for(int h = 0; h < height; h++) {
        for(int r = 0; r < row; r++) {
          st = new StringTokenizer(br.readLine());
          String rowStr = st.nextToken();
//          System.out.println(rowStr);
          for(int c = 0; c < col; c++) {
            building[h][r][c] = rowStr.charAt(c);
            if(building[h][r][c] == START_POINT) {
              start.h = h;
              start.x = r;
              start.y = c;
              visited[h][r][c] = true;
            }
            if(building[h][r][c] == END_POINT) {
              end.h = h;
              end.x = r;
              end.y = c;
            }
          }
        }
        st = new StringTokenizer(br.readLine());
      }

      PriorityQueue<Node> pq = new PriorityQueue<>();
      pq.add(start);

      while(!pq.isEmpty()) {

        Node here = pq.poll();

        int ch = here.h;
        int cx = here.x;
        int cy = here.y;
        int count = here.count;

        if(ch == end.h && cx == end.x && cy == end.y) {
          answer = count;
          break;
        }

        // 층
        for(int i = 0; i < 2; i++) {
          int nh = ch + dh[i];
          if(nh < 0 || nh >= height) continue;
          if(visited[nh][cx][cy]) continue;
          if(building[nh][cx][cy] == CAN_NOT_POINT) continue;
          visited[nh][cx][cy] = true;
          pq.add(new Node(nh, cx, cy, count + 1));
        }

        // 같은 층에서 움직임
        for(int i = 0; i < 4; i++) {
          int nx = cx + dx[i];
          int ny = cy + dy[i];
          if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
          if(visited[ch][nx][ny]) continue;
          if(building[ch][nx][ny] == CAN_NOT_POINT) continue;
          visited[ch][nx][ny] = true;
          pq.add(new Node(ch, nx, ny, count + 1));
        }
      }

      if(answer == 0) {
        bw.write("Trapped!");
        bw.newLine();
      }
      else {
        bw.write("Escaped in " + String.valueOf(answer) +" minute(s).");
        bw.newLine();
      }

    }

    bw.flush();
    bw.close();
    br.close();

  }

  private static class Node implements Comparable<Node> {
    int h;
    int x;
    int y;
    int count;

    Node() {}

    Node(int h, int x, int y, int count) {
      this.h = h;
      this.x = x;
      this.y = y;
      this.count = count;
    }

    @Override
    public int compareTo(Node o) {
      return this.count - o.count;
    }
  }

}
