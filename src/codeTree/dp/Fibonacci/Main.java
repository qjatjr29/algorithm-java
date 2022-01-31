package codeTree.dp.Fibonacci;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int dp[] = new int[46];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=45;i++)
        {
            dp[i]=dp[i-1]+dp[i-2];
        }
        bw.write(String.valueOf(dp[N]));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
