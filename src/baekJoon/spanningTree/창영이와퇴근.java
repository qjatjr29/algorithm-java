package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 창영이와퇴근 {

    private static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int answer = -1;

        int[][] map = new int[size][size];
        root = new int[size * size];

        for(int i = 0; i < root.length; i++) {
            root[i] = i;
        }

        for(int i = 0; i < size; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        PriorityQueue<Road> roadQueue = new PriorityQueue<>();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int cIndex = makeIndex(i, j, size);
                for(int z = 0; z < 4; z++) {
                    int nx = i + dx[z];
                    int ny = j + dy[z];

                    if(isOutOfRange(size, nx, ny)) continue;
                    int nIndex = makeIndex(nx, ny, size);
                    int cost = calculateCost(i, j, nx, ny, map);
                    roadQueue.add(new Road(cIndex, nIndex, cost));
                }
            }
        }

        while(!roadQueue.isEmpty()) {
            Road cRoad = roadQueue.poll();
            if(isCycle(cRoad.u, cRoad.v)) continue;

            union(cRoad.u, cRoad.v);
            answer = Math.max(answer, cRoad.cost);
            if(isCycle(makeIndex(0, 0, size), makeIndex(size - 1, size - 1, size))) break;
        }
        if(size == 1) answer = 0;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int calculateCost(int x, int y, int nx, int ny, int[][] map) {
        return Math.abs(map[x][y] - map[nx][ny]);
    }

    private static boolean isOutOfRange(int size, int x, int y) {
        return x < 0 || x >= size || y < 0 || y >= size;
    }

    private static int makeIndex(int x, int y, int size) {
        return size * x + y;
    }

    private static int find(int x) {
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) root[x] = y;
    }

    private static boolean isCycle(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    private static class Road implements Comparable<Road> {
        int u;
        int v;
        int cost;

        Road(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return this.cost - o.cost;
        }
    }
}
