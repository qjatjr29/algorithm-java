package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
  private static final char MINE = 'M';
  private static final char EMPTY = 'E';
  private static final char BLANK = 'B';
  private static final char BOMB = 'X';

  public char[][] updateBoard(char[][] board, int[] click) {
    return click(click[0], click[1], board);
  }

  private char[][] click(int clickX, int clickY, char[][] board) {
    int row = board.length;
    int col = board[0].length;
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    if(board[clickX][clickY] == MINE) {
      board[clickX][clickY] = BOMB;
      return board;
    }


    boolean[][] visited = new boolean[row][col];
    visited[clickX][clickY] = true;

    Queue<Square> queue = new LinkedList<>();
    queue.add(new Square(clickX, clickY));

    while(!queue.isEmpty()) {

      Square square = queue.poll();
      int cx = square.x;
      int cy = square.y;

      int adjacentMineCount = getAdjacentMine(cx, cy, board);

      if(adjacentMineCount == 0) {
        board[cx][cy] = BLANK;
        for(int i = 0; i < 8; i++) {
          int nx = cx + dx[i];
          int ny = cy + dy[i];
          if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
          if(visited[nx][ny]) continue;
          if(board[nx][ny] == MINE) continue;
          visited[nx][ny] = true;
          queue.add(new Square(nx, ny));
        }
      }

      else {
        board[cx][cy] = (char) ('0' + adjacentMineCount);
      }

    }

    return board;
  }

  private int getAdjacentMine(int x, int y, char[][] board) {

    int row = board.length;
    int col = board[0].length;
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    int count = 0;

    for(int i = 0; i < 8; i++) {
      int targetX = x + dx[i];
      int targetY = y + dy[i];

      if(targetX < 0 || targetX >= row || targetY < 0 || targetY >= col) continue;
      if(board[targetX][targetY] == MINE) count++;
    }

    return count;
  }

  private class Square {
    int x, y;
    Square(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
