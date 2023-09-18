package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 행성터널 {

    private static int[] root;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int planetCount = Integer.parseInt(input.nextToken());
        Planet[] planets = new Planet[planetCount];
        root = new int[planetCount];
        int answer = 0;

        for(int i = 0; i < planetCount; i++) {
            input = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());
            int z = Integer.parseInt(input.nextToken());
            planets[i] = new Planet(i, x, y, z);
            root[i] = i;
        }

        List<Connection> connectionList = new ArrayList<>();

        // x 좌표에 대해 행성 정렬
        Arrays.sort(planets, (p1, p2) -> p1.x - p2.x);
        for(int i = 1; i < planetCount; i++) {
            int cost = Math.abs(planets[i].x - planets[i - 1].x);
            connectionList.add(new Connection(planets[i - 1].id, planets[i].id, cost));
        }

        // y 좌표에 대해 행성 정렬
        Arrays.sort(planets, (p1, p2) -> p1.y - p2.y);
        for(int i = 1; i < planetCount; i++) {
            int cost = Math.abs(planets[i].y - planets[i - 1].y);
            connectionList.add(new Connection(planets[i - 1].id, planets[i].id, cost));
        }

        // z 좌표에 대해 행성 정렬
        Arrays.sort(planets, (p1, p2) -> p1.z - p2.z);
        for(int i = 1; i < planetCount; i++) {
            int cost = Math.abs(planets[i].z - planets[i - 1].z);
            connectionList.add(new Connection(planets[i - 1].id, planets[i].id, cost));
        }

        // 각 연결 정보를 이용한 최소 스패닝 트리를 적용해서 최소 비용을 구하자.
        Collections.sort(connectionList);

        for(Connection con : connectionList) {
            int planet1 = con.planet1;
            int planet2 = con.planet2;
            int cost = con.cost;

            if(isCycle(planet1, planet2)) continue;
            union(planet1, planet2);
            answer += cost;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Connection implements Comparable<Connection> {
        int planet1;
        int planet2;

        int cost;

        Connection(int planet1, int planet2, int cost) {
            this.planet1 = planet1;
            this.planet2 = planet2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connection o) {
            return this.cost - o.cost;
        }
    }

    private static class Planet {
        int id;
        int x, y, z;

        Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
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
