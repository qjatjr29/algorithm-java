package baekJoon.Implementation;

import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 사탕게임 {

  private static int[][] board;
  private static int answer;

  public static void main(String[] args) throws IOException {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    board = new int[N][N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      String row = st.nextToken();

      for(int j = 0; j < N; j++) {
        char color = row.charAt(j);
        board[i][j] = color;
      }
      checkMaxRow(i);
    }

    for(int i = 0; i < N; i++) checkMaxCol(i);

    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        if(j + 1 < N) {
          swap(i, j, i , j + 1);
          checkMaxRow(i);
          checkMaxCol(j);
          checkMaxCol(j + 1);
          swap(i, j, i, j + 1);
        }
        if(i + 1 < N) {
          swap(i, j, i + 1 , j);
          checkMaxRow(i);
          checkMaxCol(j);
          checkMaxRow(i + 1);
          swap(i, j, i + 1, j);
        }
      }
    }
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void swap (int x1, int y1, int x2, int y2) {
    int temp = board[x1][y1];
    board[x1][y1] = board[x2][y2];
    board[x2][y2] = temp;
  }

  private static void checkMaxRow(int row) {
    int count = 1;
    int maxCount = 1;
    int curValue = board[row][0];
    for(int i = 1; i < board.length; i++) {
      if(curValue == board[row][i]) count++;
      else {
        maxCount = max(maxCount, count);
        count = 1;
        curValue = board[row][i];
      }
    }
    maxCount = max(maxCount, count);
    answer = max(maxCount, answer);
  }

  private static void checkMaxCol(int col) {
    int count = 1;
    int maxCount = 1;
    int curValue = board[0][col];
    for(int i = 1; i < board.length; i++) {
      if(curValue == board[i][col]) count++;
      else {
        maxCount = max(maxCount, count);
        count = 1;
        curValue = board[i][col];
      }
    }
    maxCount = max(maxCount, count);
    answer = max(maxCount, answer);
  }


}
