package algospot.dp;

import java.io.*;
import java.util.StringTokenizer;

// https://www.algospot.com/judge/problem/read/JUMPGAME
public class 외발뛰기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());
        for (int testCases = 0; testCases < T; testCases++) {

            input = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(input.nextToken()); // 마을 수
            int[][] board = new int[n][n];
            int[][] dp = new int[n][n];

            for (int i = 0; i < n; i++) {
                input = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(input.nextToken());
                    dp[i][j] = 10001;
                }
            }

            dp[0][0] = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int jump = board[i][j];
                    if (i + jump < board.length) {
                        dp[i + jump][j] = Math.min(dp[i + jump][j], dp[i][j] + 1);
                    }
                    if (j + jump < board[i].length) {
                        dp[i][j + jump] = Math.min(dp[i][j + jump], dp[i][j] + 1);
                    }
                }
            }
            if (dp[n - 1][n - 1] != 10001) {
                bw.write("YES");
            }
            else bw.write("NO");
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
