package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 자두나무 {
    static int T,W;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][][] dp;
    static int[] fruits;


    public static void setDp(){
        //  T초
        for(int i=1;i<=T;i++)
        {
            // W 만큼만 이동할 수 있다.
            for(int j=1;j<=W+1;j++)
            {
                // 자두가 1에서 떨어질때.
                if(fruits[i] == 1)
                {
                    // 1에 있는 경우는 원래 1에 있던 경우와 2에서 한번 이동한 경우 중 최대값
                    dp[i][j][1] = max(dp[i-1][j][1],dp[i-1][j-1][2]) + 1;
                    // 2에 있는경우는 자두 못받음.
                    dp[i][j][2] = max(dp[i-1][j][2],dp[i-1][j-1][1]);
                }
                // 2에서 떨어질때
                else
                {
                    if(i==1 && j==1) continue;
                    dp[i][j][1] = max(dp[i-1][j][1],dp[i-1][j-1][2]);
                    dp[i][j][2] = max(dp[i-1][j][2],dp[i-1][j-1][1]) + 1;
                }
                //System.out.println(dp[i][j][1] + " "+dp[i][j][2]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        fruits = new int[T+1];
        dp = new int[T+1][W+2][3];
        for(int i=1;i<=T;i++)
        {
            st = new StringTokenizer(br.readLine());
            fruits[i] = Integer.parseInt(st.nextToken());
        }
        setDp();
        int answer = 0;
        for(int i=1;i<=W+1;i++)
        {
            answer = max(answer, max(dp[T][i][1],dp[T][i][2]));
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();




    }
}
