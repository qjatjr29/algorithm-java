package baekJoon.dp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 플로이드 {

    private static final int MAX = 10000001;
    private static int n, m;
    private static int[][] dp;

    private static void floyd() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                for(int z = 1; z <= n; z++) {
                    if(j == z) continue;
                    dp[j][z] = min(dp[j][z], dp[j][i] + dp[i][z]);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(dp[i][j] == MAX) dp[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][n + 1];

        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                if(i == j) dp[i][j] = 0;
                else dp[i][j] = MAX;
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dp[start][end] = min(dp[start][end], cost);
        }
        floyd();

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp.length; j++) {
                bw.write(String.valueOf(dp[i][j]) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();


    }
}
