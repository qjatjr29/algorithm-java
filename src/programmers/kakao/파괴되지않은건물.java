package programmers.kakao;

public class 파괴되지않은건물 {

  private static int[][] sum;

  public static int solution(int[][] board, int[][] skill) {
    int answer = 0;

    int row = board.length;
    int col = board[0].length;

    sum = new int[row + 1][col + 1];

    for(int i = 0; i < skill.length; i++) {

      int type = skill[i][0] == 1 ? -1 : 1;
      int x1 =skill[i][1];
      int y1 =skill[i][2];
      int x2 =skill[i][3];
      int y2 =skill[i][4];

      int value = skill[i][5] * type;

      sum[x1][y1] += value;
      sum[x1][y2 + 1] -= value;
      sum[x2 + 1][y2 + 1] += value;
      sum[x2 + 1][y1] -= value;
    }

    for(int i = 1; i < row; i++) {
      for(int j = 0; j < col; j++) {
        sum[i][j] += sum[i - 1][j];
      }
    }

    for(int j = 1; j < col; j++) {
      for(int i = 0; i < row; i++) {
        sum[i][j] += sum[i][j - 1];
      }
    }

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        if(sum[i][j] + board[i][j] > 0) answer++;
      }
    }
    return answer;
  }


  public static void main(String[] args) {


    int[][] board = {
        {5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5},
        {5, 5, 5, 5, 5}};

    int[][] skill = {
        {1, 0, 0, 3, 4, 4},
        {1, 2, 0, 2, 3, 2},
        {2, 1, 0, 3, 1 ,2},
        {1, 0, 1, 3, 3, 1}};

    System.out.println(solution(board, skill));


  }

}
