package codeTree.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 알바로부자되기 {
    static int N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Pair[] dp;
    static Info[] informations;

    public static class Info{
        int s,e;
        Long cost;

        Info(int s,int e, Long cost){
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }
    public static class Pair{
        int idx;
        Long value;
        Pair(int idx,Long value){
            this.idx = idx;
            this.value = value;
        }
    }
    public static Long solution()
    {
        dp[1] = new Pair(informations[1].e,informations[1].cost);
        Long answer = informations[1].cost;
        for(int i=2;i<=N;i++)
        {
            int curStart = informations[i].s;
            int curEnd = informations[i].e;
            Long curCost = informations[i].cost;
            Long maxNum = curCost;
            for(int j=1;j<i;j++)
            {
                if(dp[j].idx< curStart)
                {
                    maxNum = max(maxNum,dp[j].value + curCost);
                }
            }
            dp[i] = new Pair(curEnd,maxNum);
            answer = max(answer,maxNum);
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        informations = new Info[N+1];
        dp = new Pair[N+1];
        for(int i=1;i<=N;i++)
        {
            st = new StringTokenizer(br.readLine());
            int start,end;
            Long weight;
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Long.parseLong(st.nextToken());
            informations[i] = new Info(start,end,weight);
        }
        Long result = solution();
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
