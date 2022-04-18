package baekJoon.dijkstra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백도어 {

    static int[] visited;
    static List<Pair>[] path;
    static long answer[];
    static int[] sight;
    static class Pair implements Comparable<Pair>{
        int dest;
        long weight;
        Pair(int dest, long weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.weight - o.weight > 0) return 1;
            else return -1;
        }
    }
    private static void sol() {

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        answer[0] = 0;
        pq.add(new Pair(0,0));

        while(true) {
            if(pq.isEmpty()) break;
            Pair here = pq.poll();
            int curIndex = here.dest;
            long curCost  = here.weight;

            if(visited[curIndex] == 1) continue;
            visited[curIndex] = 1;

            for(int i = 0; i < path[curIndex].size(); i++) {
                Pair next = path[curIndex].get(i);
                int nIdx = next.dest;
                long nWeight = next.weight;
                if(answer[nIdx] > curCost + nWeight) {
                    answer[nIdx] = curCost + nWeight;
                    pq.add(new Pair(nIdx, answer[nIdx]));
                }
            }
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new int[N];
        path = new List[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<Pair>();
        }

        sight = new int[N];
        answer = new long[N];

        for(int i = 0; i < N; i++) {
            answer[i] = Long.MAX_VALUE;
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            sight[i] = Integer.parseInt(st.nextToken());
        }
        sight[N-1] = 0;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(sight[s] == 1 || sight[d] == 1) continue;
            path[s].add(new Pair(d, w));
            path[d].add(new Pair(s, w));
        }
        sol();
        if(answer[N-1] == Long.MAX_VALUE) bw.write("-1");
        else bw.write(String.valueOf(answer[N-1]));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
