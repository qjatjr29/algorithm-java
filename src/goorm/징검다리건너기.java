package goorm;

import java.io.*;
import java.util.*;

public class 징검다리건너기 {

    private static final int MAX_JUMP = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int stoneCount = Integer.parseInt(input.nextToken());

        int[] stones = new int[stoneCount + 1];
        int[] dp = new int[stoneCount + 1];

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= stoneCount; i++) {
            stones[i] = Integer.parseInt(input.nextToken());
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i = 1; i <= stoneCount; i++) {
            for(int j = 1; j <= MAX_JUMP; j++) {
                if(i - j < 0) break;
                dp[i] = Math.min(dp[i], dp[i - j] + stones[i]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++) {
            answer = Math.min(answer, dp[stoneCount - i]);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
