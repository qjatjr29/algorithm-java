package baekJoon.simulation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 로봇청소기 {

    private static int sx, sy;
    private static List<int[]> dirty;
    private static int[][][][] distance;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input;

        while (true) {
            input = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(input.nextToken());
            int h = Integer.parseInt(input.nextToken());

            if (w == 0 && h == 0) break;
            char[][] room = new char[h][w];
            distance = new int[h][w][h][w];
            answer = 987654321;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    for (int k = 0; k < h; k++) {
                        for (int l = 0; l < w; l++) {
                            distance[i][j][k][l] = 987654321;
                        }
                    }
                }
            }

            dirty = new ArrayList<int[]>();
            for (int i = 0; i < h; i++) {
                input = new StringTokenizer(br.readLine());
                char[] c = input.nextToken().toCharArray();
                for (int j = 0; j < w; j++) {
                    room[i][j] = c[j];

                    if (room[i][j] == 'o') {
                        sx = i;
                        sy = j;
                    }
                    if (room[i][j] == '*') {
                        dirty.add(new int[] {i, j});
                    }
                }
            }

            // 시작지점에서 각 더러운칸까지의 최솟값 구하기
            bfs(sx, sy, room);

            boolean check = true;
            for (int[] dir : dirty) {
                if (distance[sx][sy][dir[0]][dir[1]] == 987654321) {
                    check = false;
                    break;
                }
                bfs(dir[0], dir[1], room);
            }


            if (check) {
                dfs(0, new boolean[dirty.size()], new int[dirty.size()]);
                if (answer == 987654321) answer = -1;
            }

            if (!check) {
                bw.write("-1");
            }
            else {
                bw.write(String.valueOf(answer));
            }

            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cnt, boolean[] check, int[] order) {

        if (cnt == dirty.size()) {
            int dis = solve(order);
            answer = Math.min(answer, dis);
        }

        for (int i = 0; i < order.length; i++) {
            if (check[i]) continue;
            check[i] = true;
            order[cnt] = i;
            dfs(cnt + 1, check, order);
            check[i] = false;
        }
    }

    private static int solve(int[] order) {
        int dis = 0;

        int x = sx;
        int y = sy;
        for (int i = 0; i < order.length; i++) {
            int[] next = dirty.get(order[i]);
            dis += distance[x][y][next[0]][next[1]];

            if (answer < dis) {
                return answer;
            }

            x = next[0];
            y = next[1];
        }
        return dis;
    }

    private static void bfs(int sx, int sy, char[][] room) {

        boolean[][] visited = new boolean[room.length][room[0].length];
        visited[sx][sy] = true;

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(sx, sy, 0));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            Node node = pq.poll();

            int cx = node.x;
            int cy = node.y;
            for (int i = 0; i < 4; i++) {

                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= room.length || ny >= room[0].length) {
                    continue;
                }
                if (room[nx][ny] == 'x') continue;

                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                pq.add(new Node(nx, ny, node.count + 1));

                if (room[nx][ny] == '*' ) {
                    distance[sx][sy][nx][ny] = Math.min(distance[sx][sy][nx][ny], node.count + 1);
                    distance[nx][ny][sx][sy] = distance[sx][sy][nx][ny];
                }
            }
        }

    }

    private static boolean isContain(int x, int y) {
        for (int[] dir : dirty) {
            if (dir[0] == x && dir[1] == y) return true;
        }
        return false;
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int count;
        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
