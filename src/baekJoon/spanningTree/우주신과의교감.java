package baekJoon.spanningTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.util.Collections.sort;

public class 우주신과의교감 {

    static Position[] gods;
    static int[] root;
    static int[] rank;

    private static void setUnionFind(int N) {
        root = new int[N + 1];
        rank = new int[N+1];
        for (int i = 1; i <= N; i++) {
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

        if(x==y) return;

        if(rank[x] < rank[y]) root[x] = y;
        else
        {
            root[y] =x;
            if(rank[x]==rank[y]) rank[x] ++;
        }
    }

    private static class Position {
        int idx, x, y;
        Position(int index, int x, int y) {
            this.idx = index;
            this.x = x;
            this.y = y;
        }
    }

    private static double distance(Position p1, Position p2) {
        return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
    }
    private static class Node implements Comparable<Node> {
        int start, end;
        double length;

        public Node(int start, int end, double length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(Node o) {
            if(length < o.length) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        gods = new Position[N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods[i] = new Position(i, x, y);
        }

        setUnionFind(N);

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        List<Node> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                double len = distance(gods[i], gods[j]);
                list.add(new Node(gods[i].idx, gods[j].idx, len));
            }
        }
        sort(list);

        double answer = 0;

        for(int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if(find(node.start) != find(node.end)) {
                answer += node.length;
                union(node.start, node.end);
            }
        }
        bw.write(String.format("%.2f", answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
