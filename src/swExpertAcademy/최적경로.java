package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 최적경로 {

    private static int N;
    private static Node home;
    private static Node company;
    private static Node[] clients;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 1; testcase <= T; testcase++) {

            answer = Integer.MAX_VALUE;
            input = new StringTokenizer(br.readLine());
            N = Integer.parseInt(input.nextToken());
            clients = new Node[N];

            input = new StringTokenizer(br.readLine());

            int hx = Integer.parseInt(input.nextToken());
            int hy = Integer.parseInt(input.nextToken());
            home = new Node(hx, hy);

            int cx = Integer.parseInt(input.nextToken());
            int cy = Integer.parseInt(input.nextToken());
            company = new Node(cx, cy);

            for(int i = 0; i < N; i++) {
                int x = Integer.parseInt(input.nextToken());
                int y = Integer.parseInt(input.nextToken());
                clients[i] = new Node(x, y);
            }

            boolean[] visited = new boolean[N];

            dfs(company, 0, 0, visited);

            bw.write("#" + String.valueOf(testcase) + " " + String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(Node node, int count, int distance, boolean[] visited) {

        if(answer <= distance) {
            return;
        }

        if(count == N) {
            answer = Math.min(answer, distance + home.calculateDistance(node));
            return;
        }

        for(int i = 0; i < N; i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            int nDistance = distance + node.calculateDistance(clients[i]);
            dfs(clients[i], count + 1, nDistance, visited);
            visited[i] = false;
        }

    }

    private static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calculateDistance(Node o) {
            return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
        }
    }

}
