package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int[] numbers = new int[n];

        // i 번째 수를 선택했을 때 최대값.
        int[] dp = new int[n];

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        dp[0] = numbers[0];
        int answer = numbers[0];

        for (int i = 1; i < n; i++) {
            // 현재 인덱스 dp값 갱신
            dp[i] = Math.max(numbers[i], dp[i - 1] + numbers[i]);

            // 연속된 수의 최댓값 갱신
            if (dp[i] > answer) {
                answer = dp[i];
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
