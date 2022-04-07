package baekJoon.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class 최솟값 {
    static int N,M;
    static long[] arr,segmentTree;
    public static long setTree(int node,int start,int end){
        if(start == end) return segmentTree[node] = arr[start];
        int mid = (start + end) / 2;
        long leftSide = setTree(node * 2,start , mid);
        long rightSide = setTree(node * 2 + 1,mid + 1,end);
        return segmentTree[node] = min(leftSide,rightSide);
    }
    public static long getMin(int node,int start,int end,int left,int right){
        if(end < left || start > right) return -1;
        if(start >= left && end <= right) return segmentTree[node];
        int mid = (start + end) / 2;
        long leftSide = getMin(node * 2,start,mid,left,right);
        long rightSide = getMin(node * 2 + 1,mid + 1,end,left,right);
        if(leftSide == -1) return rightSide;
        else if(rightSide == -1) return leftSide;
        return min(leftSide, rightSide);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        int treeHeight = (int) ceil(log10(N) / log10(2));
        int treeSize = (1 << (treeHeight + 1));
        segmentTree = new long[treeSize];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }
        setTree(1,1,N);
        int a, b;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(getMin(1,1,N,a,b)));
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }
}
