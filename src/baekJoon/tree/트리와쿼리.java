package baekJoon.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리와쿼리 {

    static int N,R,Q;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static ArrayList<Integer>[] tree;
    static int[] children;
    static int[] dp;
    public static void setChildren(int index,int parent)
    {
        children[index] = 1;
        for(int i=0;i<tree[index].size();i++)
        {
            int nx = tree[index].get(i);
            if(nx == parent) continue;
            setChildren(nx,index);
            children[index] += children[nx];
        }
    }
//    public static int setDp(int index,int parent){
//        dp[index] = 1;
//        for(int i=0;i<tree[index].size();i++)
//        {
//            int nidx = tree[index].get(i);
//            if(nidx == parent) continue;
//            dp[index] += setDp(nidx,index);
//        }
//        return dp[index];
//    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N+1];
        for(int i=0;i<N+1;i++)
        {
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++)
        {
            st = new StringTokenizer(br.readLine());
            int s,e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }
        children = new int[N+1];
        setChildren(R,-1);
        for(int i=0;i<Q;i++)
        {
            st = new StringTokenizer(br.readLine());
           // dp = new int[N+1];
            int q = Integer.parseInt(st.nextToken());
            //setDp(q,-1);
            bw.write(String.valueOf(children[q]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

}
