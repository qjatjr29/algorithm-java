package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 골목대장호석_효율성1 {

    private static final int MAX_COST = 21;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int answer = MAX_COST;
        int crossCount = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int start = Integer.parseInt(input.nextToken());
        int end = Integer.parseInt(input.nextToken());
        int money = Integer.parseInt(input.nextToken());

        List<List<Connection>> connects = new ArrayList<>();

        for(int i = 0; i <= crossCount; i++) {
            connects.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int cross1 = Integer.parseInt(input.nextToken());
            int cross2 = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            connects.get(cross1).add(new Connection(cross2, cost));
            connects.get(cross2).add(new Connection(cross1, cost));
        }

        PriorityQueue<CrossPoint> pq = new PriorityQueue<>((c1, c2) -> {
            if(c1.maxCost == c2.maxCost) return c1.number - c2.number;
            return c1.maxCost - c2.maxCost;
        });

        boolean[] visited = new boolean[crossCount + 1];

        pq.add(new CrossPoint(start, 0, 0));

        while(!pq.isEmpty()) {

            CrossPoint here = pq.poll();
            int number = here.number;
            int cost = here.cost;
            int maxCost = here.maxCost;

            if(number == end) {
                answer = Math.min(answer, maxCost);
                continue;
            }
            if(visited[number]) continue;
            visited[number] = true;

            for(Connection con : connects.get(number)) {
                int next = con.next;
                int nCost = con.cost;
                if(cost + nCost > money || visited[next]) continue;
                int sum = cost + nCost;
                maxCost = Math.max(maxCost, nCost);

                pq.add(new CrossPoint(next,  sum, maxCost));
            }
        }
        if(answer == MAX_COST) answer = -1;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Connection {
        int next;
        int cost;

        public Connection(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    private static class CrossPoint {
        int number;
        int cost;
        int maxCost;

        public CrossPoint(int number, int cost, int maxCost) {
            this.number = number;
            this.cost = cost;
            this.maxCost = maxCost;
        }
    }

}
