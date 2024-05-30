package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 로봇 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());

        int[][] room = new int[R][C];

        input = new StringTokenizer(br.readLine());
        int obstacleCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < obstacleCount; i++) {
            input = new StringTokenizer(br.readLine());
            int ox = Integer.parseInt(input.nextToken());
            int oy = Integer.parseInt(input.nextToken());
            room[ox][oy] = -1;
        }

        input = new StringTokenizer(br.readLine());
        int cx = Integer.parseInt(input.nextToken());
        int cy = Integer.parseInt(input.nextToken());

        room[cx][cy] = -1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        input = new StringTokenizer(br.readLine());
        int[] direction = new int[4];
        for(int i = 0; i < 4; i++) {
            int dir = Integer.parseInt(input.nextToken());
            direction[i] = dir;
        }

        int idx = 0;
        int count = 0;
        while(true) {
            int dir = direction[idx] - 1;

            while(true) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                if(!isInRoom(nx, ny, room) || room[nx][ny] == -1) {
                    break;
                }
                cx = nx;
                cy = ny;
                room[cx][cy] = -1;
                count++;
            }
            idx = (idx + 1) % 4;
            if (idx == 0) {
                if (count == 0) {
                    break;
                }
                count = 0;
            }
        }
        bw.write(String.valueOf(cx) + " " + String.valueOf(cy));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isInRoom(int x, int y, int[][] room) {
        return x >= 0 && x < room.length && y >= 0 && y < room[0].length;
    }
}
