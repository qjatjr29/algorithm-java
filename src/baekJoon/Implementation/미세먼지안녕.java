package baekJoon.Implementation;

import java.io.*;
import java.util.*;

public class 미세먼지안녕 {

    private static int R, C, T;
    private static int[][] map;
    private static int[][] sum;
    private static List<Position> q;
    private static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static Position airMachine;

    private static void moveDust(int x, int y) {

            int dust = map[x][y];

            int addDust = dust / 5;
            int cnt = 0;
            for(int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (nx == airMachine.x && ny == 0) continue;
                if (nx == airMachine.y && ny == 0) continue;

                cnt++;
                sum[nx][ny] += addDust;
            }
            map[x][y] -= (cnt * addDust);
    }

    public static void wind() {
        int x1 = airMachine.x;
        int x2 = airMachine.y;

        // 위쪽
        int temp = map[x1][C -1];
        for(int i = C - 1; i >= 2; i--) {
            map[x1][i] = map[x1][i - 1];
        }
        map[x1][1] = 0;
        int temp2 = map[0][C - 1];
        for(int i = 0; i < x1 - 1; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        map[x1 - 1][C - 1] = temp;
        temp = map[0][0];
        for(int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        map[0][C - 2] = temp2;
        for(int i = x1 - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        map[1][0] = temp;

        // 아래쪽
        temp = map[x2][C -1];
        for(int i = C - 1; i >= 2; i--) {
            map[x2][i] = map[x2][i - 1];
        }
        map[x2][1] = 0;
        temp2 = map[R - 1][C - 1];
        for(int i = R - 1; i > x2 + 1; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        map[x2 + 1][C - 1] = temp;
        temp = map[R - 1][0];
        for(int i = 0; i < C - 2; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        map[R - 1][C - 2] = temp2;
        for(int i = x2 + 1; i < R - 2; i++) {
            map[i][0] = map[i + 1][0];
        }
        map[R - 2][0] = temp;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        q = new ArrayList<>();
        airMachine = new Position(-1, -1);
        map = new int[R][C];
        sum = new int[R][C];

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) {
                    if(airMachine.x == -1) {
                        airMachine = new Position(i, i + 1);
                    }
                }
            }
        }

        for(int i = 0; i < T; i++) {
            for(int j = 0; j < R; j++) {
                for(int z = 0; z < C; z++) {
                    if(map[j][z] != 0 && map[j][z] != -1) {
                        moveDust(j, z);
                    }
                }
            }
            for(int j = 0; j < R; j++) {
                for(int z = 0; z < C; z++) {
                    map[j][z] += sum[j][z];
                }
            }
            sum = new int[R][C];
            wind();
        }

        int answer = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                answer += map[i][j];
            }
        }
        answer += 2;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
