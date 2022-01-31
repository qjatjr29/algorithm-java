package codeTree.dp.findMaxSubSequence;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.util.Arrays.sort;

public class Main {

    static int N;
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());

        int dp[] = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++)
        {
            dp[i]=-1001;
        }

        for(int i=0;i<N;i++)
        {
            if(i==0) {
                dp[0]=Integer.parseInt(st.nextToken());
                continue;
            }
            int number=Integer.parseInt(st.nextToken());

            dp[i]=max(dp[i-1]+number,number);
        }
        sort(dp);
        bw.write(String.valueOf(dp[N-1]));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
