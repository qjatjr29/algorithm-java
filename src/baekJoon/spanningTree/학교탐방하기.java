package baekJoon.spanningTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.pow;
import static java.util.Collections.sort;

public class 학교탐방하기 {

    static int[] root, rank;

    private static void setup(int N) {
        root = new int[N + 1];
        rank = new int[N + 1];
        for(int i = 0 ; i <=N; i++) {
            root[i] = i;
            rank[i] = 0;
        }
    }

    private static int find(int x) {
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(rank[x] < rank[y]) root[x] = y;
        else {
            root[y] = x;
            if(rank[x] == rank[y]) rank[x]++;
        }
    }

    private static class Road implements Comparable<Road> {
        int start, end, cost;

        Road (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            if(this.cost < o.cost) return 1;
            else if(this.cost > o.cost) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        setup(N);

        List<Road> roads = new ArrayList<>();

        int start, end, cost;

        st = new StringTokenizer(br.readLine());
        int zero = Integer.parseInt(st.nextToken());
        int first = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            roads.add(new Road(start, end , cost));
        }

        sort(roads);

        union(0, 1);
        int minAnswer = 1 - num;
        for(int i = 0; i < roads.size(); i++) {
            Road road = roads.get(i);
            if(find(road.start) != find(road.end)) {
                minAnswer += 1 - road.cost;
                union(road.start , road.end);
            }
        }

        setup(N);
        union(0, 1);
        sort(roads, Collections.reverseOrder());
        int maxAnswer = 1 - num;
        for(int i = 0; i < roads.size(); i++) {
            Road road = roads.get(i);
            if(find(road.start) != find(road.end)) {
                maxAnswer += 1 - road.cost;
                union(road.start , road.end);
            }
        }
        int answer = (int) (pow(maxAnswer, 2) - pow(minAnswer, 2));
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
