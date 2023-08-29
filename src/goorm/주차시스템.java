package goorm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 주차시스템 {
    private static final int EMPTY = 0;
    private static final int PARKING_AREA = 1;
    private static final int DISABLED_AREA = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] parking = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        int answer = 0;

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                parking[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(parking[i][j] != PARKING_AREA && !visited[i][j])
                    answer = Math.max(answer, calculateScore(i, j, parking, visited));
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static int calculateScore(int x, int y, int[][] parking, boolean[][] visited) {
        int rtn = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        visited[x][y] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});

        while(!queue.isEmpty()) {

            int[] area = queue.poll();

            int cx = area[0];
            int cy = area[1];

            if(parking[cx][cy] == EMPTY) rtn += 1;
            if(parking[cx][cy] == DISABLED_AREA) rtn -= 2;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= parking.length || ny < 0 || ny >= parking[0].length) continue;
                if(visited[nx][ny]) continue;
                if(parking[nx][ny] == PARKING_AREA) continue;
                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny});
            }

        }

        return rtn;
    }

}
