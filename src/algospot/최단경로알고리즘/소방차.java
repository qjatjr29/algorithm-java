package algospot.최단경로알고리즘;

import java.io.*;
import java.util.*;

// https://www.algospot.com/judge/problem/read/FIRETRUCKS
public class 소방차 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for (int test_case = 0; test_case < T; test_case++) {
            input = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(input.nextToken());
            int e = Integer.parseInt(input.nextToken());
            int n = Integer.parseInt(input.nextToken());
            int m = Integer.parseInt(input.nextToken());

            List<Node>[] times = new List[v + 1];
            int[] distance = new int[v + 1];

            for (int i = 0; i <= v; i++) {
                times[i] = new ArrayList<>();
                distance[i] = 100001;
            }
            distance[0] = 0;

            for (int i = 0; i < e; i++) {
                input = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(input.nextToken());
                int b = Integer.parseInt(input.nextToken());
                int t = Integer.parseInt(input.nextToken());
                times[a].add(new Node(b, t));
                times[b].add(new Node(a, t));
            }

            List<Integer> fire = new ArrayList<>();
            input = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                fire.add(Integer.parseInt(input.nextToken()));
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            boolean[] visited = new boolean[v + 1];

            input = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int fireStation = Integer.parseInt(input.nextToken());
                times[0].add(new Node(fireStation, 0));
            }

            pq.add(new Node(0, 0));
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                visited[node.num] = true;
                for (Node next : times[node.num]) {
                    if (visited[next.num]) continue;
                    if (distance[next.num] > distance[node.num] + next.time) {
                        distance[next.num] = distance[node.num] + next.time;
                        pq.add(new Node(next.num, distance[next.num]));
                    }
                }
            }

            int answer = 0;

            for (int f : fire) {
                answer += distance[f];
            }

            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Node implements Comparable<Node> {
        int num;
        int time;

        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
