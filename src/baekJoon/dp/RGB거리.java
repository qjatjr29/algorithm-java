package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class RGB거리 {

    private static int[][] dp;
    private static int[][] cost;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dp = new int[N][3];
        cost = new int[N][3];

        for(int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        for(int i = 1; i < N; i++) {
            dp[i][0] = cost[i][0] + min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = cost[i][1] + min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = cost[i][2] + min(dp[i - 1][0], dp[i - 1][1]);
        }
        int answer = min(dp[N - 1][0], dp[N - 1][1]);
        answer = min(answer, dp[N - 1][2]);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
