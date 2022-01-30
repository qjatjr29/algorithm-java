package khuCodeTestCamp.dp.gridMinDp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main {
    static int N;
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int map[][] = new int[N+1][N+1];
        int dp[][] = new int[N+1][N+1];
        for(int i=1;i<=N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1]=map[1][1];
        int rowMin,colMin;
        rowMin=dp[1][1];
        colMin=dp[1][1];
        for(int i=2;i<=N;i++)
        {
            rowMin=min(rowMin,map[1][i]);
            dp[1][i]=rowMin;
        }
        for(int i=2;i<=N;i++)
        {
            colMin=min(colMin,map[i][1]);
            dp[i][1]=colMin;
        }
        for(int i=2;i<=N;i++) {
            for (int j = 2; j <= N; j++) {
                int x= min(map[i][j],dp[i-1][j]);
                int y= min(map[i][j],dp[i][j-1]);
                dp[i][j]=max(x,y);
            }
        }
        bw.write(String.valueOf(dp[N][N]));
        bw.newLine();
        bw.flush();
        bw.close();


    }
}
