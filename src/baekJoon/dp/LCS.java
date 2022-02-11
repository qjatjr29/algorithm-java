package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LCS {
    static String first;
    static String second;
    static int[][] dp;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        first = st.nextToken();
        int firstSize = first.length();
        st = new StringTokenizer(br.readLine());
        second = st.nextToken();
        int secondSize = second.length();

        dp = new int[firstSize+1][secondSize+1];

        for(int i=1;i<=firstSize;i++)
        {
            for(int j=1;j<=secondSize;j++)
            {
                if(first.charAt(i-1) == second.charAt(j-1))
                {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else dp[i][j] = max(dp[i-1][j],dp[i][j-1]);
            }
        }
        bw.write(String.valueOf(dp[firstSize][secondSize]));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
