package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 진우의달여행 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] map = new int[N + 1][M + 2];
        int[][][] dp = new int[N + 1][M + 2][3];

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= M + 1; j++) {
                map[i][j] = 100000001;
                dp[i][j][0] = 100000001;
                dp[i][j][1] = 100000001;
                dp[i][j][2] = 100000001;
            }
        }

        for(int i = 1; i <= N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j + 1][1] + map[i][j]);
                dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j + 1][2] + map[i][j]);

                dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][j][0] + map[i][j]);
                dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][j][2] + map[i][j]);

                dp[i][j][2] = Math.min(dp[i][j][2], dp[i - 1][j - 1][0] + map[i][j]);
                dp[i][j][2] = Math.min(dp[i][j][2], dp[i - 1][j - 1][1] + map[i][j]);

            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= M; i++) {
            answer = Math.min(answer, dp[N][i][0]);
            answer = Math.min(answer, dp[N][i][1]);
            answer = Math.min(answer, dp[N][i][2]);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
