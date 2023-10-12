package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정복자 {

    private static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int cityCount = Integer.parseInt(input.nextToken());
        int roadCount = Integer.parseInt(input.nextToken());
        int additionalCost = Integer.parseInt(input.nextToken());
        Road[] roads = new Road[roadCount];
        root = new int[cityCount + 1];
        long answer = 0;
        int count = 0;

        for(int i = 0; i <= cityCount; i++) root[i] = i;

        for(int i = 0; i < roadCount; i++) {
            input = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(input.nextToken());
            int city2 = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            roads[i] = new Road(city1, city2, cost);
        }

        Arrays.sort(roads, (r1, r2) -> r1.cost - r2.cost);

        for(Road road : roads) {

            int city1 = road.city1;
            int city2 = road.city2;

            if(isCycle(city1, city2)) continue;
            answer += road.cost;
            union(city1, city2);
            answer += ((long) count * additionalCost);
            count++;
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Road {
        int city1, city2;
        int cost;
        Road(int city1, int city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }
    }

    private static int find(int x) {
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }

    private static boolean isCycle(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) root[x] = y;
    }

}
