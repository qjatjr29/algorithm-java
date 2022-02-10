package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 피보나치함수_1003 {
    static int T,N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Pair[] dp;
    public static void fibonacci(){
        dp[0] = new Pair(1,0);
        dp[1] = new Pair(0,1);
        for(int i=2;i<=40;i++)
        {
            int nx = dp[i-1].x + dp[i-2].x;
            int ny = dp[i-1].y + dp[i-2].y;
            dp[i] = new Pair(nx,ny);
        }
    }
    public static class Pair{
        int x,y;
        Pair(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        dp = new Pair[41];
        fibonacci();
        for(int i=0;i<T;i++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(dp[N].x)+" "+String.valueOf(dp[N].y));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
