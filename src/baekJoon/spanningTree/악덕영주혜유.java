package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 악덕영주혜유 {

    private static int maxCost;
    private static int maxDistanceNodeNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        long minCost = 0;

        int houseCount = Integer.parseInt(input.nextToken());
        int tradeRoutes = Integer.parseInt(input.nextToken());
        TradeRoute[] routes = new TradeRoute[tradeRoutes];
        int[] root = new int[houseCount];
        List<House>[] minRoutes = new List[houseCount];


        for(int i = 0; i < houseCount; i++) {
            root[i] = i;
            minRoutes[i] = new ArrayList<House>();
        }

        for(int i = 0; i < tradeRoutes; i++) {
            input = new StringTokenizer(br.readLine());
            int house1 = Integer.parseInt(input.nextToken());
            int house2 = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            routes[i] = new TradeRoute(house1, house2, cost);
        }

        Arrays.sort(routes, (r1, r2) -> r1.cost - r2.cost);

        for(int i = 0; i < routes.length; i++) {
            TradeRoute route = routes[i];
            int house1 = route.house1;
            int house2 = route.house2;
            int cost = route.cost;

            if(isCycle(house1, house2, root)) continue;
            minRoutes[house1].add(new House(house2, cost));
            minRoutes[house2].add(new House(house1, cost));
            union(house1, house2, root);
            minCost += cost;
        }

        boolean[] visited = new boolean[houseCount];
        maxCost = Integer.MIN_VALUE;
        dfs(0, 0, visited, minRoutes);

        visited = new boolean[houseCount];
        maxCost = Integer.MIN_VALUE;
        dfs(maxDistanceNodeNumber, 0, visited, minRoutes);

        bw.write(String.valueOf(minCost));
        bw.newLine();
        bw.write(String.valueOf(maxCost));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(int index, int cost, boolean[] visited, List<House>[] routes) {
        visited[index] = true;

        if(maxCost < cost) {
            maxCost = cost;
            maxDistanceNodeNumber = index;
        }

        for(House next : routes[index]) {
            if(!visited[next.number]) dfs(next.number, cost + next.cost, visited, routes);
        }
    }

    private static int find(int x, int[] root) {
        if(root[x] == x) return x;
        else return root[x] = find(root[x], root);
    }

    private static void union(int x, int y, int[] root) {
        x = find(x, root);
        y = find(y, root);

        if(x != y) root[x] = y;
    }

    private static boolean isCycle(int x, int y, int[] root) {
        x = find(x, root);
        y = find(y, root);

        return x == y;
    }

    private static class TradeRoute {
        int house1, house2;
        int cost;

        TradeRoute(int house1, int house2, int cost) {
            this.house1 = house1;
            this.house2 = house2;
            this.cost = cost;
        }
    }

    private static class House {
        int number;
        int cost;

        House(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }

}
