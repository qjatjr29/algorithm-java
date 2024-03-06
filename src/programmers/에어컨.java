package programmers;

import java.util.Arrays;

public class 에어컨 {

    private static final int INF = 987654321;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = 0;

        temperature += 10;
        t1 += 10;
        t2 += 10;

        int time = onboard.length;

        // i 분의 j 도의 온도를 만드는 최소 전력값 dp 배열
        int[][] dp = new int[time + 1][53];

        for(int i = 0; i <= time; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][temperature] = 0;

        for(int i = 0; i < time; i++) {

            for(int j = 0; j < 52; j++) {

                if(dp[i][j] == INF) continue;

                if(onboard[i] == 1 && (j < t1 || j > t2)) continue;

                // 온도 조작 3가지 경우
                // 1. 온도 올리기

                // 1 - 1. 에어컨을 가동해 실내온도를 높이는 방법
                dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + a);

                // 1 - 2. 에어컨을 꺼서 실내온도를 높임 (실외 온도가 실내온도보다 높은경우 가능)
                if(j < temperature) {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j]);
                }

                // 2. 온도 유지하기

                // 2 - 1.  만약, 실외 기온과 현재 j 기온이 같다면 에어컨을 꺼서 비용이 0
                if(j == temperature) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                }

                // 2 - 2. 실외기온과 j 기온이 다르다면 에어컨을 켜서 b만큼의 비용을 사용
                else {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + b);
                }

                // 3. 온도 낮추기
                // 3-1. 에어컨을 켜서 온도를 낮추기
                if(j - 1 >= 0) {
                    dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j] + a);
                }

                // 3-2. 에어컨을 끄고 유지비용 b 만큼 사용 (실외온도보다 실내온도가 높을때 가능)
                if(j > temperature) {
                    dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j]);
                }

            }

        }

        answer = Integer.MAX_VALUE;
        for(int i = 0; i <= 51; i++) {
            answer = Math.min(answer, dp[time][i]);
        }
        return answer;
    }

}
