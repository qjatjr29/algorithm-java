package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class nx2타일링2_11727 {
    static int n;
    static int NUM = 10007;
    static int[] dp;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void tiling()
    {
        dp[2] = 3;
        for(int i=3;i<=n;i++)
        {
            dp[i] = ( dp[i-1] + (dp[i-2] * 2) ) % NUM;
        }
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());

        dp = new int[n+1];
        dp[1] = 1;
        if(n>1)  tiling();

        bw.write(String.valueOf(dp[n]));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
