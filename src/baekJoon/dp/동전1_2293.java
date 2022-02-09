package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.util.Arrays.sort;

public class 동전1_2293 {
    static int n,k;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] numbers;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());

        dp = new int[k+1];
        numbers = new int[n];
        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            numbers[i] = num;
        }
        dp[0]=1;
        for(int i=0;i<n;i++)
        {
            // 동전의 가치가 numbers[i] 인 경우.
            for(int j=numbers[i];j<=k;j++)
            {
                // 동전의 가치가 j 인 경우의 수는
                dp[j] += dp[j-numbers[i]];
            }
        }
        bw.write(String.valueOf(dp[k]));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
