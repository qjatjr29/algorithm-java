package programmers.graph;

import java.util.*;

public class 무인도여행 {

  private static final char SEA = 'X';

  private static List<Integer> answer;
  private static int map[][];
  private static boolean visited[][];

  public int[] solution(String[] maps) {
    answer = new ArrayList<>();

    int row = maps.length;
    int col = maps[0].length();

    map = new int[row][col];
    visited = new boolean[row][col];

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        char m = maps[i].charAt(j);
        if(m == SEA) map[i][j] = -1;
        else map[i][j] = (int) m - '0';
      }
    }

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        if(map[i][j] != -1 && !visited[i][j]) bfs(i, j);
      }
    }

    if(answer.size() == 0) return new int[] {-1};

    Collections.sort(answer);

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }

  private void bfs(int x, int y) {
    Queue<Island> q = new LinkedList<>();

    q.add(new Island(x, y));
    visited[x][y] = true;

    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};

    int day = map[x][y];

    while(!q.isEmpty()) {

      Island island = q.poll();
      int cx = island.x;
      int cy = island.y;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
        if(visited[nx][ny]) continue;
        if(map[nx][ny] == -1) continue;
        visited[nx][ny] = true;
        q.add(new Island(nx, ny));
        day += map[nx][ny];
      }

    }

    answer.add(day);

  }

  private class Island {
    int x, y;
    Island(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
