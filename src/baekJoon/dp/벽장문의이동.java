package baekJoon.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 벽장문의이동 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int open1 = Integer.parseInt(input.nextToken());
        int open2 = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int targetCount = Integer.parseInt(input.nextToken());
        int[] targets = new int[targetCount];

        for (int i = 0; i < targetCount; i++) {
            input = new StringTokenizer(br.readLine());
            targets[i] = Integer.parseInt(input.nextToken());
        }

        int[][][] dp = new int[targetCount + 1][n + 1][n + 1];

        for (int i = 0; i <= targetCount; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        int temp = Math.min(open1, open2);
        open2 = Math.max(open1, open2);
        dp[0][temp][open2] = 0;

        for (int i = 1; i <= targetCount; i++) {
            int t = targets[i - 1]; // 사용해야하는 벽장
            for (int j = 1; j <= n; j++) {
                for (int k = j + 1; k <= n; k++) {
                    if (dp[i - 1][j][k] != Integer.MAX_VALUE) {
                        int dist1 = Math.abs(t - j);
                        int dist2 = Math.abs(t - k);

                        // 왼쪽 벽장을 이동
                        if (t >= k) dp[i][k][t] = Math.min(dp[i][k][t], dp[i - 1][j][k] + dist1);
                        else dp[i][t][k] = Math.min(dp[i][t][k], dp[i - 1][j][k] + dist1);

                        // 오른쪽 벽장을 이동
                        if (t >= j) dp[i][j][t] = Math.min(dp[i][j][t], dp[i - 1][j][k] + dist2);
                        else dp[i][t][j] = Math.min(dp[i][t][j], dp[i - 1][j][k] + dist2);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        int last = targets[targetCount - 1];
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, dp[targetCount][last][i]);
            answer = Math.min(answer, dp[targetCount][i][last]);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
