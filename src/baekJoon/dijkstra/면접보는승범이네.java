package baekJoon.dijkstra;

import java.io.*;
import java.util.*;

public class 면접보는승범이네 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken()); // 도시 수
        int m = Integer.parseInt(input.nextToken()); // 도로 수
        int k = Integer.parseInt(input.nextToken()); // 면접장 수

        // 단방향
        List<Node>[] adj = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());
            adj[v].add(new Node(u, c));
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        input = new StringTokenizer(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < k; i++) {
            int interview = Integer.parseInt(input.nextToken());
            pq.add(new Node(interview, 0));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int idx = node.idx;
            if (visited[idx]) continue;
            visited[idx] = true;
            dist[idx] = Math.min(dist[idx], node.distance);

            for (Node next : adj[idx]) {
                int nIdx = next.idx;
                if (visited[nIdx]) continue;
                pq.add(new Node(nIdx, node.distance + next.distance));
            }
        }

        long distance = 0;
        int city = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Long.MAX_VALUE) continue;
            if (distance < dist[i]) {
                distance = dist[i];
                city = i;
            }
        }
        bw.write(String.valueOf(city));
        bw.newLine();
        bw.write(String.valueOf(distance));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Node implements Comparable<Node> {
        int idx;
        long distance;
        public Node(int idx, long distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.distance - o.distance);
        }
    }
}
