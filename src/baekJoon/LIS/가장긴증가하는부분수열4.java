package baekJoon.LIS;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        int[] numbers = new int[n + 1];
        int[] dp = new int[n + 1];

        input = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }
        dp[1] = 1;
        int length = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    length = Math.max(length, dp[i]);
                }
            }
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = n; i >= 1; i--) {
            if (length == dp[i]) {
                stack.push(numbers[i]);
                length--;
            }
        }

        bw.write(String.valueOf(stack.size()));
        bw.newLine();

        while(!stack.isEmpty()) {
            int number = stack.pop();
            bw.write(String.valueOf(number) + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
