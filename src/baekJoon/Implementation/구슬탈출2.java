package baekJoon.Implementation;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 구슬탈출2 {

    private static final char HALL = 'O';
    private static final char WALL = '#';
    private static int tx, ty;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        int answer = -1;
        char[][] board = new char[n][m];
        visited = new boolean[n][m][n][m];

        int rx = 0, ry = 0;
        int bx = 0, by = 0;
        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            char[] chars = input.nextToken().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = chars[j];
                if (board[i][j] == HALL) {
                    tx = i;
                    ty = j;
                }
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                }
                if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int redX = cur.rx;
            int redY = cur.ry;
            int blueX = cur.bx;
            int blueY = cur.by;

            if (cur.count > 10) {
                break;
            }

            if (redX == tx && redY == ty) {
                answer = cur.count;
                break;
            }

            for (int i = 0; i < 4; i++) {

                int[] nRed = tilt(redX, redY, i, board);
                int[] nBlue = tilt(blueX, blueY, i, board);

                // 파란 구슬이 구멍에 빠지는 경우는 안된다.
                if (nBlue[0] == tx && nBlue[1] == ty) continue;

                // 같은 장소로 이동했다면, 이동하는 방향에 더 가까웠던 구슬이 해당 장소에 위치.
                if (nRed[0] == nBlue[0] && nRed[1] == nBlue[1]) {
                    // 위
                    if (i == 0) {
                        if (redX < blueX) nBlue[0] += 1;
                        else nRed[0] += 1;
                    }
                    // 아래
                    if (i == 1) {
                        if (redX > blueX) nBlue[0] -= 1;
                        else nRed[0] -= 1;
                    }
                    // 왼쪽
                    if (i == 2) {
                        if (redY < blueY) nBlue[1] += 1;
                        else nRed[1] += 1;
                    }
                    // 오른쪽
                    if (i == 3) {
                        if (redY > blueY) nBlue[1] -= 1;
                        else nRed[1] -= 1;
                    }
                }

                if (visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]]) continue;
                visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]] = true;

                pq.add(new Node(nRed[0], nRed[1], nBlue[0], nBlue[1], cur.count + 1));
            }

        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] tilt(int x, int y, int dir, char[][] board) {

        while (true) {
            x = x + dx[dir];
            y = y + dy[dir];
            if (board[x][y] == WALL) {
                x -= dx[dir];
                y -= dy[dir];
                break;
            }
            if (board[x][y] == HALL) break;
        }
        return new int[]{x, y};
    }

    private static class Node implements Comparable<Node> {
        int rx;
        int ry;
        int bx;
        int by;
        int count;

        public Node() { }

        public Node(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

}
