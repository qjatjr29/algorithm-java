package programmers.구현;

import static java.lang.Math.min;

public class 행렬테두리회전하기 {

  private static int[][] map;

  public static int[] solution(int rows, int columns, int[][] queries) {
    int[] answer = new int[queries.length];

    map = new int[rows + 1][columns + 1];

    setup(rows, columns);

    for(int i = 0; i < queries.length; i++) {
      int[] temp = queries[i];
      int sx = temp[0];
      int sy = temp[1];
      int ex = temp[2];
      int ey = temp[3];

      answer[i] = findMinValue(sx, sy, ex, ey);
      moveContainer(sx, sy, ex, ey);
    }
    return answer;
  }

  private static void setup(int row, int column) {
    int value = 1;
    for(int i = 1; i <= row; i++) {
      for(int j = 1; j <= column; j++) {
        map[i][j] = value;
        value++;
      }
    }
  }

  private static void moveContainer(int sX, int sY, int eX, int eY) {
    // 오른쪽 이동
    int rightUp = map[sX][eY];
    for(int i = eY - 1; i >= sY; i--) {
      map[sX][i + 1] = map[sX][i];
    }
    // 아래 이동
    int rightDown = map[eX][eY];
    for(int i = eX; i > sX + 1; i--) {
      map[i][eY] = map[i - 1][eY];
    }
    map[sX + 1][eY] = rightUp;
    // 왼쪽 이동
    int leftDown = map[eX][sY];
    for(int i = sY; i < eY - 1; i++) {
      map[eX][i] = map[eX][i + 1];
    }
    map[eX][eY - 1] = rightDown;
    // 위 이동
    for(int i = sX; i < eX - 1; i++) {
      map[i][sY] = map[i + 1][sY];
    }
    map[eX - 1][sY] = leftDown;
  }

  private static int findMinValue(int sx, int sy, int ex, int ey) {
    int rtn = 101;
    for(int i = sy; i <= ey; i++) {
      rtn = min(rtn, map[sx][i]);
      rtn = min(rtn, map[ex][i]);
    }
    for(int i = sx; i <= ex; i++) {
      rtn = min(rtn, map[i][sy]);
      rtn = min(rtn, map[i][ey]);
    }
    return rtn;
  }


  public static void main(String[] args) {

//    int rows = 6;
//    int columns = 6;
//    int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};

    int rows = 3;
    int columns = 3;
    int[][] queries = {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}};

//    int rows = 100;
//    int columns = 97;
//    int[][] queries = {{1, 1, 100 ,97}};

    int[] result = solution(rows, columns, queries);

    for (int i : result) {
      System.out.print(i + " ");
    }
    System.out.println();
  }
}
