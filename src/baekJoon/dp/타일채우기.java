package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;


public class 타일채우기 {

    static int N;
    static int[] dp;
    private static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        dp = new int[31];
        dp[2]=3;
        for(int i = 4;i<=N;i+=2)
        {
            dp[i] += dp[i-2] * 3;
            dp[i]+=2;
            for(int j=4;j<=i;j+=2)
            {
                dp[i] += dp[i-j] * 2;
            }
        }
        bw.write(String.valueOf(dp[N]));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
