package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 공주님을구해라 {

    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int SWORD = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int timeLimit = Integer.parseInt(input.nextToken());
        int[][] map = new int[N + 1][M + 1];
        boolean[][] visited = new boolean[N + 1][M + 1];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Position sword = new Position();

        for(int i = 1; i <= N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
                if(map[i][j] == SWORD) {
                    sword.x = i;
                    sword.y = j;
                }
            }
        }

        // '그람' 에서 공주까지의 최단 거리 (벽 다 부심)
        int swordToPrincess = Math.abs(N - sword.x) + Math.abs(M - sword.y);

        // 전설의 명검 '그람' 까지의 거리 구하기
        int heroToSword = 987654321;
        visited[1][1] = true;
        PriorityQueue<Position> pq = new PriorityQueue<>();
        pq.add(new Position(1, 1));

        while(!pq.isEmpty()) {

            Position here = pq.poll();

            int cx = here.x;
            int cy = here.y;
            int distance = here.distance;

            if(cx == sword.x && cy == sword.y) {
                heroToSword = distance;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx <= 0 || nx > N || ny <= 0 || ny > M) continue;
                if(visited[nx][ny] || map[nx][ny] == WALL) continue;
                pq.add(new Position(nx, ny, distance + 1));
                visited[nx][ny] = true;
            }
        }

        // 영웅 -> 그람 -> 공주 까지의 거리
        int heroToPrincess = swordToPrincess + heroToSword;

        // 영웅 -> 공주 의 경로
        visited = new boolean[N + 1][M + 1];
        visited[1][1] = true;
        pq = new PriorityQueue<>();
        pq.add(new Position(1, 1));

        while(!pq.isEmpty()) {

            Position here = pq.poll();

            int cx = here.x;
            int cy = here.y;
            int distance = here.distance;

            if(cx == N && cy == M) {
                heroToPrincess = Math.min(heroToPrincess, distance);
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx <= 0 || nx > N || ny <= 0 || ny > M) continue;
                if(visited[nx][ny] || map[nx][ny] == WALL) continue;
                pq.add(new Position(nx, ny, distance + 1));
                visited[nx][ny] = true;
            }
        }

        if(heroToPrincess > timeLimit) bw.write("Fail");
        else bw.write(String.valueOf(heroToPrincess));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();


    }

    private static class Position implements Comparable<Position> {
        int x, y;
        int distance;

        public Position() {
            this.distance = 0;
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = 0;
        }

        public Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Position o) {
            return this.distance - o.distance;
        }
    }

}
