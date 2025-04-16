package baekJoon.dijkstra;

import java.io.*;
import java.util.*;

public class 인터넷설치 {

    private static List<Edge>[] adj;
    private static int n;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        n = Integer.parseInt(input.nextToken());
        int p = Integer.parseInt(input.nextToken());
        k = Integer.parseInt(input.nextToken());
        adj = new List[n + 1];

        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<Edge>();
        }

        int maxCost = 0;

        for (int i = 0; i < p; i++) {
            input = new StringTokenizer(br.readLine());
            int computer1 = Integer.parseInt(input.nextToken());
            int computer2 = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            adj[computer1].add(new Edge(computer2, cost));
            adj[computer2].add(new Edge(computer1, cost));

            // 각 루트별 비용의 최대값
            maxCost = Math.max(maxCost, cost);
        }

        int answer = findMinCost(maxCost);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findMinCost(int maxCost) {
        int left = 0;
        int right = maxCost;
        int answer = Integer.MAX_VALUE;

        while (left < right) {
            int mid = (left + right) / 2;

            // 가능한 경우
            if (dijkstra(1, n, mid, maxCost)) {
                answer = mid;
                right = mid;
            }
            else left = mid + 1;
        }
        if (answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }

    private static boolean dijkstra(int start, int targetComputer, int cost, int maxCost) {

        // i번째 컴퓨터로 가는데 cost 보다 큰 값이 몇개 필요한지
        int[] counts = new int[maxCost + 1];
        Arrays.fill(counts, Integer.MAX_VALUE);

        counts[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {

            Edge edge = pq.poll();
            int computer = edge.computer;
            int cCount = edge.cost;

            // 현재 컴퓨터를 더 작은 값으로 이미 방문한 경우
            if (counts[computer] < cCount) continue;

            for (int i = 0; i < adj[computer].size(); i++) {
                Edge next = adj[computer].get(i);
                int nComputer = next.computer;
                int nCost = next.cost;

                // 다음 컴퓨터로 가는데 공짜로 사용하는 선의 개수
                int nCount = cCount;

                // cost보다 큰 값이라면 공짜로 사용하는 선의 개수를 하나 사용.
                if (nCost > cost) {
                    nCount++;
                }

                // 다음 컴퓨터로 가는데 공짜로 사용하는 선의 개수를 갱신
                if (counts[nComputer] > nCount) {
                    counts[nComputer] = nCount;
                    pq.add(new Edge(nComputer, nCount));
                }
            }
        }
        return counts[targetComputer] <= k; // cost 보다 큰 비용이 k개보다 작은지 아닌지 리턴
    }

    private static class Edge implements Comparable<Edge> {
        int computer;
        int cost;

        public Edge(int computer, int cost) {
            this.computer = computer;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
