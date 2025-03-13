package baekJoon.Implementation;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 치즈 {

    private static final int CHEESE = 1;
    private static int[][] board;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

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

        int answer = melting();

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int melting() {

        int time = 0;
        boolean[][] visited = new boolean[board.length][board[0].length];
        PriorityQueue<int[]> airQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        airQueue.add(new int[] {0, 0, 0});
        visited[0][0] = true;

        int[][] aroundAirCount = new int[board.length][board[0].length];

        while (!airQueue.isEmpty()) {

            int[] air = airQueue.poll();
            int x = air[0];
            int y = air[1];
            int count = air[2];

            time = Math.max(time, count);
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nCount = count;

                if (isOutRange(nx, ny) || visited[nx][ny]) continue;

                if (board[nx][ny] == CHEESE) {
                    aroundAirCount[nx][ny]++;
                    if (aroundAirCount[nx][ny] < 2) continue;
                    nCount += 1;
                }
                visited[nx][ny] = true;
                airQueue.add(new int[] {nx, ny, nCount});
            }

        }

        return time;
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length;
    }
}
