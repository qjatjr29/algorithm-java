package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 암호코드 {

    private static final int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        String password = input.nextToken();
        int[] dp = new int[password.length() + 1];

        dp[0] = 1;

        for(int i = 1; i <= password.length(); i++) {

            // 현재 글자
            int cAlpha = password.charAt(i - 1) - '0';
            if(cAlpha >= 1 && cAlpha <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= INF;
            }

            if(i == 1) continue;

            // 두 개의 숫자가 되는지 확인
            // 이전 글자
            int pAlpha = password.charAt(i - 2) - '0';
            if(pAlpha == 0) continue;

            int twoAlpha = pAlpha * 10 + cAlpha;

            if(twoAlpha >= 10 && twoAlpha <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= INF;
            }
        }

        bw.write(String.valueOf(dp[password.length()]));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
