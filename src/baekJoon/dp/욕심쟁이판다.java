package baekJoon.dp;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 욕심쟁이판다 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(input.nextToken());

        int[][] bamboos = new int[n][n];
        int[][] dp = new int[n][n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }

        PriorityQueue<Bamboo> bambooQueue = new PriorityQueue<>((a, b) -> {
            return a.amount - b.amount;
        });

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                bamboos[i][j] = Integer.parseInt(input.nextToken());
                bambooQueue.add(new Bamboo(i, j, bamboos[i][j]));
            }
        }

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        while (!bambooQueue.isEmpty()) {
            Bamboo bamboo = bambooQueue.poll();

            int cx = bamboo.x;
            int cy = bamboo.y;
            int amount = bamboo.amount;
            answer = Math.max(answer, dp[cx][cy]);

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                // 현재 대나무 위치에서 상하좌우로 움직였을 때 대나무 양이 더 많은 경우
                if (bamboos[nx][ny] > amount) {
                    dp[nx][ny] = Math.max(dp[nx][ny], dp[cx][cy] + 1);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Bamboo {
        int x;
        int y;
        int amount;

        Bamboo(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

}
