package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 최소비용구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        input = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(input.nextToken());
        List<int[]>[] lines = new List[N + 1];
        for(int i = 1; i <= N; i++) {
            lines[i] = new ArrayList<int[]>();
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            lines[start].add(new int[] {end, cost});
        }

        input = new StringTokenizer(br.readLine());
        int sCity = Integer.parseInt(input.nextToken());
        int eCity = Integer.parseInt(input.nextToken());

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.add(new int[] {sCity, 0});
        boolean[] visited = new boolean[N + 1];
        int answer = 0;

        while(!queue.isEmpty()) {
            int[] here = queue.poll();
            int cCity = here[0];
            int cost = here[1];

            visited[cCity] = true;

            if(cCity == eCity) {
                answer = cost;
                break;
            }

            for(int[] next : lines[cCity]) {
                int nCity = next[0];
                int nCost = next[1];

                if(visited[nCity]) {
                    continue;
                }
                queue.add(new int[] {nCity, cost + nCost});
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
