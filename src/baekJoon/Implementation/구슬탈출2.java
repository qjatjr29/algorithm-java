package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출2 {

    private static final int WALL = 1;
    private static final int EMPTY = 0;
    private static final int BALL = 2;
    private static final int HOLE = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int answer = -1;
        int[][] board = new int[N][M];

        Ball red = new Ball(0, 0);
        Ball blue = new Ball(0, 0);

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            String inputStr = input.nextToken();
            for(int j = 0; j < M; j++) {

                char state = inputStr.charAt(j);
                if(state == '#') {
                    board[i][j] = WALL;
                }

                if(state == 'R') {
                    red = new Ball(i, j);
                }

                if(state == 'B') {
                    blue = new Ball(i, j);
                }

                if(state == 'O') {
                    board[i][j] = HOLE;
                }
            }
        }

        answer = moveBall(red, blue, board, answer);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int moveBall(Ball red, Ball blue, int[][] board, int answer) {

        Direction[] directions = new Direction[] {Direction.LEFT,
                Direction.RIGHT,
                Direction.UP,
                Direction.DOWN};

        Queue<Node> pq = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[10][10][10][10];

        visited[red.x][red.y][blue.x][blue.y] = true;
        pq.add(new Node(red, blue, 0));

        while(!pq.isEmpty()) {

            Node here = pq.poll();
            Ball redBall = here.redBall;
            Ball blueBall = here.blueBall;
            int count = here.count;

            if(count > 10) {
                continue;
            }

            if(redBall.isInHole(board)) {
                answer = count;
                break;
            }

           // 4가지 방향으로 이동
            for(int i = 0; i < 4; i++) {
                Direction direction = directions[i];
                Ball nextRed = redBall.move(direction, board);
                Ball nextBlue = blueBall.move(direction, board);

                // 실패 케이스 - 파란색 공이 hole에 들어간 경우
                if(nextBlue.isInHole(board)) {
                    continue;
                }

                // 두 구슬이 같은 위치에 있는 경우 - 위치 수정해 주어야함
                if(nextRed.isSamePosition(nextBlue)) {
                    // 기울이기전에 기울이는 방향으로 더 많이 가있던 공은 그대로
                    if(direction == Direction.LEFT) {
                        if(redBall.y > blueBall.y) {
                            nextRed.y++;
                        }
                        else nextBlue.y++;
                    }

                    if(direction == Direction.RIGHT) {
                        if(redBall.y < blueBall.y) {
                            nextRed.y--;
                        }
                        else nextBlue.y--;
                    }

                    if(direction == Direction.UP) {
                        if(redBall.x > blueBall.x) {
                            nextRed.x++;
                        }
                        else nextBlue.x++;
                    }

                    if(direction == Direction.DOWN) {
                        if(redBall.x < blueBall.x) {
                            nextRed.x--;
                        }
                        else nextBlue.x--;
                    }
                }

                if(visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y]) {
                    continue;
                }

                visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y] = true;
                pq.add(new Node(nextRed, nextBlue, count + 1));
            }
        }
        return answer;
    }

    private static class Node {
        Ball redBall;
        Ball blueBall;
        int count;

        public Node(Ball redBall, Ball blueBall, int count) {
            this.redBall = redBall;
            this.blueBall = blueBall;
            this.count = count;
        }
    }

    private static class Ball {
        int x;
        int y;

        public Ball(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Ball move(Direction dir, int[][] board) {

            int[] d = dir.getDirection();
            int cx = x;
            int cy = y;

            while(true) {

                int nx = cx + d[0];
                int ny = cy + d[1];

                if(nx == 0 || ny == 0 || nx == board.length - 1 || ny == board[0].length - 1) {
                    break;
                }

                if(board[nx][ny] == WALL) {
                    break;
                }

                if(board[nx][ny] == HOLE) {
                    cx = nx;
                    cy = ny;
                    break;
                }

                cx = nx;
                cy = ny;
            }

            return new Ball(cx, cy);
        }

        public boolean isSamePosition(Ball other) {
            return this.x == other.x && this.y == other.y;
        }

        public boolean isInHole(int[][] board) {
            return board[x][y] == HOLE;
        }
    }

    public enum Direction {
        UP(new int[] {-1, 0}),
        DOWN(new int[] {1, 0}),
        LEFT(new int[] {0, -1}),
        RIGHT(new int[] {0, 1});

        private int[] direction;

        Direction(int[] direction) {
            this.direction = direction;
        }

        public int[] getDirection() {
            return direction;
        }
    }
}
