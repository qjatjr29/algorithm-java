package programmers.graph;

import java.util.PriorityQueue;

public class 게임맵최단거리 {

  private static boolean[][] visited;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};
  private static int n, m;
  private static int answer;

  public static int solution(int[][] maps) {
    answer = -1;

    n = maps.length;
    m = maps[0].length;

    visited = new boolean[n][m];

    bfs(maps, n, m);

    return answer;
  }

  private static void bfs(int[][] maps, int n, int m) {

    visited[0][0] = true;
    PriorityQueue<Position> pq = new PriorityQueue<>();
    pq.add(new Position(0, 0, 0));

    while(true) {
      if(pq.isEmpty()) break;

      Position here = pq.poll();
      int cnt = here.count;
      int cx = here.x;
      int cy = here.y;

      if(cx == n - 1 && cy == m - 1) {
        answer = cnt + 1;
        break;
      }

      for(int i = 0; i < 4; i++){

        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx >= n || nx < 0 || ny >= m || ny < 0) continue;
        if(visited[nx][ny]) continue;
        if(maps[nx][ny] == 0) continue;

        visited[nx][ny] = true;
        pq.add(new Position(cnt + 1, nx, ny));
      }
    }
  }

  private static class Position implements Comparable<Position> {
    int count;
    int x, y;

    public Position(int count, int x, int y) {
      this.count = count;
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Position o) {
      if(this.count > o.count) return 1;
      else if(this.count == o.count) return 0;
      else return -1;
    }
  }

  public static void main(String[] args) {

    int[][] maps = {
        {1, 0, 1, 1, 1},
        {1, 0, 1, 0, 1},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 0, 1},
        {0, 0, 0, 0, 1}};

    System.out.println(solution(maps));
  }

}
