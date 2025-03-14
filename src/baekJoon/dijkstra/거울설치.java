package baekJoon.dijkstra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 거울설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        char[][] house = new char[n][n];
        List<int[]> mirrorPositions = new ArrayList<>();
        List<int[]> doors = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            char[] chars = input.nextToken().toCharArray();
            for (int j = 0; j < n; j++) {
                house[i][j] = chars[j];
                if (house[i][j] == '!') mirrorPositions.add(new int[] {i, j});
                if (house[i][j] == '#') doors.add(new int[] {i, j});
            }
        }

        int answer = 2501;
        int[] start = doors.get(0);
        int[] end = doors.get(1);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][][] visited = new boolean[n][n][4];

        for (int i = 0; i < 4; i++) {
            visited[start[0]][start[1]][i] = true;
            pq.add(new Node(start[0], start[1], i, 0));
        }

        while (!pq.isEmpty()) {
            Node here = pq.poll();

            int x = here.x;
            int y = here.y;
            int dir = here.d;
            int count = here.mirrorCount;

            if (x == end[0] && y == end[1]) {
                answer = Math.min(answer, count);
                continue;
            }
            visited[x][y][dir] = true;
            if (house[x][y] == '!') {
                if (dir == 0 || dir == 1) {
                    int nextX = x + dx[2];
                    int nextY = y + dy[2];
                    if (!checkRange(nextX, nextY, house) && !visited[nextX][nextY][2]) {
                        pq.add(new Node (nextX, nextY, 2, count + 1));
                    }

                    nextX = x + dx[3];
                    nextY = y + dy[3];
                    if (!checkRange(nextX, nextY, house) && !visited[nextX][nextY][3]) {
                        pq.add(new Node (nextX, nextY, 3, count + 1));
                    }
                }

                if (dir == 2 || dir == 3) {
                    int nextX = x + dx[0];
                    int nextY = y + dy[0];
                    if (!checkRange(nextX, nextY, house) && !visited[nextX][nextY][0]) {
                        visited[nextX][nextY][0] = true;
                        pq.add(new Node (nextX, nextY, 0, count + 1));
                    }

                    nextX = x + dx[1];
                    nextY = y + dy[1];
                    if (!checkRange(nextX, nextY, house) && !visited[nextX][nextY][1]) {
                        visited[nextX][nextY][1] = true;
                        pq.add(new Node (nextX, nextY, 1, count + 1));
                    }
                }

            }
            // 거울 설치 안하는 경우 또는 아무것도 없는 경우
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (checkRange(nx, ny, house)) continue;
            if (visited[nx][ny][dir]) continue;
            pq.add(new Node(nx, ny, dir, count));
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkRange(int nextX, int nextY, char[][] house) {
        return nextX < 0 || nextX >= house.length || nextY < 0 || nextY >= house.length || house[nextX][nextY] == '*';
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int d;
        int mirrorCount;

        public Node(int x, int y, int d, int mirrorCount) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.mirrorCount = mirrorCount;
        }

        @Override
        public int compareTo(Node o) {
            return this.mirrorCount - o.mirrorCount;
        }
    }
}
