package baekJoon.unionFind;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class 네트워크연결 {
    private static int MOD = 1000;
    static int T,N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] root;
    static int[] rank;
    static int[] dp;

    public static void setUnion(){
        root = new int[N+1];
        rank = new int[N+1];
        dp = new int[N+1];
        for(int i=1;i<=N;i++){
            rank[i] = 0;
            root[i] = i;
        }
    }

    /**
     * root 노드를 찾아가면서 거리를 더해간다.
     */
    public static int find(int x){
        if(root[x] == x) return x;
        else {
            int nNode = find(root[x]);
            dp[x] += dp[root[x]];
            return root[x] = nNode;
        }

    }
    public static void union(int x, int y){
        dp[x] = abs(x-y) % MOD;
        root[x] = y ;
    }

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int testcase =0;testcase<T;testcase++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            setUnion();
            while(true){
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                int center,company;
                if(!command.equals("E") && !command.equals("I"))break;
                if(command.equals("E")){
                    company = Integer.parseInt(st.nextToken());
                    find(company);
                    bw.write(String.valueOf(dp[company]));
                    bw.newLine();
                }else{
                    center = Integer.parseInt(st.nextToken());
                    company = Integer.parseInt(st.nextToken());
                    union(center,company);
                }
            }
            bw.flush();
        }
        bw.close();
    }
}
