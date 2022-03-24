package baekJoon.unionFind;

import java.io.*;
import java.util.StringTokenizer;

public class 사이클게임 {

    static int n,m;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int answer;
    static int[] root;
    static int[] rank;

    public static void setting() {
        root = new int[n+1];
        rank = new int[n+1];
        for(int i=0;i<n;i++) {
            root[i] = i;
            rank[i] = 0;
        }
    }

    public static int find(int x) {
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }

    public static void union(int x,int y,int cnt) {
        x = find(x);
        y = find(y);

        if(x == y)
        {
            answer = cnt;
            return;
        }

        if(rank[x] < rank[y]) root[x] = y;
        else{
            root[y] = x;
            if(rank[x] == rank[y]) rank[x]++;
        }

    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        setting();
        int point1,point2;
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            point1 = Integer.parseInt(st.nextToken());
            point2 = Integer.parseInt(st.nextToken());
            union(point1,point2,i+1);
            if(answer != 0) break;
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
