package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class 공통부분문자열 {
    static String s1,s2;

    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] dp;
    static int answer;
    public static void sol()
    {
        for(int i = 0; i<=s1.length();i++)
        {
            for(int j=0;j<=s2.length();j++)
            {
                if(i==0 || j == 0 ) continue;
                // 문자열 a의 i번째 문자
                // 문자열 b의 j번째 문자
                // 같다면 i-1번째 문자와 j-1번째 문자의 같음 여부를 통해
                // 같다면 계속 이어 질것이고
                // 아니면 이번 i,j번째들 문자 부터 시작. -> 1
                else if(s1.charAt(i-1) == s2.charAt(j-1))
                {
                    dp[i][j] = dp[i-1][j-1] +1;
                }
                else dp[i][j] = 0;
                answer = max(answer,dp[i][j]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        s1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        s2 = st.nextToken();

        dp = new int[4001][4001];
        sol();
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
