package baekJoon.dijkstra;

import java.io.*;
import java.util.*;

public class 우주탐사선 {

    private static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());
        int[][] times = new int[n][n];
        time = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                times[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int z = 0; z < n; z++) {
                    if (i == j) continue;
                    times[i][j] = Math.min(times[i][j], times[i][z] + times[z][j]);
                }
            }
        }

        boolean[] visited = new boolean[n];
        visited[k] = true;
        dfs(k, 1, visited, times, 0);

        bw.write(String.valueOf(time));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(int planet, int cnt, boolean[] visited, int[][] times, int sum) {

        if (sum >= time) return;

        if (cnt == times.length) {
            time = sum;
            return;
        }

        for (int i = 0; i < times.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            dfs(i, cnt + 1, visited, times, sum + times[planet][i]);
            visited[i] = false;
        }
    }

}

