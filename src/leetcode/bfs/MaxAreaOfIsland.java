package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland {
  private static final int ISLAND = 1;
  private static final int WATER = 0;

  private static boolean[][] visited;

  public int maxAreaOfIsland(int[][] grid) {

    int answer = 0;
    int row = grid.length;
    int col = grid[0].length;

    visited = new boolean[row][col];

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        if(grid[i][j] == ISLAND && !visited[i][j]) {
          answer = Math.max(answer, bfs(i, j, grid));
        }
      }
    }
    return answer;
  }

  private int bfs(int x, int y, int[][] grid) {
    int size = 0;
    Queue<Grid> q = new LinkedList<>();
    q.add(new Grid(x, y));
    visited[x][y] = true;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    while(!q.isEmpty()) {
      Grid here = q.poll();
      int cx = here.x;
      int cy = here.y;
      size++;
      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        if(nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) continue;
        if(visited[nx][ny] || grid[nx][ny] == WATER) continue;
        visited[nx][ny] = true;
        q.add(new Grid(nx, ny));
      }
    }

    return size;
  }

  private class Grid {
    int x, y;
    Grid(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
