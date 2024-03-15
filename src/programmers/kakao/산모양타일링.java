package programmers.kakao;

public class 산모양타일링 {

    private static final int INF = 10007;

    public static int solution(int n, int[] tops) {
        int answer = 0;

        // i 번째 모양 - 탑의 유무 - 끝나는 형태(정삼각형, 사다리꼴)
        int[][][] dp = new int[n + 1][2][2];

        // 탑이 있음
        if(tops[0] == 1) {
            dp[1][1][0] = 3;
            dp[1][1][1] = 1;
        }

        else {
            dp[1][0][0] = 2;
            dp[1][0][1] = 1;
        }

        for(int i = 2; i <= tops.length; i++) {
            if(tops[i - 1] == 1) {
                if(tops[i - 2] == 1) {
                    dp[i][1][0] = (dp[i - 1][1][0] * 3 + dp[i - 1][1][1] * 2) % INF;
                    dp[i][1][1] = (dp[i - 1][1][0] + dp[i - 1][1][1]) % INF;
                }
                else {
                    dp[i][1][0] = (dp[i - 1][0][0] * 3 + dp[i - 1][0][1] * 2) % INF;
                    dp[i][1][1] = (dp[i - 1][0][0] + dp[i - 1][0][1]) % INF;
                }
            }

            else {
                if(tops[i - 2] == 1) {
                    dp[i][0][0] = (dp[i - 1][1][0] * 2 + dp[i - 1][1][1]) % INF;
                    dp[i][0][1] = (dp[i - 1][1][0] + dp[i - 1][1][1]) % INF;
                }
                else {
                    dp[i][0][0] = (dp[i - 1][0][0] * 2 + dp[i - 1][0][1]) % INF;
                    dp[i][0][1] = (dp[i - 1][0][0] + dp[i - 1][0][1]) % INF;
                }
            }
        }

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                answer += dp[n][i][j];
                answer %= INF;
            }
        }

        return answer;
    }


    public static void main(String[] args) {
        int[] t = {1, 1, 0, 1};
        int ans = solution(4, t);
        System.out.println(ans);
    }
    // n = 6
    // i번째 사다리꼴에서
    // 해당 사다리꼴의 top이 있는지 없는지 여부 파악

    // 없다면? => 해당 사다리꼴에서 채울 수 있는 경우의 수
    // 사다리꼴 + 정삼각형 인 경우의 수       = 이전 모양이 사다리꼴이면 안된다.
    // 모두 정삼각형인 경우의 수             = 이전 모양이 정삼각형으로 끝나야 한다.
    // 정삼각형 + 사다리꼴 경우의수           = 이전 모양이 정삼각형으로 끝나야 한다.


    // top이 있을 경우
    // 정삼각형 + 사다리꼴 + 정삼각형
    // 사다리꼴 + 정삼각형 + 정삼각형
    // 정삼각형 + 정삼각형 + 사다리꼴
    // 정삼각형 * 4


    // 형태
    // 정 + x + 정
    // 사 + 정삼각
    // 정삼각 + 사
}
