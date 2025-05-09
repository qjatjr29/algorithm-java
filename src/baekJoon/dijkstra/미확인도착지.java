package baekJoon.dijkstra;

import java.io.*;
import java.util.*;

public class 미확인도착지 {

    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for (int testcase = 0; testcase < T; testcase++) {

            // 출발지점에서 목적지까지는 무조건 최소거리임.

            input = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(input.nextToken());
            int m = Integer.parseInt(input.nextToken());
            int t = Integer.parseInt(input.nextToken());

            input = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(input.nextToken()); // 예술가 시작 위치

            // g와 h의 도로를 지나감.
            int g = Integer.parseInt(input.nextToken());
            int h = Integer.parseInt(input.nextToken());

            List<int[]>[] roads = new List[n + 1];
            distance = new int[n + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);

            for (int i = 1; i <= n; i++) {
                roads[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                input = new StringTokenizer(br.readLine());
                int p1 = Integer.parseInt(input.nextToken());
                int p2 = Integer.parseInt(input.nextToken());
                int length = Integer.parseInt(input.nextToken());
                if ((p1 == g && p2 == h) || (p1 == h && p2 == g)) {
                    // g-h 도로의 거리를 홀수로 만듬.
                    roads[p1].add(new int[] {p2, length * 2 - 1});
                    roads[p2].add(new int[] {p1, length * 2 - 1});
                }
                else {
                    // 나머지 도로들은 모두 짝수
                    roads[p1].add(new int[] {p2, length * 2});
                    roads[p2].add(new int[] {p1, length * 2});
                }
            }

            int[] endpoints = new int[t];
            for (int i = 0; i < t; i++) {
                input = new StringTokenizer(br.readLine());
                endpoints[i] = Integer.parseInt(input.nextToken());
            }

            // 시작 지점에서 모든 지점까지의 최소 거리 구하기
            bfs(s, roads);

            List<Integer> answer = new ArrayList<>();
            // 각 목적지들이 g-h 도로를 지나서 갔다면 반환 리스트에 추가
            // 목적지까지의 최소거리가 홀수라면 도로를 지나간 것임.
            for (int endpoint : endpoints) {
                if (distance[endpoint] != Integer.MAX_VALUE && distance[endpoint] % 2 == 1) {
                    answer.add(endpoint);
                }
            }
            Collections.sort(answer);

            for (int ans : answer) {
                bw.write(String.valueOf(ans) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // 시작지점에서 모든 지점까지의 최소거리를 구하기.
    private static void bfs(int start, List<int[]>[] roads) {

        boolean[] visited = new boolean[roads.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] {0, start});
        distance[start] = 0;

        while (!pq.isEmpty()) {

            int[] node = pq.poll();
            int number = node[1];

            if (visited[number]) {
                continue;
            }

            distance[number] = Math.min(distance[number], node[0]);
            visited[number] = true;

            for (int i = 0; i < roads[number].size(); i++) {
                int[] next = roads[number].get(i);
                int nextNum = next[0];
                int nextDistance = next[1];

                if (visited[nextNum]) {
                    continue;
                }

                pq.add(new int[] {node[0] + nextDistance, nextNum});

            }
        }
    }
}
