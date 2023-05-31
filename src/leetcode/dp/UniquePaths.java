package leetcode.dp;

public class UniquePaths {

  private static int[][] paths;

  public int uniquePaths(int m, int n) {

    paths = new int[m][n];
    paths[0][0] = 1;

    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        calculatePath(i, j);
      }
    }
    return paths[m - 1][n - 1];
  }

  private void calculatePath(int x, int y) {

    // 위에서 올 수 있는 경우
    if (x - 1 >= 0) {
      paths[x][y] += paths[x - 1][y];
    }
    // 왼쪽에서 올 수 있는 경우
    if (y - 1 >= 0) {
      paths[x][y] += paths[x][y - 1];
    }
  }
}