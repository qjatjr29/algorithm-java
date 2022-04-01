package baekJoon.segmentTree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.ceil;
import static java.lang.Math.log10;

public class 구간곱구하기 {
    static final int MOD = 1000000007;
    static int N,M,K;
    static Long[] segmentTree;
    static Long[] numbers;
    public static Long setTree(int node,int left,int right){
        if(left==right) return segmentTree[node] = numbers[left];

        int mid = (left + right) / 2;
        Long leftNumber = setTree(node * 2, left, mid);
        Long rightNumber = setTree(node * 2 + 1,mid+1, right);

        return segmentTree[node] = (leftNumber * rightNumber) % MOD;

    }
    public static Long updateTree(int node, int start,int end, int index,Long value){
        if(index < start || index > end ) return segmentTree[node];
        if(start == end) return segmentTree[node] = value;
        int mid = (start + end) / 2;
        Long leftSide = updateTree(node * 2, start , mid ,index, value);
        Long rightSide = updateTree(node * 2 + 1, mid + 1,end, index,value);
        segmentTree[node] = leftSide * rightSide % MOD;
        return segmentTree[node];
    }
    public static Long multiply(int node, int start,int end,int left, int right){
        if(end < left || right < start) return 1L;
        if( start >= left && right >= end) return segmentTree[node];
        int mid = (start + end) / 2;
        Long leftSide = multiply(node * 2 ,start,mid,left,right);
        Long rightSide = multiply(node * 2 + 1,mid + 1,end,left,right);
        return (leftSide * rightSide) % MOD;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new Long[N+1];
        int treeHeight = (int) ceil(log10(N) / log10(2));
        int treeSize = (1 << (treeHeight + 1));
        segmentTree = new Long[treeSize];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            numbers[i] = Long.parseLong(st.nextToken());
        }
        setTree(1,1,N);
        int a,b,c;
        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(a==1){
                updateTree(1,1,N,b, (long) c);
            }else{
                bw.write(String.valueOf(multiply(1,1,N,b,c)));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();

    }
}
