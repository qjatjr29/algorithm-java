package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 알약 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input;

        long[][] dp = new long[31][31];
        dp[1][0] = 1;

        for(int w = 0; w <= 30; w++) {
            for(int h = 0; h <= 30; h++) {
                if (w < 30) {
                    dp[w + 1][h] += dp[w][h];
                }
                if (h < w) {
                    dp[w][h + 1] += dp[w][h];
                }
            }
        }

        while(true) {
            input = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(input.nextToken());
            if (N == 0) {
                break;
            }
            bw.write(String.valueOf(dp[N][N]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
