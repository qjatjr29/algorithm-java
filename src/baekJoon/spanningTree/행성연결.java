package baekJoon.spanningTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.Collections.sort;

public class 행성연결 {

    static int[] root;
    static int[] rank;

    private static void setup(int N) {
        root = new int[N + 1];
        rank = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            root[i] = i;
            rank[i] = 0;
        }
    }

    private static int find(int x) {
        if(x == root[x]) return x;
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

    private static class Planet implements Comparable<Planet> {
        int x, y, cost;

        public Planet(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Planet o) {
            if(this.cost < o.cost) return -1;
            else if(this.cost > o.cost) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        setup(N);
        List<Planet> planets = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                planets.add(new Planet(i, j, Integer.parseInt(st.nextToken())));
            }
        }
        sort(planets);
        long answer = 0;
        for(int i = 0; i < planets.size(); i++) {
            Planet p = planets.get(i);

            if(find(p.x) != find(p.y)) {
                answer += p.cost;
                union(p.x, p.y);
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
