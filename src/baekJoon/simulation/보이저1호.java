package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 보이저1호 {

    private static final int MAX_SIZE = 500;
    private static final String INFINITE = "Voyager";
    private static int answer = 0;
    private static char direction;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());

        char[][] space = new char[MAX_SIZE + 2][MAX_SIZE + 2];
        boolean[][][] visited = new boolean[MAX_SIZE + 2][MAX_SIZE + 2][4];

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            String row = input.nextToken();
            for(int j = 0; j < M; j++) {
                space[i][j] = row.charAt(j);
            }
        }

        input = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(input.nextToken()) - 1;
        int sy = Integer.parseInt(input.nextToken()) - 1;

        for(int i = 0; i < 4; i++) {
            signalCount(sx, sy, i, space);
            if(answer == Integer.MAX_VALUE) break;
        }

        bw.write(direction);
        bw.newLine();
        if(answer == Integer.MAX_VALUE) {
            bw.write(INFINITE);
        }
        else {
            bw.write(String.valueOf(answer));
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void signalCount(int sx, int sy, int dir, char[][] space) {

        int rl = space.length;
        int cl = space[0].length;
        boolean[][][] visited = new boolean[MAX_SIZE + 2][MAX_SIZE + 2][4];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int count = 0;
        int nx = sx;
        int ny = sy;
        int cd = dir;

        while (true) {

            count++;

            if(!visited[nx][ny][cd]) {
                visited[nx][ny][cd] = true;
            } else {
                // 무한대
                answer = Integer.MAX_VALUE;
                direction = convertDirectionStr(dir);
                break;
            }

            nx += dx[cd];
            ny += dy[cd];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || space[nx][ny] == 'C') {
                if(answer < count) {
                    answer = count;
                    direction = convertDirectionStr(dir);
                }
                break;
            }

            if (space[nx][ny] == '/' || space[nx][ny] == '\\') {
                cd = reflectDirection(space[nx][ny], cd);
            }
        }
    }

    private static int reflectDirection(char cmd, int dir) {
        if(cmd == '/') {
            if(dir == 0) return 1;
            if(dir == 1) return 0;
            if(dir == 2) return 3;
            if(dir == 3) return 2;
        }
        if(cmd == '\\') {
            if(dir == 0) return 3;
            if(dir == 1) return 2;
            if(dir == 2) return 1;
            if(dir == 3) return 0;
        }
        return 0;
    }

    private static char convertDirectionStr(int dir) {
        if(dir == 0) return 'U';
        if(dir == 1) return 'R';
        if(dir == 2) return 'D';
        if(dir == 3) return 'L';
        return 'U';
    }
}
