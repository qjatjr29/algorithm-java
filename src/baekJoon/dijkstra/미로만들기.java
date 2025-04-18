package baekJoon.dijkstra;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 미로만들기 {

    private static final int WHITE_ROOM = 1;
    private static final int BLACK_ROOM = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int answer = 0;
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            String s = input.nextToken();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        boolean[][] visited = new boolean[n][n];

        pq.add(new int[] {0, 0, 0});

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!pq.isEmpty()) {

            int[] here = pq.poll();
            int count = here[0];
            int cx = here[1];
            int cy = here[2];

            if (visited[cx][cy]) continue;
            visited[cx][cy] = true;
            if (cx == n - 1 && cy == n - 1) {
                answer = count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                if (map[nx][ny] == WHITE_ROOM) {
                    pq.add(new int[] {count, nx, ny});
                }

                if (map[nx][ny] == BLACK_ROOM) {
                    pq.add(new int[] {count + 1, nx, ny});
                }
            }
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
