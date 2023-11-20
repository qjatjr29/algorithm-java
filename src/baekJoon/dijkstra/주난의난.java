package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 주난의난 {

    private static final Character EMPTY = '0';
    private static final Character FRIEND = '1';
    private static final Character DESTINATION = '#';

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        char[][] classRoom = new char[N + 1][M + 1];

        input = new StringTokenizer(br.readLine());

        int startX = Integer.parseInt(input.nextToken());
        int startY = Integer.parseInt(input.nextToken());
        int destinationX = Integer.parseInt(input.nextToken());
        int destinationY = Integer.parseInt(input.nextToken());

        for(int i = 1; i <= N; i++) {
            input = new StringTokenizer(br.readLine());
            String str = input.nextToken();
            for(int j = 1; j <= M; j++) {
                classRoom[i][j] = str.charAt(j - 1);
            }
        }

        int answer = solution(startX, startY, destinationX, destinationY, classRoom);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static int solution(int startX, int startY, int destinationX, int destinationY, char[][] map) {

        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        nodeQueue.add(new Node(0, startX, startY));

        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[startX][startY] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!nodeQueue.isEmpty()) {

            Node cNode = nodeQueue.poll();
            int cx = cNode.x;
            int cy = cNode.y;
            int cJumpCount = cNode.jumpCount;

            if(cx == destinationX && cy == destinationY) {
                return cJumpCount;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx <= 0 || nx >= map.length || ny <= 0 || ny >= map[0].length) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;

                if(map[nx][ny] == EMPTY) {
                    nodeQueue.add(new Node(cJumpCount, nx, ny));
                }
                if(map[nx][ny] == FRIEND || map[nx][ny] == DESTINATION) {
                    nodeQueue.add(new Node(cJumpCount + 1, nx, ny));
                }
            }

        }

        return 0;
    }

    private static class Node implements Comparable<Node> {
        int jumpCount;
        int x;
        int y;

        public Node(int jumpCount, int x, int y) {
            this.jumpCount = jumpCount;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.jumpCount - o.jumpCount;
        }
    }
}
