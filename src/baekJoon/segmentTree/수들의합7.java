package baekJoon.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;
import static java.lang.Math.log10;

public class 수들의합7 {

    static int N,M;
    static long[]segmentTree;

    static long sum(int node, int fixLeft, int fixRight,int start, int end){
        if(start > fixRight || end <fixLeft) return 0;
        if(start >= fixLeft && end <= fixRight) return segmentTree[node];
        int mid = (start + end) / 2;
        long leftSide = sum(node * 2 ,fixLeft,fixRight,start,mid);
        long rightSide = sum(node * 2 + 1,fixLeft,fixRight,mid + 1 ,end);
        return leftSide + rightSide;
    }
    static long modify(int node, int start, int end, int index, int value){
        if(index < start || index > end) return segmentTree[node];
        if(start == end) return segmentTree[node] = value;
        int mid = (start + end) / 2;
        long leftSide = modify(node * 2, start , mid ,index, value);
        long rightSide = modify(node * 2 + 1, mid + 1, end, index, value);
        return segmentTree[node] = leftSide + rightSide;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int treeHeight = (int) ceil(log10(N) / log10(2));
        int size = (1 << (treeHeight + 1));
        segmentTree = new long[size];

        int cmd,a,b;
        for(int i = 0; i < M; i++){
            st= new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(cmd == 0){
                long result;
                if(a < b) result = sum(1,a,b,1,N);
                else result = sum(1,b,a,1,N);
                bw.write(String.valueOf(result));
                bw.newLine();
            }else{
                modify(1,1,N,a,b);
            }
        }
        bw.flush();
        bw.close();

    }
}
