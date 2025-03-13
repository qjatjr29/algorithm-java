package baekJoon.dijkstra;

import java.io.*;
import java.util.*;

public class 최단경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        String IMPOSSIBLE = "INF";
        int v = Integer.parseInt(input.nextToken());
        int e = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(input.nextToken());

        List<Path>[] adj = new List[v + 1];

        for (int i = 1; i <= v; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            input = new StringTokenizer(br.readLine());
            int u1 = Integer.parseInt(input.nextToken());
            int u2 = Integer.parseInt(input.nextToken());
            int w = Integer.parseInt(input.nextToken());
            adj[u1].add(new Path(u2, w));
        }

        PriorityQueue<Path> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v + 1];
        int[] answer = new int[v + 1];
        Arrays.fill(answer, 987654321);
        pq.add(new Path(start, 0));

        while (!pq.isEmpty()) {

            Path here = pq.poll();

            int x = here.x;
            int w = here.w;

            if (visited[x]) continue;
            answer[x] = w;
            visited[x] = true;

            for (Path next : adj[x]) {
                int nx = next.x;
                int nw = next.w;

                if (visited[nx] || answer[nx] <= w + nw) continue;
                pq.add(new Path(nx, w + nw));
            }
        }

        for (int i = 1; i <= v; i++) {
            if (answer[i] == 987654321) {
                bw.write(IMPOSSIBLE);
            }
            else bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Path implements Comparable<Path> {
        int x;
        int w;
        public Path(int x, int w) {
            this.x = x;
            this.w = w;
        }

        @Override
        public int compareTo(Path o) {
            return this.w - o.w;
        }
    }
}
