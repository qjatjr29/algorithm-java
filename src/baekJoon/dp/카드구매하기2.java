package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 카드구매하기2 {

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int cardCount = Integer.parseInt(input.nextToken());
        int[] cards = new int[cardCount + 1];
        int[] dp = new int[cardCount + 1];

        input = new StringTokenizer(br.readLine());

        for(int i = 1; i <= cardCount; i++) {
           cards[i] = Integer.parseInt(input.nextToken());
        }

        dp[0] = 0;
        dp[1] = cards[1];

        for(int i = 2; i <= cardCount; i++) {
            dp[i] = cards[i];
            for(int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
            }
        }

        bw.write(String.valueOf(dp[cardCount]));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
