package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 레이저통신 {

    private static final char WALL = '*';
    private static final char EMPTY = '.';
    private static final char TARGET = 'C';

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int width = Integer.parseInt(input.nextToken());
        int height = Integer.parseInt(input.nextToken());

        char[][] map = new char[height][width];
        answer = 987654321;

        int sx = 0;
        int sy = 0;

        for(int i = 0; i < height; i++) {
            input = new StringTokenizer(br.readLine());
            String str = input.nextToken();
            for(int j = 0; j < width; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == TARGET) {
                    sx = i;
                    sy = j;
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue();

        pq.add(new Node(sx, sy, DIRECTION.UP, 0));
        pq.add(new Node(sx, sy, DIRECTION.DOWN, 0));
        pq.add(new Node(sx, sy, DIRECTION.LEFT, 0));
        pq.add(new Node(sx, sy, DIRECTION.RIGHT, 0));

        boolean[][][] visited = new boolean[height][width][4];

        while(!pq.isEmpty()) {
            Node cNode = pq.poll();
            int cx = cNode.x;
            int cy = cNode.y;

            DIRECTION direction = cNode.direction;
            int cCount = cNode.count;

            if(map[cx][cy] == TARGET && (!(cx == sx && cy == sy))) {
                answer = Math.min(answer, cCount);
                break;
            }
            visited[cx][cy][direction.direction] = true;

            if(cCount > answer) {
                continue;
            }

            // 그냥 그대로 진행
            int nx = cx + direction.dx;
            int ny = cy + direction.dy;
            int nextDirection = direction.direction;

            if(nx >= 0 && nx < height && ny >= 0 && ny < width
                    && !visited[nx][ny][nextDirection] && (map[nx][ny] != WALL)) {
                pq.add(new Node(nx, ny, direction, cCount));
            }

            // 왼쪽으로 회전
            DIRECTION leftDirection = direction.rotateLeft();
            nx = cx + leftDirection.dx;
            ny = cy + leftDirection.dy;
            nextDirection = leftDirection.direction;

            if(nx >= 0 && nx < height && ny >= 0 && ny < width
                    && !visited[nx][ny][nextDirection] && (map[nx][ny] != WALL)) {
                pq.add(new Node(nx, ny, leftDirection, cCount + 1));
            }

            // 오른쪽으로 회전
            DIRECTION rightDirection = direction.rotateRight();
            nx = cx + rightDirection.dx;
            ny = cy + rightDirection.dy;
            nextDirection = rightDirection.direction;

            if(nx >= 0 && nx < height && ny >= 0 && ny < width
                    && !visited[nx][ny][nextDirection] && (map[nx][ny] != WALL)) {
                pq.add(new Node(nx, ny, rightDirection, cCount + 1));
            }

        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        DIRECTION direction;
        int count;

        public Node(int x, int y, DIRECTION direction, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    private enum DIRECTION {

        UP(0, -1, 0) {
            @Override
            public DIRECTION rotateLeft() {
                return LEFT;
            }

            @Override
            public DIRECTION rotateRight() {
                return RIGHT;
            }
        },
        DOWN(1, 1, 0) {
            @Override
            public DIRECTION rotateLeft() {
                return LEFT;
            }

            @Override
            public DIRECTION rotateRight() {
                return RIGHT;
            }
        },
        LEFT(2, 0, -1) {
            @Override
            public DIRECTION rotateLeft() {
                return DOWN;
            }

            @Override
            public DIRECTION rotateRight() {
                return UP;
            }
        },
        RIGHT(3, 0, 1) {
            @Override
            public DIRECTION rotateLeft() {
                return UP;
            }

            @Override
            public DIRECTION rotateRight() {
                return DOWN;
            }
        };

        int direction;
        int dx;
        int dy;

        DIRECTION(int direction, int dx, int dy) {
            this.direction = direction;
            this.dx = dx;
            this.dy = dy;
        }

        abstract public DIRECTION rotateLeft();
        abstract public DIRECTION rotateRight();
    }
}
