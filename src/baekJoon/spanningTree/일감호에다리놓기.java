package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 일감호에다리놓기 {

    private static int[] root;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        long K = Long.parseLong(input.nextToken());
        int[] requiredStone = new int[N + 1];
        root = new int[N + 1];
        Map<Integer, Integer> subSet = new HashMap<>();

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            requiredStone[i] = Integer.parseInt(input.nextToken());
        }

        List<Integer>[] connections = new List[N+ 1];
        for(int i = 1; i <= N; i++) {
            connections[i] = new ArrayList<Integer>();
            root[i] = i;
            if(i == 1) {
                connections[i].add(i + 1);
                connections[i].add(N);
            }
            else if(i == N) {
                connections[i].add(i - 1);
                connections[i].add(1);
            }
            else {
                connections[i].add(i - 1);
                connections[i].add(i + 1);
            }
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());

            int b1 = Integer.parseInt(input.nextToken());
            int b2 = Integer.parseInt(input.nextToken());
            connections[b1].remove(Integer.valueOf(b2));
            connections[b2].remove(Integer.valueOf(b1));
        }

        for(int i = 1; i <= N; i++) {
            for(int next : connections[i]) {
                if(isCycle(i, next)) continue;
                union(i, next);
            }
        }

        for(int i = 1; i <= N; i++) {
            int rt = find(i);

            int minDistance = subSet.getOrDefault(rt, requiredStone[i]);
            subSet.put(rt, Math.min(minDistance, requiredStone[i]));
        }

        long requiredDistance = 0;
        for(int key : subSet.keySet()) {
            requiredDistance += subSet.get(key);
        }

        int size = subSet.size();
        if(size == 1) bw.write("YES");
        else if(requiredDistance > K) bw.write("NO");
        else bw.write("YES");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

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
