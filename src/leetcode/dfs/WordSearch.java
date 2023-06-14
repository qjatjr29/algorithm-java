package leetcode.dfs;

public class WordSearch {
  public boolean exist(char[][] board, String word) {

    int row = board.length;
    int col = board[0].length;

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        if(board[i][j] == word.charAt(0)) {
          boolean[][] visited = new boolean[row][col];
          if(search(i, j, 0, visited, board, word)) return true;
        }
      }
    }

    return false;
  }

  public boolean search(int x, int y, int index, boolean[][] visited, char[][] board, String word) {
    int row = board.length;
    int col = board[0].length;

    visited[x][y] = true;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    // 기저 사례 - 찾은 경우
    if(index == word.length() - 1) return true;

    boolean rtn = false;
    for(int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
      if(visited[nx][ny]) continue;
      if(board[nx][ny] != word.charAt(index + 1)) continue;
      visited[nx][ny] = true;
      rtn = search(nx, ny, index + 1, visited, board, word);
      if(rtn == true) return true;
      visited[nx][ny] = false;
    }
    return rtn;
  }
}
