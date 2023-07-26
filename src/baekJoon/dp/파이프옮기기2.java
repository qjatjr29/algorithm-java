package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 파이프옮기기2 {

  private static final int EMPTY = 0;
  private static final int WALL = 1;
  private static final int ROW_STATE = 0;
  private static final int COL_STATE = 1;
  private static final int DIAGONAL_STATE = 2;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int size = Integer.parseInt(st.nextToken());

    int[][] board = new int[size][size];
    long[][][] dp = new long[size][size][3];

    for(int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < size; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dp[0][1][ROW_STATE] = 1;

    for(int i = 1; i < size; i++) {
      if(board[0][i] == WALL) break;
      dp[0][i][ROW_STATE] = 1;
    }

    for(int i = 1; i < size; i++) {
      for(int j = 1; j < size; j++) {
        if(board[i][j] == WALL) continue;
        dp[i][j][ROW_STATE] += dp[i][j - 1][ROW_STATE] + dp[i][j - 1][DIAGONAL_STATE];
        dp[i][j][COL_STATE] += dp[i - 1][j][COL_STATE] + dp[i - 1][j][DIAGONAL_STATE];

        if(board[i][j - 1] == WALL || board[i - 1][j] == WALL) continue;
        dp[i][j][DIAGONAL_STATE] +=
            dp[i - 1][j - 1][DIAGONAL_STATE] + dp[i - 1][j - 1][ROW_STATE] + dp[i - 1][j - 1][COL_STATE];
      }
    }

    long answer = dp[size - 1][size - 1][ROW_STATE]
        + dp[size - 1][size - 1][COL_STATE]
        + dp[size - 1][size - 1][DIAGONAL_STATE];


    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
