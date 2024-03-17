package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _123더하기5 {

    private static final int INF = 1000000009;
    private static final int MAX_NUMBER = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int testcase = Integer.parseInt(input.nextToken());

        long[][] dp = new long[MAX_NUMBER + 1][4];

        calculate(dp);

        for(; testcase > 0; testcase--) {
            input = new StringTokenizer(br.readLine());
            int targetNumber = Integer.parseInt(input.nextToken());
            long answer = (dp[targetNumber][1] + dp[targetNumber][2] + dp[targetNumber][3]) % INF;
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void calculate(long[][] dp) {
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i = 4; i < dp.length; i++) {
            // i의 수를 만들기 위해 1을 마지막에 더하는 경우
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % INF;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % INF;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % INF;
        }
    }
}
