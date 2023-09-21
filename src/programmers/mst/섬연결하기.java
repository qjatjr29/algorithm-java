package programmers.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 섬연결하기 {

    private static int[] root;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        root = new int[n];
        List<Connection> connections = new ArrayList<>();

        for(int i = 0; i < n; i++) root[i] = i;

        for(int[] cost : costs) {
            connections.add(new Connection(cost[0], cost[1], cost[2]));
        }

        Collections.sort(connections, (c1, c2) -> c1.cost - c2.cost);

        for(Connection con : connections) {
            int island1 = con.island1;
            int island2 = con.island2;
            int cost = con.cost;

            if(isCycle(island1, island2)) continue;
            answer += cost;
            union(island1, island2);
        }

        return answer;
    }

    private int find(int x) {
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }

    private boolean isCycle(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) root[x] = y;
    }

    private class Connection {
        int island1;
        int island2;
        int cost;

        Connection(int island1, int island2, int cost) {
            this.island1 = island1;
            this.island2 = island2;
            this.cost = cost;
        }
    }

}
