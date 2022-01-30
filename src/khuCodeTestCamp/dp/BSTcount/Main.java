package khuCodeTestCamp.dp.BSTcount;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int dp[] = new int[20];
        dp[0]=1;
        dp[1]=1;
        //0 1 2 5
        for(int i=2;i<=19;i++)
        {
            for(int j=0;j<i;j++)
            {
                dp[i] += (dp[j] * dp[i-j-1]);
            }
        }
        bw.write(String.valueOf(dp[N]));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
