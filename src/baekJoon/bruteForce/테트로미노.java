package baekJoon.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 테트로미노 {

    private static int[][] map;
    private static boolean[][] visited;
    private static int N, M, answer;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map =new int[N][M];
        visited =new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                sol(1, i, j, map[i][j]);
                visited[i][j] = false;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();


    }

    private static void sol(int cnt, int x, int y, int sum) {
        if(cnt == 4) {
            answer = max(answer, sum);
            return;
        }

        if(cnt == 2) {
            List<Integer> possible = new ArrayList<>();
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny]) continue;
                possible.add(i);
            }
            if(possible.size() == 2) {
                int first = possible.get(0);
                int second = possible.get(1);

                first = map[x + dx[first]][y + dy[first]];
                second = map[x + dx[second]][y + dy[second]];

                answer = max(answer, sum + first + second);
            } else if(possible.size() == 3) {
                int first = possible.get(0);
                int second = possible.get(1);
                int third = possible.get(2);

                first = map[x + dx[first]][y + dy[first]];
                second = map[x + dx[second]][y + dy[second]];
                third = map[x + dx[third]][y + dy[third]];

                answer = max(answer, sum + first + second);
                answer = max(answer, sum + first + third);
                answer = max(answer, sum + second + third);
            }
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(visited[nx][ny]) continue;
            visited[nx][ny] = true;
            sol(cnt + 1, nx,  ny, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

}
