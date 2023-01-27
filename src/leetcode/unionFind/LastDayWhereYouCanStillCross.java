package leetcode.unionFind;

import java.util.Arrays;

public class LastDayWhereYouCanStillCross {

  private static int[][] map;
  private static int[] landCnt;
  private static int[] dx = {0, 0, 1, -1};
  private static int[] dy = {-1, 1, 0, 0};

  public int latestDayToCross(int row, int col, int[][] cells) {

    landCnt = new int[row];
    map = new int[row][col];
    int top = row * col;
    int bottom = row * col + 1;

    for (int[] r : map) Arrays.fill(r, -1);

    Union un = new Union(row * col + 2);

    for(int i = 0; i < col; i++) {
      un.union(top, 0 * col + i);
      un.union(bottom, (row - 1) * col + i);
    }

    int len = cells.length - 1;
    while(un.find(top) != un.find(bottom) && len >= 0) {
      int[] cell = cells[len--];

      int cx = cell[0] - 1;
      int cy = cell[1] - 1;

      map[cx][cy] = 0;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        if(nx >= 0 && nx < row && ny >= 0 && ny < col && map[nx][ny] == 0) {
          un.union(cx * col + cy, nx * col + ny);
        }
      }
    }
    return len + 1;
  }

  private class Union {
    int[] parent;

    public Union(int size) {
      parent = new int[size];
      for(int i = 0; i < size; i++) {
        parent[i] = i;
      }
    }

    public int find(int x) {
      if(parent[x] != x) parent[x] = find(parent[x]);
      return parent[x];
    }

    public void union(int x, int y) {
      parent[find(x)] = find(y);
    }

  }

  private void checkAble(int x, int y, int row, int col) {

    boolean chk = false;

    for(int i = 0; i < 3; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if(nx <= 0 || nx > row || ny <= 0 || ny > col) continue;
      // 경로가 있는 경우
      if(map[nx][ny] == 0) {
        chk = true;
        break;
      }
    }
    // 경로가 다 막힘
    if(!chk) {
      map[x][y] = 1;
      landCnt[x]++;
    }
  }

}
