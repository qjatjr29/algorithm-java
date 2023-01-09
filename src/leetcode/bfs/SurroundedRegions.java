package leetcode.bfs;

// https://leetcode.com/problems/surrounded-regions/

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {

  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};
  private static int row, col;
  private static boolean[][] visited;

  public void solve(char[][] board) {

    Queue<Region> temp = new LinkedList<>();
    row = board.length;
    col = board[0].length;
    visited = new boolean[row][col];

    for(int i = 0; i < row; i++) {
      temp.clear();
      for(int j = 0; j < col; j++) {
        if(board[i][j] == 'O' && !visited[i][j]) {
          isCaptured(i, j, temp, board);
        }
      }
    }
  }

  private boolean isBound(int x, int y) {
    return x >= 0 && x < row && y >= 0 && y < col;
  }

  private void isCaptured(int x, int y, Queue<Region> temp, char[][] board) {
    boolean isCheck = true;
    for(int i = 0; i < dx.length; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(!isBound(nx, ny)) {
        isCheck = false;
        continue;
      }
    }

    LinkedList<Region> capturedRegions = new LinkedList<>();
    Region start = new Region(x, y);
    temp.add(start);
    capturedRegions.add(start);
    visited[x][y] = true;

    while (!temp.isEmpty()) {

      Region here = temp.poll();
      int cx = here.x;
      int cy = here.y;

      for(int i = 0; i < dx.length; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(!isBound(nx, ny)) {
         isCheck = false;
          continue;
        }
        if(board[nx][ny] == 'O' && !visited[nx][ny]) {
          visited[nx][ny] = true;
          Region next = new Region(nx, ny);
          temp.add(next);
          capturedRegions.add(next);
        }
      }
    }

    if(isCheck) {
      for (Region region : capturedRegions) {
        board[region.x][region.y] = 'X';
      }
    }
  }

  private class Region {
    int x, y;

    public Region(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) {

    char[][] b = {
        {'O', 'O', 'O'},
        {'O', 'O', 'O'},
        {'O', 'O', 'O'}
    };

//    solve(b);

  }

}
