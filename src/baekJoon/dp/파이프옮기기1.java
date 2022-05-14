package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;
import java.util.function.Supplier;

public class 파이프옮기기1 {

    static int N;
    static int[][] map;

    static int answer;

    public static void sol(int state, int x, int y) {
        if(x >= N || y >= N) return;
        if(map[x][y] == 1) return;
        if(state == 2)  {
            if(map[x - 1][y] == 1 || map[x][y - 1] == 1) return;
        }
        if(x == N - 1 && y == N - 1) {
            answer++;
            return;
        }
        if(state == 0) {
            sol(state, x , y + 1);
            sol(2, x + 1, y + 1);
        } else if(state == 1) {
            sol(state, x + 1, y);
            sol(2, x + 1, y + 1);
        } else {
            sol(0, x, y + 1);
            sol(1, x + 1, y);
            sol(2, x + 1, y + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int state = Integer.parseInt(st.nextToken());
                map[i][j] = state;
            }
        }

        sol(0, 0, 1);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
