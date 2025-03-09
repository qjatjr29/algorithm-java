package baekJoon.Implementation;

import java.io.*;

import java.util.StringTokenizer;

public class 테트로미노 {

    private static int answer;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int[][] board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                solve(board, i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve(int[][] board, int x, int y, int sum, int count) {

        if (count == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || visited[nx][ny]) {
                continue;
            }

            // ㅗ, ㅓ, ㅏ, ㅜ 와 같은 모양을 만들기 위함
            // 삐죽 튀어나온 부분을 이미 갔다고 가정 (visited = true, sum 도 해당 칸 값 더함)
            if (count == 2) {
                visited[nx][ny] = true;
                solve(board, x, y, sum + board[nx][ny], count + 1);
                visited[nx][ny] = false;
            }

            visited[nx][ny] = true;
            solve(board, nx, ny, sum + board[nx][ny], count + 1);
            visited[nx][ny] = false;
        }

    }


}
