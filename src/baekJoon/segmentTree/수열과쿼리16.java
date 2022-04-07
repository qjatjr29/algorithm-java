package baekJoon.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class 수열과쿼리16 {

    static int N, M;
    static long[] arr;
    static long[] segment;
    public static long index(long x, long y){
        if(x == -1) return y;
        if(y == -1) return x;
        if(arr[(int) x] == arr[(int) y]) return min(x, y);
        return arr[(int) x] < arr[(int) y] ? x : y;
    }
    public static long setTree(int node, int start,int end){
        if(start == end) return segment[node] = start;
        int mid = (start + end) / 2;
        long leftSide = setTree(node * 2,start, mid);
        long rightSide = setTree(node * 2 + 1,mid + 1,end);

        return segment[node] = index(leftSide,rightSide);
    }
    public static long update(int node, int left, int right, int index){
        if(index < left || index > right) return segment[node];

        if(left == right) return segment[node];

        int mid = (left + right) / 2;
        long leftSide = update(node * 2, left, mid, index);
        long rightSide = update(node * 2 + 1,mid + 1,right, index);
        return segment[node] = index(leftSide, rightSide);
    }
    public static long findMin(int node, int left, int right, long start, long end){
        if(right < start || left > end) return -1;
        if(right <= end && left >= start) return segment[node];
        int mid = (left + right) / 2;
        long leftSide = findMin(node * 2, left, mid,start, end);
        long rightSide = findMin(node * 2 + 1,mid + 1,right,start,end);
        return index(leftSide, rightSide);
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        int treeHeight = (int) ceil(log10(N) / log10(2));
        int treeSize = (1 << (treeHeight + 1));
        segment = new long[treeSize];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        setTree(1,1,N);
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        int cmd;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            if(cmd == 1){
                int idx = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                arr[idx] = val;
                update(1,1,N,idx);
            }
            else{
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                bw.write(String.valueOf(findMin(1,1,N,a,b)));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();

    }
}
