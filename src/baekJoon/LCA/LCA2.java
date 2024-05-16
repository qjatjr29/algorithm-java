package baekJoon.LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA2 {

    private static final int ROOT_NUMBER = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        List<Integer>[] nodes = new List[N + 1];

        for(int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            input = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(input.nextToken());
            int node2 = Integer.parseInt(input.nextToken());
            nodes[node1].add(node2);
            nodes[node2].add(node1);
        }

        input = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(input.nextToken());
        int height = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
        boolean[] visited = new boolean[N + 1];
        int[] depth = new int[N + 1];
        int[][] parent = new int[N + 1][height];

        dfs(nodes, depth, parent, visited, 0, ROOT_NUMBER);
        initSparseTable(parent, N, height);

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int targetNode1 = Integer.parseInt(input.nextToken());
            int targetNode2 = Integer.parseInt(input.nextToken());

            int result = LCA(targetNode1, targetNode2, parent, depth, height);
            bw.write(String.valueOf(result));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int LCA(int n1, int n2, int[][] parent, int[] depth, int height) {
        if(depth[n1] > depth[n2]) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        for (int i = height - 1; i >= 0; i--) {
            if (depth[n2] - depth[n1] >= Math.pow(2,i)) {
                n2 = parent[n2][i];
            }
        }
        if (n1 == n2) return n1;

        for(int i = height - 1; i >= 0; i--) {
            if(parent[n1][i] != parent[n2][i]) {
                n1 = parent[n1][i];
                n2 = parent[n2][i];
            }
        }

        return parent[n1][0];
    }

    private static void dfs(List<Integer>[] nodes, int[] depth, int[][] parent, boolean[] visited, int cDepth, int node) {
        visited[node] = true;
        depth[node] = cDepth;

        for(int child : nodes[node]) {
            if(visited[child]) {
                continue;
            }
            parent[child][0] = node;
            dfs(nodes, depth, parent, visited, cDepth + 1, child);
        }
    }

    private static void initSparseTable(int[][] parent, int N, int height) {
        for(int i = 1; i < height; i++) {
            for (int node = 1; node <= N; node++) {
                parent[node][i] = parent[parent[node][i - 1]][i - 1];
            }
        }
    }
}
