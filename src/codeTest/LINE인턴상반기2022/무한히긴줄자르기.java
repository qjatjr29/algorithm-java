package codeTest.LINE인턴상반기2022;

import static java.lang.Math.min;

public class 무한히긴줄자르기 {

    private static int[] dp;

    public static int solution(int n, int[] times) {
        int answer = 0;

        dp = new int[2001];

        for(int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[1] = 0;
        dp[2] = times[0];

        for(int i = 3; i <= n; i++) {
            for(int j = 1; j <= times.length; j++) {
                if(i - j < j) break;
                dp[i] = min(dp[i], dp[i - j] + times[j - 1]);
            }
        }

        answer = dp[n];

        return answer;
    }

    public static void main(String[] args) {

        int[] times = {2, 4, 5};
        System.out.println(solution(5, times));
    }
}
