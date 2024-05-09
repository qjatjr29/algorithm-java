package baekJoon.knapsack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 수강과목 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());
        int answer = 0;
        Subject[] subjects = new Subject[K + 1];
        int[][] dp = new int[K + 1][N + 1];

        for(int i = 1; i <= K; i++) {
            input = new StringTokenizer(br.readLine());
            int importance = Integer.parseInt(input.nextToken());
            int time = Integer.parseInt(input.nextToken());

            subjects[i] = new Subject(importance, time);
        }

        for(int i = 1; i <= K; i++) {
            Subject subject = subjects[i];
            for(int j = 1; j <= N; j++) {
                // 최대 사용 시간이 j인 경우 해당 과목을 들을 수 있음.
                if(j - subject.time >= 0) {
                    // 해당 과목을 듣지 않는 경우와 듣는 경우의 최대값
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - subject.time] + subject.importance);
                    continue;
                }
                // 해당 과목을 들을 수 없는 경우
                dp[i][j] = dp[i - 1][j];
            }
        }

        answer = dp[K][N];

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Subject {
        int importance;
        int time;

        public Subject(int importance, int time) {
            this.importance = importance;
            this.time = time;
        }
    }
}
