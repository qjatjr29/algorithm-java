package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 행렬곱셈순서 {

    private static int[][] dp;
    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        matrix = new int[501][2];
        dp = new int[501][501];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++) {
            for(int j = 1; i + j <= N; j++) {
                dp[j][i + j] = Integer.MAX_VALUE;
                for(int k = j; k < i + j; k++) {
                    dp[j][i + j] = min(
                            dp[j][i + j],
                            dp[j][k] + dp[k + 1][i + j] + matrix[j][0] * matrix[k][1] * matrix[i+j][1]);
                }
            }
        }
        bw.write(String.valueOf(dp[1][N]));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
