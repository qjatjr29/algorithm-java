package baekJoon.dijkstra.floydWarshall;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 궁금한민호 {

    static int N,answer;
    static int[][] road, dp;
    public static void floyd(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                for(int k = 1; k <= N; k++){
                    if(i == k || i == j || j == k) continue;
                    // 최소 이동거리가 아님.
                    if(road[j][k] > road[j][i] + road[i][k])
                    {
                        answer = -1;
                        return;
                    }
                    if(road[j][k] == road[j][i] + road[i][k]) dp[j][k] = 0;
                }
            }
        }
    }
    public static void sol(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                answer += dp[i][j];
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        road = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                road[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = road[i][j];
            }
        }
        floyd();
        if(answer != -1) {
            sol();
            bw.write(String.valueOf(answer/2));
        }
        else bw.write(String.valueOf(-1));
        bw.newLine();
        bw.flush();
        bw.close();


    }
}
