package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;


public class 스티커 {
    static int t,n;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] dp;
    static int[][] sticker;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        for(int testcase = 0; testcase<t;testcase++)
        {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            dp = new int[2][n+1];
            sticker = new int[2][n+1];

            for(int i=0;i<2;i++)
            {
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<=n;j++)
                {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];

            for(int i=2;i<=n;i++)
            {
                int nValue1 = max(dp[0][i-2],dp[0][i-1]) + sticker[1][i];
                int nValue2 = max(dp[1][i-2],dp[1][i-1]) + sticker[0][i];

                dp[0][i] = nValue2;
                dp[1][i] = nValue1;
            }
            bw.write(String.valueOf(max(dp[0][n],dp[1][n])));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

}
