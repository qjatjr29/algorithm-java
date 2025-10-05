package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 내리막길 {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(input.nextToken());
        int n = Integer.parseInt(input.nextToken());

        int[][] board = new int[m][n];
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
                dp[i][j] = -1;
            }
        }

        dfs(0, 0, dp, board);

        int answer = dp[0][0];

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int x, int y, int[][] dp, int[][] board) {

        if (x == board.length - 1 && y == board[0].length - 1) return 1;
        if (dp[x][y] != -1) return dp[x][y];
        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
            if (board[nx][ny] >= board[x][y]) continue;

            dp[x][y] += dfs(nx, ny, dp, board);
        }
        return dp[x][y];
    }
}
