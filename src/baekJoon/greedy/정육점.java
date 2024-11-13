package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정육점 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int[][] meats = new int[N][2];
        int[] weights = new int[N];

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            meats[i][0] = weight;
            meats[i][1] = cost;
        }

        Arrays.sort(meats, (a, b) -> {
            if(a[1] == b[1]) return b[0] - a[0];
            return a[1] - b[1];
        });

        weights[0] = meats[0][0];

        int answer = -1;

        int totalWeight = 0;
        int totalCost = 0;
        int beforeCost = 0;

        for(int i = 0; i < N; i++) {

            int[] m = meats[i];
            int weight = m[0];
            int cost = m[1];

            totalWeight += weight;

            if(beforeCost == cost) {
                totalCost += cost;
            }
            else {
                totalCost = cost;
            }

            if(totalWeight >= M) {
                if(answer == -1) {
                    answer = totalCost;
                }
                answer = Math.min(answer, totalCost);
            }

            beforeCost = cost;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
