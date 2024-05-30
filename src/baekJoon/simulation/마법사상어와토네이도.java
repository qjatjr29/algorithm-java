package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {

    // 토네이도가 한 칸 이동할 때마다 모래는 일정한 비율로 날라감
    private static final int[][][] SCATTERED_RATE = {
            {{-2, 0, 2}, {-1, 0, 7}, {-1, -1, 10}, {-1, 1, 1}, {0, -2, 5}, {1, -1, 10}, {1, 0, 7}, {2, 0, 2}, {1, 1, 1}},
            {{0, -2, 2}, {0, -1, 7}, {1, -1, 10}, {-1, -1, 1}, {2, 0, 5}, {1, 1, 10}, {0, 1, 7}, {0, 2, 2}, {-1, 1, 1}},
            {{2, 0, 2}, {1, 0, 7}, {1, 1, 10}, {1, -1, 1}, {0, 2, 5}, {-1, 1, 10}, {-1, 0, 7}, {-2, 0, 2}, {-1, -1, 1}},
            {{0, 2, 2}, {0, 1, 7}, {-1, 1, 10}, {1, 1, 1}, {-2, 0, 5}, {-1, -1, 10}, {0, -1, 7}, {0, -2, 2}, {1, -1, 1}}
    };


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int answer = 0;
        int size = Integer.parseInt(input.nextToken());
        SandRegion[][] grid = new SandRegion[size + 1][size + 1];

        int cx = size / 2 + 1;
        int cy = size / 2 + 1;

        for(int i = 1; i <= size; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 1; j <= size; j++) {
                grid[i][j] = new SandRegion(i, j, Integer.parseInt(input.nextToken()));
            }
        }

        int dir = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};


        for(int i = 1; i < size; i++) {
            for(int j = 0; j < 2; j++) {
                int cnt = 0;
                while(cnt < i) {
                    int nx = cx + dx[dir];
                    int ny = cy + dy[dir];

                    // 다음 위치 칸
                    SandRegion next = grid[nx][ny];
                    int sandAmount = next.sandAmount;
                    int sAmount = 0;

                    grid[nx][ny].sandAmount = 0;

                    for(int[] srate : SCATTERED_RATE[dir]) {
                        int sx = nx + srate[0];
                        int sy = ny + srate[1];
                        int rate = srate[2];

                        int amount = (sandAmount * rate) / 100;
                        if(sx <= 0 || sx > size || sy <= 0 || sy > size) {
                            answer += amount;
                        }
                        else {
                            grid[sx][sy].scattered(amount);
                        }
                        sAmount += amount;
                    }

                    sandAmount -= sAmount;

                    int nnx = nx + dx[dir];
                    int nny = ny + dy[dir];

                    if(nnx <= 0 || nnx > size || nny <= 0 || nny > size) {
                        answer += sandAmount;
                    }

                    else {
                        grid[nnx][nny].scattered(sandAmount);
                    }

                    cx = nx;
                    cy = ny;
                    cnt++;
                }

                dir = (dir + 1) % 4;
            }
        }

        for(int i = 1; i < size; i++) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            SandRegion next = grid[nx][ny];
            int sandAmount = next.sandAmount;
            int sAmount = 0;
            next.sandAmount = 0;
            for(int[] srate : SCATTERED_RATE[dir]) {
                int sx = nx + srate[0];
                int sy = ny + srate[1];
                int rate = srate[2];
                int amount = (sandAmount * rate) / 100;
                if(sx <= 0 || sx > size || sy <= 0 || sy > size) {
                    answer += amount;
                }
                else {
                    grid[sx][sy].scattered(amount);
                }
                sAmount += amount;
            }

            sandAmount -= sAmount;

            int nnx = nx + dx[dir];
            int nny = ny + dy[dir];

            if(nnx <= 0 || nnx > size || nny <= 0 || nny > size) {
                answer += sandAmount;
            }

            else {
                grid[nnx][nny].scattered(sandAmount);
            }
            cx = nx;
            cy = ny;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class SandRegion {
        int x;
        int y;
        int sandAmount;

        public SandRegion(int x, int y, int sandAmount) {
            this.x = x;
            this.y = y;
            this.sandAmount = sandAmount;
        }

        public void scattered(int sandAmount) {
            this.sandAmount += sandAmount;
        }
    }

}
