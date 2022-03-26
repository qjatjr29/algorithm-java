package baekJoon.unionFind;

import java.io.*;
import java.util.StringTokenizer;

public class 공항 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int G,P,answer;
    static int[] planes;
    static int[] rank;
    static int[] root;

    public static void setUnion(){
        rank = new int[G+1];
        root = new int[G+1];
        planes = new int[P+1];
        for(int i = 0; i<=G;i++){
            rank[i] = 0;
            root[i] = i;
        }
    }
    public static int find(int x){
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        G = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        setUnion();
        for(int i=0;i<P;i++){
            st = new StringTokenizer(br.readLine());
            int plane = Integer.parseInt(st.nextToken());
            planes[i] = plane;
        }
        for(int i=0;i<P;i++){
            int parent = find(planes[i]);
            if(parent == 0 )break;
            answer++;
            root[parent] = parent -1;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
