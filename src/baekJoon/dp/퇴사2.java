package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        // 1 ~ n + 1 까지의 받을 수 있는 최대 금액
        int[] dp = new int[n + 2];

        // 각 날짜에 맞는 상담 정보 (기간과 비용)
        int[] days = new int[n + 2];
        int[] profits = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            input = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(input.nextToken());
            profits[i] = Integer.parseInt(input.nextToken());
        }

        int totalProfit = 0;
        for (int i = 1; i <= n; i++) {

            // i번째 날에 얻을 수 있는 누적 금액이 지금까지의 누적 금액보다 크다면 갱신
            if (totalProfit < dp[i]) totalProfit = dp[i];

            // i 번째 날에 상담이 가능한 경우
            if (i + days[i] <= n + 1) {
                dp[i + days[i]] = Math.max(dp[i + days[i]], totalProfit + profits[i]);
            }
        }

        int answer = Math.max(totalProfit, dp[n + 1]);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
