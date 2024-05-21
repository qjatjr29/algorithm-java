package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 소용돌이예쁘게출력하기 {

    private static int r1;
    private static int c1;
    private static int r2;
    private static int c2;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        r1 = Integer.parseInt(input.nextToken());
        c1 = Integer.parseInt(input.nextToken());
        r2 = Integer.parseInt(input.nextToken());
        c2 = Integer.parseInt(input.nextToken());

        int size = Math.max(Math.abs(r1), Math.abs(c1));
        size = Math.max(size, Math.abs(r2));
        size = Math.max(size, Math.abs(c2));

        int[][] board = new int[r2 - r1 + 1][c2 - c1 + 1];
        fillArray(size, board);

        int maxLen = (int) Math.log10(max);
        int len = 0;

        for(int i = 0; i <= r2 - r1; i++) {
            for(int j = 0; j <= c2 - c1; j++) {
                len = maxLen - (int) Math.log10(board[i][j]);
                for (int k = 0; k < len; k++) {
                    bw.write(" ");
                }
                bw.write(String.valueOf(board[i][j]) + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }


    private static void fillArray(int count, int[][] board) {

//        // 왼쪽 - 위 - 오른쪽 - 아래
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        int size = count * 2 + 1;
        int value = size * size;

        for(int i = 0; i <= count; i++) {
            int cx = size - i - 1;
            int cy = size - i - 1;

            int sValue = value;

            int direction = 0;
            while (direction < 4) {

                if(cx >= r1 + count && cx <= r2 + count && cy >= c1 + count && cy <= c2 + count) {
                    board[cx - r1- count][cy - c1 - count] = value;
                    max = Math.max(max, value);
                }

                int nx = cx + dx[direction];
                int ny = cy + dy[direction];

                // 움직이는 방향으로 이동할 수 있는 경우
                if (nx < size - i && ny < size - i && nx >= i && ny >= i) {
                    cx = nx;
                    cy = ny;
                    value--;
                    continue;
                }
                direction++;
            }
            if(cx >= r1 + count && cx <= r2 + count && cy >= c1 + count && cy <= c2 + count) {
                board[cx - r1- count][cy - c1 - count] = sValue;
                max = Math.max(max, sValue);
            }
        }
    }
}
