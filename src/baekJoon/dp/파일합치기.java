package baekJoon.dp;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 파일합치기 {
    static int T;
    static int K;
    static int[][] dp;
    static int[] numbers;
    static int[] sumNum;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int answer;

    public static int sumNumbers(int number1,int number2)
    {
        if(number1 == 0 )return sumNum[number2];
        else return sumNum[number2] - sumNum[number1-1];
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int i=0;i<T;i++)
        {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            dp = new int[K+1][K+1];
            numbers = new int[K+1];
            sumNum = new int[K+1];

            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=K;j++)
            {
                int number = Integer.parseInt(st.nextToken());
                numbers[j] = number;
                sumNum[j] = sumNum[j-1]+number;
            }

            for(int j=0;j<K;j++)
            {
                // j 에서 j+1 로 가는 비용
                dp[j][j+1] = numbers[j] + numbers[j+1];
            }

            for(int j=2;j<=K;j++)
            {
                for(int z=1;z+j<=K;z++)
                {
                    for(int n=z;n<z+j;n++)
                    {
                        if(dp[z][j+z]==0)
                        {
                            dp[z][z+j] = dp[z][n] + dp[n+1][z+j] + sumNumbers(z,z+j);
                        }
                        else
                            dp[z][z+j] = min(dp[z][z+j],
                                    dp[z][n]+dp[n+1][z+j] + sumNumbers(z,z+j));
                    }
                }
            }
            bw.write(String.valueOf(dp[1][K]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

}
