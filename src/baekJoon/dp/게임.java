package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 게임 {

    private static final int HOLE = -1;
    private static int maxCount;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] dp;
    private static boolean[][] visited;
    private static boolean chk = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int[][] board = new int[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            String s = input.nextToken();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == 'H') {
                    board[i][j] = HOLE;
                }
                else board[i][j] = s.charAt(j) - '0';
            }
        }
        dp[0][0] = 0;
        visited[0][0] = true;
        dfs(0, 0, 1, board);

        int answer = -1;
        if (!chk) answer = maxCount;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int count, int[][] board) {

        maxCount = Math.max(maxCount, count);
        int number = board[x][y];
        dp[x][y] = count;

        for (int i = 0; i < 4; i++) {
            int nx = x + (dx[i] * number);
            int ny = y + (dy[i] * number);
            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == HOLE) {
                continue;
            }

            // 해당 경로에서 이미 방문한 경우 -> 무한 루프
            if (visited[nx][ny]) {
                chk = true;
                return;
            }

            // 이미 다음 위치에 갔을 때 이미 더 많은 게임을 할 수 있는 경우가 있다면 넘어간다.
            if (dp[nx][ny] >= count + 1) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, count + 1, board);
            visited[nx][ny] = false;
        }
    }
}
