package baekJoon.greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 뉴스전하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parentId = Integer.parseInt(input.nextToken());

            if (parentId == -1) continue;
            adj[parentId].add(i);
        }

        int time = dfs(0, adj);

        bw.write(String.valueOf(time));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int idx, List<Integer>[] adj) {
        int time = 0;

        int[] times = new int[adj[idx].size()];

        for (int i = 0; i < adj[idx].size(); i++) {
            int next = adj[idx].get(i);
            // 직속 부하가 전화하는 시간을 구한다.
            times[i] = dfs(next, adj);
        }

        Arrays.sort(times);

        for (int i = times.length - 1; i >= 0; i--) {
            time = Math.max(time, times[i] + (times.length - i));
        }

        return time;
    }
}
