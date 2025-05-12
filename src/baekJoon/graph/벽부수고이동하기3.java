package baekJoon.graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기3 {

    private static final int AVAILABLE = 0;
    private static final int WALL = 1;
    private static final int DAY = 0;
    private static final int NIGHT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            String s = input.nextToken();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        // (i, j)에 낮(0) 또는 밤(1)에 방문
        boolean[][][][] visited = new boolean[n][m][2][k + 1];

        Queue<Node> queue = new ArrayDeque<>();

        queue.add(new Node(0, 0, 0, 0, 1));
        visited[0][0][0][0] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int answer = -1;

        while (!queue.isEmpty()) {

            Node here = queue.poll();
            int cx = here.x;
            int cy = here.y;
            int dn = here.dayNight;
            int cCount = here.count;
            int wallCount = here.wallCount;

            if (cx == n - 1 && cy == m - 1) {
                answer = cCount;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (map[nx][ny] == WALL && wallCount + 1 <= k) {
                    // 낮 인경우
                    if (dn == DAY && !visited[nx][ny][NIGHT][wallCount + 1]) {
                        // 벽을 부수고 이동
                        visited[nx][ny][NIGHT][wallCount + 1] = true;
                        queue.add(new Node(nx, ny, NIGHT, wallCount + 1, cCount + 1));
                    }
                    // 밤인 경우
                    // 이동하지 않고 해당 칸에 있는 경우는 벽에 부딪혔을 때, 밤인 경우 뿐
                    else if (dn == NIGHT && !visited[cx][cy][DAY][wallCount]) {
                        // 현재 자리에 그대로 머문다.
                        visited[cx][cy][DAY][wallCount] = true;
                        queue.add(new Node(cx, cy, DAY, wallCount, cCount + 1));
                    }
                }

                // 벽이 아닌 경우
                else if (map[nx][ny] == AVAILABLE && !visited[nx][ny][(dn + 1) % 2][wallCount]) {
                    visited[nx][ny][(dn + 1) % 2][wallCount] = true;
                    queue.add(new Node(nx, ny, (dn + 1) % 2, wallCount, cCount + 1));
                }
            }
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
    private static class Node {
        int x;
        int y;
        int dayNight;
        int wallCount;
        int count;

        Node(int x, int y, int dayNight, int wallCount, int count) {
            this.x = x;
            this.y = y;
            this.dayNight = dayNight;
            this.wallCount = wallCount;
            this.count = count;
        }
    }
}
