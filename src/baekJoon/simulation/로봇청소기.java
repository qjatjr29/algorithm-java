package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 로봇청소기 {

    private static final int WALL = 1;
    private static int[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());
        int answer = 0;

        int r = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(input.nextToken());
        int sc = Integer.parseInt(input.nextToken());
        int d = Integer.parseInt(input.nextToken());
        board = new int[r][c];
        visited = new boolean[r][c];
        Robot robot = new Robot(sr, sc, d);

        for(int i = 0; i < r; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        do {
            robot.clean();
        } while (robot.move());

        answer = robot.count;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Robot {

        // 북 동 남 서
        private static final int[] dx = {-1, 0, 1, 0};
        private static final int[] dy = {0, 1, 0, -1};

        int x;
        int y;
        int direction;
        int count;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void clean() {
            if (!visited[this.x][this.y]) {
                visited[this.x][this.y] = true;
                count++;
            }
        }

        public boolean move() {
            if(!checkMove()) {
                int nx = this.x + dx[(direction + 2) % 4];
                int ny = this.y + dy[(direction + 2) % 4];
                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == WALL) {
                    return false;
                }
                this.x = nx;
                this.y = ny;
                return true;
            }

            for(int i = 0; i < 4; i++) {
                rotate();
                int nx = this.x + dx[direction];
                int ny = this.y + dy[direction];
                if (isInMap(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                this.x = nx;
                this.y = ny;
                break;
            }
            return true;
        }

        private void rotate() {
            this.direction = (this.direction + 3) % 4;
        }

        private boolean checkMove() {
            boolean check = false;
            for(int i = 0; i < 4; i++) {
                int nx = this.x + dx[i];
                int ny = this.y + dy[i];
                if (isInMap(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                check = true;
            }
            return check;
        }

        private boolean isInMap(int nx, int ny) {
            return nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == WALL;
        }
    }
}
