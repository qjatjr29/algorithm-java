package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class 내려가기 {

    private static int[][] board;

    private static Score[][] dp;

    private static int[] idx = { -1, 0, 1 };

    private static class Score {
        int minScore, maxScore;
        Score(int minScore, int maxScore) {
            this.minScore = minScore;
            this.maxScore = maxScore;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        board = new int[N + 1][5];
        dp = new Score[N + 1][5];

        for(int i = 0; i < N + 1; i++) {
            for(int j = 0; j < 5; j++) {
                board[i][j] = 1000000;
                dp[i][j] = new Score(1000000, -1);
            }
        }

        int minAnswer = Integer.MAX_VALUE;
        int maxAnswer = Integer.MIN_VALUE;

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(i == 1) {
                    dp[i][j] = new Score(board[i][j], board[i][j]);
                    if(N == 1) {
                        minAnswer = min(minAnswer, board[i][j]);
                        maxAnswer = max(maxAnswer, board[i][j]);
                    }
                }
            }
        }

        for(int i = 2; i <= N; i++) {
            for(int j = 1; j <= 3; j++) {
                for(int z = 0; z < 3; z++) {
                    dp[i][j].minScore = min(dp[i][j].minScore, dp[i - 1][j + idx[z]].minScore + board[i][j]);
                    dp[i][j].maxScore = max(dp[i][j].maxScore, dp[i - 1][j + idx[z]].maxScore + board[i][j]);
                }
                if(i == N) {
                    minAnswer = min(minAnswer, dp[i][j].minScore);
                    maxAnswer = max(maxAnswer, dp[i][j].maxScore);
                }
            }
        }

        bw.write(String.valueOf(maxAnswer));
        bw.write(" ");
        bw.write(String.valueOf(minAnswer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
