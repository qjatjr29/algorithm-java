package programmers.kakao;

import java.util.*;

public class 미로탈출명령어 {
  private static final String IMPOSSIBLE = "impossible";

  private static int[] dx = {0, 0, -1, 1};
  private static int[] dy = {-1, 1, 0, 0};
  private static Map<Integer, Character> directionMap;

  public String solution(int n, int m, int x, int y, int r, int c, int k) {

    directionMap = new HashMap<>();
    setMap();

    PriorityQueue<Path> queue = new PriorityQueue<>();
    boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];

    queue.add(new Path(x, y, 0, ""));
    visited[x][y][0] = true;

    while(!queue.isEmpty()) {

      Path here = queue.poll();
      int cx = here.x;
      int cy = here.y;
      int count = here.count;
      String str = here.path;

      if(count > k) return IMPOSSIBLE;

      // 정해진 이동거리만큼 이동
      if(count == k) {
        // 도착
        if(cx == r && cy == c) return str;
        continue;
      }

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        if(nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
        if(visited[nx][ny][count + 1]) continue;
        char direction = directionMap.get(i);
        visited[nx][ny][count + 1] = true;
        queue.add(new Path(nx, ny, count + 1, str + direction));
      }

    }

    return IMPOSSIBLE;
  }

  private class Path implements Comparable<Path> {
    int x, y;
    int count;
    String path;

    Path(int x, int y, int count, String path) {
      this.x = x;
      this.y = y;
      this.count = count;
      this.path = path;
    }

    @Override
    public int compareTo(Path o) {
      if(this.count == o.count) return this.path.compareTo(o.path);
      return this.count - o.count;
    }
  }

  private void setMap() {
    directionMap.put(0, 'l');
    directionMap.put(1, 'r');
    directionMap.put(2, 'u');
    directionMap.put(3, 'd');
  }

}
