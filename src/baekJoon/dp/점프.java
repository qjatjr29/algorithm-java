package baekJoon.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        boolean[] rocks = new boolean[n + 1];
        Arrays.fill(rocks, true);

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            rocks[Integer.parseInt(input.nextToken())] = false;
        }

        int maxJump = (int) Math.ceil(Math.sqrt(2 * n)) + 1;

        // i 번째 돌에 도착했고 직전에 j만큼 점프를 했을 때 최소 점프 수
        int[][] dp = new int[n + 1][maxJump + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[1][0] = 0;

        for (int i = 1; i < n; i++) {
            // 현재 돌에 도착할 수 없다면 건너뛰기
            if (!rocks[i]) continue;

            for (int j = 0; j <= maxJump; j++) {
                // 현재 위치와 이전 점프 거리에 대한 정보가 없으면 건너뛰기
                if (dp[i][j] == Integer.MAX_VALUE) continue;

                // 가능한 다음 점프 거리 계산 (j-1, j, j+1)
                // 1보다 작은 값만큼 점프할 수 없음.
                for (int nextJump = Math.max(1, j - 1); nextJump <= j + 1; nextJump++) {
                    int nextPos = i + nextJump;

                    // 범위를 벗어나거나 점프할 수 없는 돌이면 건너뛰기
                    if (nextPos > n || !rocks[nextPos] || nextJump > maxJump) continue;

                    // 다음 위치 DP 업데이트
                    dp[nextPos][nextJump] = Math.min(dp[nextPos][nextJump], dp[i][j] + 1);
                }
            }
        }

        // N번 돌에 도착하는 최소 점프 횟수 찾기
        int answer = Integer.MAX_VALUE;

        for (int j = 1; j <= maxJump; j++) {
            answer = Math.min(answer, dp[n][j]);
        }

        // 도달할 수 없으면 -1
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
