package baekJoon.dp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 우수마을 {

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int[] people = new int[N + 1];
        List<Integer>[] adj = new List[N + 1];
        dp = new int[N + 1][2];

        input = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            people[i] = Integer.parseInt(input.nextToken());

            // i 번째 도시를 우수도시로 설정했을 때
            dp[i][1] = people[i];
        }

        for (int i = 0; i < N - 1; i++) {
            input = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(input.nextToken());
            int c2 = Integer.parseInt(input.nextToken());
            adj[c1].add(c2);
            adj[c2].add(c1);
        }

        dfs(1, -1, adj, people);
        int answer = Math.max(dp[1][0], dp[1][1]);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int idx, int prev, List<Integer>[] adj, int[] people) {

        dp[idx][0] = 0;
        dp[idx][1] = people[idx];

        // 다음 자식 노드들
        for (int next : adj[idx]) {

            if (next == prev) continue;

            // 다음 마을(자식 노드)로 이동
            dfs(next, idx, adj, people);

            // 현재 마을을 선택하지 않으면, 다른 자식노드들의 (선택, 선택하지않음) 2가지 경우중 최대값을 더해준다.
            dp[idx][0] = dp[idx][0] + Math.max(dp[next][0], dp[next][1]);
            // 현재 마을을 선택하면, 다른 자식 노드들이 선택하지 않아야 한다. 모두 더해준다.
            dp[idx][1] = dp[idx][1] + dp[next][0];
        }
    }
}
