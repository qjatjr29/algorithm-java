package baekJoon.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 캠프준비 {

    private static int numberOfProblem;
    private static int minLevel;
    private static int maxLevel;
    private static int minGap;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numberOfProblem = Integer.parseInt(st.nextToken());
        minLevel = Integer.parseInt(st.nextToken());
        maxLevel = Integer.parseInt(st.nextToken());
        minGap = Integer.parseInt(st.nextToken());
        answer = 0;

        int[] problems = new int[numberOfProblem];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < numberOfProblem; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0, problems, Integer.MAX_VALUE, Integer.MIN_VALUE);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(int cnt, int index, int sum, int[] problems, int min, int max) {

        if(cnt >= 2 && sum <= maxLevel && sum >= minLevel && max - min >= minGap) {
            answer++;
        }

        for(int i = index; i < numberOfProblem; i++) {
            dfs(cnt + 1, i + 1, sum + problems[i], problems, Math.min(min, problems[i]), Math.max(max, problems[i]));
        }
    }

}
