package baekJoon.dijkstra;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 알고스팟 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(input.nextToken());
        int n = Integer.parseInt(input.nextToken());

        int[][] map = new int[n + 1][m + 1];
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            input = new StringTokenizer(br.readLine());
            char[] chars = input.nextToken().toCharArray();
            for (int j = 1; j <= m; j++) {
                map[i][j] = chars[j - 1] - '0';
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 1, 0));
        boolean[][] visited = new boolean[n + 1][m + 1];
        visited[1][1] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            Node node = pq.poll();

            int cx = node.x;
            int cy = node.y;
            int count = node.breakCount;

            if (cx == n && cy == m) {
                answer = count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx <= 0 || ny <= 0 || nx > n || ny > m || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (map[nx][ny] == 1) pq.add(new Node(nx, ny, count + 1));
                else pq.add(new Node(nx, ny, count));
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int breakCount;

        public Node(int x, int y, int breakCount) {
            this.x = x;
            this.y = y;
            this.breakCount = breakCount;
        }

        @Override
        public int compareTo(Node o) {
            return this.breakCount - o.breakCount;
        }
    }
}
