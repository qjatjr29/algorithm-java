package khuCodeTestCamp.dp.chooseMIntervals;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Main {
    static int N, M;
    static int[] arr, sum;

    // dp[n][m] : n개의 수를 m 개의 구간으로 나누었을 때 최대 값.
    static int[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        sum = new int[N + 1];
        dp = new int[N + 1][M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + arr[i]; // 누적합 구하기
        }

        for (int n = 0; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                dp[n][m] = Integer.MIN_VALUE / 2;
            }
        }

        dp[1][1] = arr[1];

        for (int n = 2; n <= N; n++) {
            for (int m = 1; m <= M; m++) {

                dp[n][m] = dp[n - 1][m]; // n번째 수가 구간에 포함안되는 경우

                int min = 0;
                if (m == 1)
                    min = -1;

                for (int k = n - 2; k >= min; k--) {

                    if (k < 0)
                        dp[n][m] = Math.max(dp[n][m], sum[n]);
                    else
                        //
                        dp[n][m] = Math.max(dp[n][m], dp[k][m - 1] + sum[n] - sum[k + 1]);
                }
            }
        }

        bw.write(dp[N][M] + "\n");
        bw.flush();

    }
}

//    static int N,M;
//    static int ans;
//    private static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
//    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    static int numbers[];
//    static int sum[];
//    static int dp[][];
//
//
//    public static void main(String[] args) throws IOException {
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//
//        numbers= new int[N+1];
//        sum=new int[N+1];
//        dp=new int[N+1][N+1];
//        st = new StringTokenizer(br.readLine());
//
//        for(int i=1;i<=N;i++)
//        {
//            numbers[i]=Integer.parseInt(st.nextToken());
//            sum[i]=sum[i-1]+numbers[i];
//        }
//        for(int i=0;i<=N;i++)
//        {
//            for(int j=1;j<=M;j++)
//            {
//                dp[i][j]=Integer.MIN_VALUE;
//            }
//        }
//        dp[1][1]=numbers[1];
//
//        for(int i=2;i<=N;i++)
//        {
//            for(int j=1;j<=M;j++)
//            {
//                dp[i][j]=dp[i-1][j]; // i번 째 수가 포함이 안되는 경우
//                int minValue=0;
//                if(j==1) minValue=-1;
//                for(int k=i-2;k>=minValue;k--)
//                {
//                    if(k<0) dp[i][j]=max(dp[i][j],sum[i]);
//                    else dp[i][j]=max(dp[i][j],dp[k][j-1]+sum[i]-sum[k+1]);
//                }
//            }
//        }
//        bw.write(String.valueOf(dp[N][M]));
//        bw.newLine();
//        bw.flush();
//        bw.close();
//
//
//    }