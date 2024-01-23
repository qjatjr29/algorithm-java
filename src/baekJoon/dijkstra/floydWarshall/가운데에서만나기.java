package baekJoon.dijkstra.floydWarshall;

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

public class 가운데에서만나기 {

    private static final int MAX_TIME = 1000000;
    private static int[][] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        List<Integer> answer = new ArrayList<>();
        int minTime = MAX_TIME;
        times = new int[N + 1][N + 1];

        for(int i = 0; i <= N; i++) {
            Arrays.fill(times[i], MAX_TIME);
            times[i][i] = 0;
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int sCity = Integer.parseInt(input.nextToken());
            int eCity = Integer.parseInt(input.nextToken());
            int time = Integer.parseInt(input.nextToken());
            times[sCity][eCity] = time;
        }

        floyd(N);

        input = new StringTokenizer(br.readLine());
        int friendCount = Integer.parseInt(input.nextToken());
        int[] friends = new int[friendCount];

        input = new StringTokenizer(br.readLine());

        for(int i = 0; i < friendCount; i++) {
            friends[i] = Integer.parseInt(input.nextToken());
        }

        for(int i = 1; i <= N; i++) {

            int maxTime = 0;
            for(int friend : friends) {
                int time = times[friend][i] + times[i][friend];
                maxTime = Math.max(maxTime, time);
            }

            if(minTime > maxTime) {
                minTime = maxTime;
                answer.clear();
                answer.add(i);
                continue;
            }

            if(minTime == maxTime) {
                answer.add(i);
            }
        }

        Collections.sort(answer);
        for(int ans : answer) {
            bw.write(String.valueOf(ans) + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void floyd(int size) {
        for(int i = 1; i <= size; i++) {
            for(int j = 1; j <= size; j++) {
                for(int z = 1; z <= size; z++) {
                   times[j][z] = Math.min(times[j][z], times[j][i] + times[i][z]);
                }
            }
        }
    }

}
