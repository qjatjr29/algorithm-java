package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 설탕배달 {

    private static final int[] bags = {3, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int targetKg = Integer.parseInt(input.nextToken());
        int[] dp = new int[targetKg + 1];

        for(int i = 1; i <= targetKg; i++) {
            dp[i] = 5001;
        }

        for(int i = 1; i <= targetKg; i++) {
            for(int bag : bags) {
                if(i - bag >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - bag] + 1);
                }
            }
        }

        int answer = dp[targetKg];
        if(answer == 5001) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
