package baekJoon.Implementation;

import java.io.*;
import java.util.*;

public class 가스관 {

    private static final char EMPTY = '.';
    private static char answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());
        answer = ' ';

        char[][] map = new char[r][c];

        for (int i = 0; i < r; i++) {
            input = new StringTokenizer(br.readLine());
            map[i] = input.nextToken().toCharArray();
        }

        int ax = 0;
        int ay = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == EMPTY) {
                    // 주변에 블록과 연결되어야 하는지 확인
                    isConnection(i, j, map);
                    if (answer != ' ') {
                        ax = i + 1;
                        ay = j + 1;
                        break;
                    }
                }
            }
            if (answer != ' ') break;
        }
        bw.write(ax + " " + ay + " " + answer);
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void isConnection(int x, int y, char[][] map) {

        // 상 하 좌 우 순
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[] connected = new boolean[4];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
            if (map[nx][ny] == EMPTY) continue;

            // i 번째 방향에 연결할 블록이 있는 경우.
            if (isConnected(nx, ny, i, map)) connected[i] = true;
        }

        // 모든 방향에 블록이 있는 경우: +
        if (connected[0] && connected[1] && connected[2] && connected[3]) {
            answer = '+';
        }

        // 왼쪽 오른쪽에만 있는 경우: -
        else if (connected[2] && connected[3]) {
            answer = '-';
        }

        // 위 아래에만 있는 경우: |
        else if (connected[0] && connected[1]) {
            answer = '|';
        }

        // 위 - 왼쪽에만 있는 경우: 3
        else if (connected[0] && connected[2]) {
            answer = '3';
        }

        // 위 - 오른쪽만 있는 경우: 2
        else if (connected[0] && connected[3]) {
            answer = '2';
        }

        // 아래 - 왼쪽만 있는 경우: 4
        else if (connected[1] && connected[2]) {
            answer = '4';
        }

        // 아래 - 오른쪽만 있는 경우: 1
        else if (connected[1] && connected[3]) {
            answer = '1';
        }
    }

    private static boolean isConnected(int x, int y, int dir, char[][] map) {

        boolean rtn = false;
        switch (dir) {

            // 이전 위치에서 dir 방향으로 이동했을 때 (x,y)의 위치로 와서 블록들과 연결되어 있는지 확인
            case 0:
                rtn = map[x][y] == '|' || map[x][y] == '+' || map[x][y] == '1' || map[x][y] == '4';
                break;
            case 1:
                rtn = map[x][y] == '|' || map[x][y] == '+' || map[x][y] == '2' || map[x][y] == '3';
                break;
            case 2:
                rtn = map[x][y] == '-' || map[x][y] == '+' || map[x][y] == '1' || map[x][y] == '2';
                break;
            case 3:
                rtn = map[x][y] == '-' || map[x][y] == '+' || map[x][y] == '3' || map[x][y] == '4';
                break;
            default:
                break;
        }

        return rtn;
    }

}
