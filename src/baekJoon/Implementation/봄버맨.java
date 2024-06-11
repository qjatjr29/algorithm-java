package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 봄버맨 {

    private static final char BOMB = 'O';
    private static final char EMPTY = '.';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());

        int[][] map = new int[R][C];
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, -1, 1, 0, 0};

        int time = 0;
        for(int i = 0; i < R; i++) {
            input = new StringTokenizer(br.readLine());
            String row = input.nextToken();
            for(int j = 0; j < C; j++) {
                char state = row.charAt(j);
                if(state == BOMB) {
                    map[i][j] = time;
                    continue;
                }
                map[i][j] = -1;
            }
        }

        time++;
        while(time < N) {
            time++;
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(map[i][j] == -1) {
                        map[i][j] = time;
                    }
                }
            }

            if(time == N) {
                break;
            }

            time++;
            Queue<int[]> bombQueue = new ArrayDeque<>();
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(time == map[i][j] + 3) {
                        for(int z = 0; z < 5; z++) {

                            int bx = i + dx[z];
                            int by = j + dy[z];
                            if(bx < 0 || bx >= R || by < 0 || by >= C) {
                                continue;
                            }

                            bombQueue.add(new int[] {bx, by});
                        }
                    }
                }
            }

            while(!bombQueue.isEmpty()) {
                int[] bomb = bombQueue.poll();
                map[bomb[0]][bomb[1]] = -1;
            }
        }

        char[][] answer = new char[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == -1) {
                    answer[i][j] = EMPTY;
                }
                else {
                    answer[i][j] = BOMB;
                }
                bw.write(String.valueOf(answer[i][j]));
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
