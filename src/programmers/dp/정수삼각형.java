package programmers.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 정수삼각형 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] dp;
    public int solution(int [][] triangle){
        int answer = 0;
        int size = triangle.length;
        dp = new int[size+1][size+1];
        for(int i=1;i<=size;i++)
        {
            for(int j=1;j<=i;j++)
            {
                dp[i][j] = max(dp[i-1][j-1],dp[i-1][j]);
                dp[i][j] += triangle[i-1][j-1];
            }
        }
        for(int i=1;i<=size;i++)
        {
            answer = max(answer,dp[size][i]);
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {

    }

}
