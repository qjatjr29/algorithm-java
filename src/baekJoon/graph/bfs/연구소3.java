package baekJoon.graph.bfs;

import java.io.*;
import java.util.*;

public class 연구소3 {

    private static int spreadCount;
    private static int answer;
    private static List<Virus> viruses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        int[][] board = new int[n][n];
        spreadCount = n * n;
        viruses = new ArrayList<Virus>();
        answer = 987654321;

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
                if (board[i][j] == 2) {
                    viruses.add(new Virus(i, j));
                }
                if (board[i][j] == 1) spreadCount--;
            }
        }

        determineVirus(0, 0, new int[m], board);
        if (answer == 987654321) answer = -1;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void determineVirus(int cnt, int idx, int[] virus, int[][] board) {
        if (cnt == virus.length) {
            answer = Math.min(answer, spreadVirus(virus, board));
            return;
        }

        if (idx >= viruses.size()) return;

        for (int i = idx; i < viruses.size(); i++) {
            virus[cnt] = i;
            determineVirus(cnt + 1, i + 1, virus, board);
        }
    }

    private static int spreadVirus(int[] virusIdx, int[][] board) {

        int time = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int virusCount = viruses.size();

        boolean[][] visited = new boolean[board.length][board[0].length];
        PriorityQueue<Virus> queue = new PriorityQueue<>();

        for (int v : virusIdx) {
            Virus virus = viruses.get(v);
            queue.add(virus);
            visited[virus.x][virus.y] = true;
        }

        while (!queue.isEmpty()) {
            Virus virus = queue.poll();
            time = virus.time;

            if (virusCount == spreadCount) continue;
            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || visited[nx][ny]) {
                    continue;
                }
                if (board[nx][ny] == 1) continue;
                if (board[nx][ny] != 2) {
                    virusCount++;
                }
                visited[nx][ny] = true;
                queue.add(new Virus(nx, ny, virus.time + 1));
            }
        }

        if (virusCount != spreadCount) return answer;

        return time;
    }

    private static class Virus implements Comparable<Virus> {
        int x;
        int y;
        int time;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
            this.time = 0;
        }

        public Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Virus o) {
            return this.time - o.time;
        }
    }

}
