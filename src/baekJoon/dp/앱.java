package baekJoon.dp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 앱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        int[] memories = new int[n];
        int[] costs = new int[n];
        App[] apps = new App[n];
        int[] dp = new int[10001]; // 가격별 확보할 수 있는 최대 메모리 바이트
        int answer = Integer.MAX_VALUE;

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memories[i] = Integer.parseInt(input.nextToken());
        }

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(input.nextToken());
        }

        for (int i = 0; i < n; i++) {
            apps[i] = new App(memories[i], costs[i]);
        }

        for (int i = 0; i < n; i++) {
            App app = apps[i];
            int cost = app.cost;
            int memory = app.memory;

            for (int j = 10000; j >= cost; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost] + memory);
            }
        }

        for (int i = 0; i < 10001; i++) {
            if (dp[i] >= m) {
                answer = i;
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class App {
        int memory;
        int cost;

        public App(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }
    }
}
