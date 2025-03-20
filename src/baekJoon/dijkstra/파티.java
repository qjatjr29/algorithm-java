package baekJoon.dijkstra;

import java.io.*;
import java.util.*;

public class 파티 {

    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int target = Integer.parseInt(input.nextToken());

        List<Node>[] adj = new List[n + 1];
        List<Node>[] reverseAdj = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            reverseAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int distance = Integer.parseInt(input.nextToken());
            adj[a].add(new Node(b, distance));
            reverseAdj[b].add(new Node(a, distance));
        }

        dist = new int[n + 1];
        bfs(target, adj);
        bfs(target, reverseAdj);

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (i == target) continue;
            answer = Math.max(answer, dist[i]);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int start, List<Node>[] adj) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        boolean[] visited = new boolean[adj.length];

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node here = pq.poll();

            int point = here.point;
            int distance = here.distance;

            if (visited[point]) continue;
            visited[point] = true;
            dist[point] += distance;

            for (Node next : adj[point]) {
                if (!visited[next.point]) {
                    pq.add(new Node(next.point, distance + next.distance));
                }
            }
        }
    }

    private static class Node {
        int point;
        int distance;

        Node(int point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }
}
