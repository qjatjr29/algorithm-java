package programmers.graph;
import java.util.*;

public class 미로탈출 {

  public int solution(String[] maps) {
    int answer = 0;

    int row = maps.length;
    int col = maps[0].length();

    Node start, lb, exit;
    start = new Node();
    lb = new Node();
    exit = new Node();

    for(int i = 0; i < row; i++) {
      String s = maps[i];
      for(int j = 0; j < col; j++) {
        char c = s.charAt(j);
        if(c == 'S') {
          start = new Node(i, j, c);
        }
        else if(c == 'E') {
          exit = new Node(i, j, c);
        }
        else if(c == 'L') {
          lb = new Node(i, j, c);
        }
      }
    }

    boolean[][] visited = new boolean[row][col];
    PriorityQueue<Node> q = new PriorityQueue<>();
    q.add(start);
    visited[start.x][start.y] = true;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    while(!q.isEmpty()) {

      Node here = q.poll();

      int cx = here.x;
      int cy = here.y;
      int count = here.count;

      if(cx == lb.x && cy == lb.y) {
        answer += count;
        break;
      }

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
        if(visited[nx][ny]) continue;
        if(maps[nx].charAt(ny) == 'X') continue;
        visited[nx][ny] = true;
        q.add(new Node(nx, ny, maps[nx].charAt(ny) , count + 1));
      }
    }
    if(answer == 0) return -1;

    visited = new boolean[row][col];
    q = new PriorityQueue<>();
    visited[lb.x][lb.y] = true;
    q.add(lb);
    int temp = 0;

    while(!q.isEmpty()) {

      Node here = q.poll();

      int cx = here.x;
      int cy = here.y;
      int count = here.count;

      if(cx == exit.x && cy == exit.y) {
        temp += count;
        break;
      }

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
        if(visited[nx][ny]) continue;
        if(maps[nx].charAt(ny) == 'X') continue;
        visited[nx][ny] = true;
        q.add(new Node(nx, ny, maps[nx].charAt(ny) , count + 1));
      }
    }
    if(temp == 0) return -1;
    answer += temp;

    return answer;
  }
  private class Node implements Comparable<Node> {
    int x, y;
    char data;
    int count;
    Node(){}
    Node(int x, int y, char data) {
      this.x = x;
      this.y = y;
      this.data = data;
      this.count = 0;
    }
    Node(int x, int y, char data, int count) {
      this.x = x;
      this.y = y;
      this.data = data;
      this.count = count;
    }
    @Override
    public int compareTo(Node o) {
      return this.count - o.count;
    }
  }

}
