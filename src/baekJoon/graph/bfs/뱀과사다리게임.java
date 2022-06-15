package baekJoon.graph.bfs;

import java.io.*;
import java.util.*;

public class 뱀과사다리게임 {

    private static int N, M;

    private static boolean[] visited;
    private static int answer;
    private static Map<Integer, Integer> snake = new HashMap<>();
    private static Map<Integer, Integer> ladder = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[101];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladder.put(start, end);
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snake.put(start, end);
        }

        sol(1);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();

    }

    private static class Pair implements Comparable<Pair> {
        int cost, x;

        public Pair(int cost, int x) {
            this.cost = cost;
            this.x = x;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.cost > o.cost) return 1;
            else if(this.cost == o.cost) return 0;
            else return -1;
        }
    }

    private static void sol(int start) {
        visited[start] = true;
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(0, start));

        while(true) {
            if(q.isEmpty()) break;
            Pair poll = q.poll();
            int here = poll.x;
            int cost = poll.cost;

            if(here == 100) {
                answer = cost;
                break;
            }

            for(int i = 1; i <= 6; i++) {
                int next = here + i;
                if(next > 100) continue;
                if(visited[next]) continue;

                if(snake.containsKey(next)) {
                    visited[next] = true;
                    int idx = snake.get(next);
                    q.add(new Pair(cost + 1, idx));
                    continue;
                }

                if(ladder.containsKey(next)) {
                    visited[next] = true;
                    int idx = ladder.get(next);
                    q.add(new Pair(cost + 1, idx));
                    continue;
                }
                visited[next] = true;
                q.add(new Pair(cost + 1, next));
            }


        }

    }
}
