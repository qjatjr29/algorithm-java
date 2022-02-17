package baekJoon.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 우수마을 {

    static int N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] dp;
    static int[] residents;
    static ArrayList<Integer>[] cities;
    public static void dfs(int index,int parent)
    {
        // [index][0] ->  index 가 우수마을 아닐때 서브노드들 중 우수마을합.
        dp[index][0] = 0;
        // [index][1] ->  index 가 우수마을 일때 서브노드들 중 우수마을합.
        dp[index][1] = residents[index];
        for(int i=0;i<cities[index].size();i++)
        {
            int nIdx = cities[index].get(i);
            if(nIdx == parent) continue;

            dfs(nIdx,index);
            // 현재 노드가 우수마을이 아니면
            // 자식 노드들은 우수마을 일 수도, 아닐 수도 있다.
            // 그중 큰 값을 사용.
            dp[index][0] = dp[index][0] + max(dp[nIdx][1],dp[nIdx][0]);
            // 현재 노드가 우수마을이라면
            // 자식 노드들은 항상 우수마을이 아니다.
            dp[index][1] = dp[index][1] + dp[nIdx][0];
        }


    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        residents = new int[N+1];
        dp = new int[N+1][2];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++)
        {
            residents[i] = Integer.parseInt(st.nextToken());
        }
        cities = new ArrayList[N+1];

        for(int i=0;i<N+1;i++)
        {
            cities[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++)
        {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            cities[node1].add(node2);
            cities[node2].add(node1);
        }
        dfs(1,-1);

        int answer = max(dp[1][0],dp[1][1]);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
