package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;
import static java.util.Arrays.sort;


public class 앱 {

    static int[] dp;
    static int N, M;
    static app[] apps;
    private static final int MAX = 10001;

    public static class app {
        int memory, cost;
        app(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int answer = 0;
        int maxCost = 0;
       dp = new int[MAX];
       apps = new app[N];

       for(int i = 0 ; i < N; i++) {
           apps[i] = new app(0, 0);
       }

       st = new StringTokenizer(br.readLine());
       for(int i = 0; i < N; i++) {
           apps[i].memory = Integer.parseInt(st.nextToken());
       }
       st = new StringTokenizer(br.readLine());
       for(int i = 0; i < N; i++) {
           apps[i].cost = Integer.parseInt(st.nextToken());
           maxCost += apps[i].cost;
       }

       for(int i = 0; i < N; i++) {
           int appMemory = apps[i].memory;
           int appCost = apps[i].cost;

           for(int j = maxCost; j >= 0; j--) {
               if(j < appCost ) break;
               dp[j] = max(dp[j], dp[j - appCost] + appMemory);
           }
       }

       for(int i = 1; i < MAX; i++) {
           if(dp[i] >= M) {
               answer = i;
               break;
           }
       }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
