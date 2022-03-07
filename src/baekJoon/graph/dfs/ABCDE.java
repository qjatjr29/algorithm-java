package baekJoon.graph.dfs;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class ABCDE {
    static int N;
    static int M;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer>[] adj;
    static boolean[] visited;
    static int answer;
    public static void sol(int curIndex,int cnt) throws IOException {

        if(cnt == 5)
        {
            bw.write(String.valueOf(1));
            bw.newLine();
            bw.flush();
            bw.close();
            exit(0);
        }
        for(int i=0;i<adj[curIndex].size();i++)
        {
            int next = adj[curIndex].get(i);
            if(visited[next]==true) continue;
            visited[next] = true;
            sol(next,cnt+1);
            visited[next] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new List[N+1];
        for(int i=0;i<N+1;i++)
        {
            adj[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];
        for(int i=0;i<M;i++)
        {
            st = new StringTokenizer(br.readLine());
            int A  = Integer.parseInt(st.nextToken());
            int B  = Integer.parseInt(st.nextToken());
            adj[A].add(B);
            adj[B].add(A);
        }
        for(int i=0;i<=N;i++)
        {
            visited = new boolean[N+1];
            visited[i] = true;
            sol(i,1);
        }
        bw.write(String.valueOf(0));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
