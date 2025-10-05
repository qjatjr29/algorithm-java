package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 포도주시식 {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        int[] juice = new int[n];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            juice[i] = Integer.parseInt(input.nextToken());
        }

        int[][] dp = new int[n][3];

        dp[0][1] = juice[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i][0], Math.max(dp[i - 1][2], Math.max(dp[i - 1][0], dp[i - 1][1])));
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + juice[i]);
            dp[i][2] = Math.max(dp[i][2], dp[i - 1][1] + juice[i]);
        }

        int answer = Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
