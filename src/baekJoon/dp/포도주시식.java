package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 포도주시식 {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        answer = 0;
        int drinkCount = Integer.parseInt(input.nextToken());

        int[] drinks = new int[drinkCount + 1];
        int[][] dp = new int[drinkCount + 1][3];

        for(int i = 1; i <= drinkCount; i++) {
            input = new StringTokenizer(br.readLine());
            drinks[i] = Integer.parseInt(input.nextToken());
        }

        for(int i = 1; i <= drinkCount; i++) {
            // 포도주 마시는 경우
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + drinks[i]);
            dp[i][2] = Math.max(dp[i][2], dp[i - 1][1] + drinks[i]);

            // 마시지 않는 경우
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][0]);
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][1]);
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][2]);
        }

        answer = Math.max(dp[drinkCount][0], dp[drinkCount][1]);
        answer = Math.max(answer, dp[drinkCount][2]);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
