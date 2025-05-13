package baekJoon.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 호텔 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(input.nextToken());
        int n = Integer.parseInt(input.nextToken());

        City[] cities = new City[n];
        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(input.nextToken());
            int customers = Integer.parseInt(input.nextToken());
            cities[i] = new City(cost, customers);
        }

        // i명을 홍보했을 때의 드는 돈의 최솟값
        int[] dp = new int[1101];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            City city = cities[i];
            int cost = city.cost;
            int customers = city.customers;

            for (int j = customers; j <= 1100; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - customers]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = c; i <= 1100; i++) {
            answer = Math.min(answer, dp[i]);
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class City {
        int cost;
        int customers;

        City(int cost, int customers) {
            this.cost = cost;
            this.customers = customers;
        }
    }
}
