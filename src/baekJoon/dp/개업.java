package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 개업 {

    private static final int MAX_AMOUNT = 10001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int orderCount = Integer.parseInt(input.nextToken());
        int wokCount = Integer.parseInt(input.nextToken());

        int[] woks = new int[wokCount + 1];
        List<Integer> wokList = new ArrayList<>();
        int[] dp = new int[orderCount + 1];

        Arrays.fill(dp, MAX_AMOUNT);
        dp[0] = 0;
        woks[0] = 1;

        input = new StringTokenizer(br.readLine());

        for(int i = 0; i < wokCount; i++) {
            int amount = Integer.parseInt(input.nextToken());
            woks[i] = amount;
            wokList.add(amount);
        }

        for(int i = 0; i < wokCount; i++) {
            for(int j = i + 1; j < wokCount; j++) {
                if(woks[i] + woks[j] <= orderCount) {
                    wokList.add(woks[i] + woks[j]);
                }
            }
        }

        Collections.sort(wokList);

        for(int i = 1; i <= orderCount; i++) {
            for(int j = 0; j < wokList.size(); j++) {
                int amount = wokList.get(j);
                if(i < amount) break;
                if(i == amount) dp[i] = 1;
                else dp[i] = Math.min(dp[i], dp[i - amount] + 1);
            }
        }

        int answer = dp[orderCount];
        if(answer == MAX_AMOUNT) answer = -1;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

}
