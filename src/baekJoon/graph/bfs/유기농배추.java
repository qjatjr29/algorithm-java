package baekJoon.graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추 {

    static int M, N;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0};
    static int[] dy = { 0, 0, -1, 1};

    private static class Location {
        int x, y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static void bfs(int x, int y) {
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(x, y));

        while(true) {
            if(queue.isEmpty()) break;
            Location here = queue.poll();
            int cx  = here.x;
            int cy  = here.y;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if(visited[nx][ny] || map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                queue.add(new Location(nx, ny));
            }

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            visited = new boolean[M][N];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            int answer = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        answer++;
                        bfs(i, j);
                    }
                }
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
