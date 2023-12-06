package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전2 {

    private static final int INF = 10001;
    private static final int MAX_COIN_VALUE = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int type = Integer.parseInt(input.nextToken());
        int targetMoney = Integer.parseInt(input.nextToken());

        int[] coins = new int[type];
        int[] coinDp = new int[MAX_COIN_VALUE];
        int answer;

        for(int i = 0; i <= targetMoney; i++) {
            coinDp[i] = INF;
        }

        for(int i = 0; i < type; i++) {
            input = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(input.nextToken());
            coinDp[coins[i]] = 1;
        }

        Arrays.sort(coins);

        for(int i = 1; i <= targetMoney; i++) {
            for(int j = 0; j < type; j++) {
                if(coins[j] > i) break;
                coinDp[i] = Math.min(coinDp[i], coinDp[i - coins[j]] + 1);
            }
        }

        answer = coinDp[targetMoney];
        if(coinDp[targetMoney] == INF) answer = -1;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
