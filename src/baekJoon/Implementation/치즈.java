package baekJoon.Implementation;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈 {

    private static final int CHEESE = 1;
    private static final int OUT_AIR = -1;
    private static final int IN_AIR = 0;
    private static int[][] board;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<int[]> meltCheese = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            determineAir();
            if (melt() == 0) break;
            answer++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int melt() {

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == CHEESE && !visited[i][j]) {
                    classifyCheese(i, j, visited);
                }
            }
        }
        if (meltCheese.isEmpty()) return 0;
        while (!meltCheese.isEmpty()) {
            int[] melt = meltCheese.poll();
            board[melt[0]][melt[1]] = IN_AIR;
        }
        return 1;
    }

    private static void classifyCheese(int cx, int cy, boolean[][] visited) {

        visited[cx][cy] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{cx, cy});

        while (!queue.isEmpty()) {
            int[] cheese = queue.poll();

            int x = cheese[0];
            int y = cheese[1];

            // 현재 치즈가 녹는 치즈인지 판단
            int outAirCount = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isOutRange(nx, ny)) continue;
                if (board[nx][ny] == OUT_AIR) outAirCount++;
            }

            if (outAirCount >= 2) meltCheese.add(cheese);

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isOutRange(nx, ny) || visited[nx][ny] || board[nx][ny] != CHEESE) continue;
                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny});
            }
        }
    }

    private static void determineAir() {

        // 맨 가장자리에는 치즈가 놓이지 않는다 = 외부 공기
        // 0,0 부터 연결된 것은 모두 외부 공기이다.
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] air = queue.poll();
            int x = air[0];
            int y = air[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isOutRange(nx, ny) || visited[nx][ny] || board[nx][ny] == CHEESE) continue;
                visited[nx][ny] = true;
                board[nx][ny] = OUT_AIR;
                queue.add(new int[] {nx, ny});
            }
        }
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length;
    }
}
