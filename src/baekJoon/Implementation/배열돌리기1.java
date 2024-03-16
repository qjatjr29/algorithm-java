package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 배열돌리기1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int R = Integer.parseInt(input.nextToken());

        int[][] board = new int[N][M];

        for(int i = 0 ; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        int rotateGroupCount = Math.min(N, M) / 2;
        // 2 x 2 배열 -> 1개 회전
        // 5 x 4 배열 -> 2개 회전

        for(int i = 0; i < R; i++) {
            rotate(rotateGroupCount, board);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                bw.write(String.valueOf(board[i][j]) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static void rotate(int count, int[][] board) {

        // 왼쪽 - 위 - 오른쪽 - 아래
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int row = board.length;
        int col = board[0].length;

        for(int i = 0; i < count; i++) {
            int cx = i;
            int cy = i;

            int leftUp = board[i][i];

            int direction = 0;
            while (direction < 4) {

                int nx = cx + dx[direction];
                int ny = cy + dy[direction];

                // 움직이는 방향으로 이동할 수 있는 경우
                if (nx < row - i && ny < col - i && nx >= i && ny >= i) {
                    board[cx][cy] = board[nx][ny];
                    cx = nx;
                    cy = ny;
                    continue;
                }
                direction++;
            }

            board[i + 1][i] = leftUp;
        }
    }
}
