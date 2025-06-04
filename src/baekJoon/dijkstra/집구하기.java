package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 집구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(input.nextToken());
        int E = Integer.parseInt(input.nextToken());

        List<int[]>[] roads = new List[V + 1];

        for (int i = 1; i <= V; i++) {
            roads[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            input = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());
            int w = Integer.parseInt(input.nextToken());
            roads[u].add(new int[] {v, w});
            roads[v].add(new int[] {u, w});
        }

        input = new StringTokenizer(br.readLine());
        int md = Integer.parseInt(input.nextToken());
        int maxDistance = Integer.parseInt(input.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[V + 1];
        int[] mdDistance = new int[V + 1];
        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < md; i++) {
            int mdPos = Integer.parseInt(input.nextToken());
            pq.add(new int[] {mdPos, 0});
            mdDistance[mdPos] = 0;
        }

        // 각 맥도날드에서 시작
        while(!pq.isEmpty()) {
            int[] here = pq.poll();
            int number = here[0];
            int distance = here[1];

            if (distance > maxDistance) continue;

            if (visited[number]) {
                continue;
            }

            visited[number] = true;
            mdDistance[number] = distance;

            for (int[] road : roads[number]) {
                if (!visited[road[0]]) {
                    pq.add(new int[] { road[0], distance + road[1] });
                }
            }
        }

        input = new StringTokenizer(br.readLine());
        int sd = Integer.parseInt(input.nextToken());
        maxDistance = Integer.parseInt(input.nextToken());

        pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        visited = new boolean[V + 1];
        int[] sdDistance = new int[V + 1];
        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < sd; i++) {
            int sdPos = Integer.parseInt(input.nextToken());
            pq.add(new int[] {sdPos, 0});
            mdDistance[sdPos] = 0;
        }

        // 각 스타벅스에서 시작
        while(!pq.isEmpty()) {
            int[] here = pq.poll();
            int number = here[0];
            int distance = here[1];

            if (distance > maxDistance) continue;

            if (visited[number]) {
                continue;
            }

            visited[number] = true;
            sdDistance[number] = distance;

            for (int[] road : roads[number]) {
                if (!visited[road[0]]) {
                    pq.add(new int[] { road[0], distance + road[1] });
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {

            if (sdDistance[i] == 0 || mdDistance[i] == 0) {
                continue;
            }

            int sum = sdDistance[i] + mdDistance[i];
            answer = Math.min(answer, sum);
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
