package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 구간합구하기5 {
    static int N,M;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] map;
    static int[][] dp;

    public static void setDp(){

        //dp[1][1] = map[1][1];
        for(int i=1;i<=N;i++)
        {
            for(int j=1;j<=N;j++)
            {
                dp[i][j] += (dp[i-1][j] + dp[i][j-1] + map[i][j] - dp[i-1][j-1]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for(int i=1;i<=N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        setDp();
        int sx,sy,ex,ey;
        for(int i=0;i<M;i++)
        {
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            int answer = dp[ex][ey]-
                    (dp[ex][sy-1] + dp[sx-1][ey]) +
                    dp[sx-1][sy-1];
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

}
