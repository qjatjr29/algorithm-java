package baekJoon.graph.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 중앙트리 {
    static int n;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Long[] dp;
    // index의 하위 자식노드 개수.
    static Long[] children;
    // index 의 하위 노드들의 거리 합.
    static Long[] distance;
    // 트리 정보
    static ArrayList<Pair>[] tree;

    public static class Pair{
        int idx,cost;
        Pair(int idx,int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static Long dfs(int index,int parent){
        distance[index] = 0l;
        children[index] = 1l;
        for(Pair p : tree[index]){
            // 자식 인 경우.
            if(p.idx != parent){
                distance[index] += dfs(p.idx,index);
                children[index] += children[p.idx];
                distance[index] +=  children[p.idx] * p.cost;
            }
        }
        return distance[index];
    }
    public static void setDP(int index,int parent){
        for(Pair p : tree[index]){
            // 자식 인 경우.
            if(p.idx != parent){
                distance[p.idx] = distance[index] + (n - 2*children[p.idx]) * p.cost;
                setDP(p.idx,index);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        while(true)
        {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());

            //  종료 조건.
            if(n == 0) break;

            dp = new Long[n+1];
            children = new Long[n+1];
            distance = new Long[n+1];
            tree = new ArrayList[n+1];
            for(int i=0;i<n+1;i++)
            {
                tree[i] = new ArrayList<>();
            }
            for(int i=0;i<n-1;i++)
            {
                st = new StringTokenizer(br.readLine());
                int s,e,cost;
                s = Integer.parseInt(st.nextToken())+1;
                e = Integer.parseInt(st.nextToken())+1;
                cost = Integer.parseInt(st.nextToken());
                tree[s].add(new Pair(e,cost));
                tree[e].add(new Pair(s,cost));
            }

            dfs(1,1);
            setDP(1,1);
            Long answer = Long.MAX_VALUE;
            for(int i=1;i<=n;i++)
            {
                answer = min(answer,distance[i]);
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

}
