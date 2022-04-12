package baekJoon.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class 수열과쿼리17 {

    static long[] arr, segment;

    public static long setup(int node,int left, int right){
        if(left == right) return segment[node] = arr[left];
        int mid = (left + right) / 2;
        long leftSide = setup(node * 2, left, mid);
        long rightSide = setup(node * 2 + 1, mid + 1, right);
        return segment[node] = min(leftSide, rightSide);
    }
    public static long update(int node, int left, int right, int index, long value){
        if(index > right || index < left) return segment[node];
        if(left == right) return segment[node] = value;

        int mid = (left + right) / 2;
        long leftSide = update(node * 2, left, mid, index, value);
        long rightSide = update(node * 2 + 1, mid + 1, right, index, value);

        return segment[node] = min(leftSide, rightSide);
    }
    public static long findMin(int node, int left, int right, int start, int end){
        if(left > end || right < start) return -1;
        if(start <= left && right <= end) return segment[node];
        int mid = (left + right) / 2;
        long leftSide = findMin(node * 2, left, mid ,start, end);
        long rightSide = findMin(node * 2 + 1,mid + 1, right, start, end);
        if(leftSide == -1) return rightSide;
        if(rightSide == -1) return leftSide;
        return min(leftSide, rightSide);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        int treeHeight = (int) ceil(log10(N) / log10(2));
        int treeSize = (1 << (treeHeight + 1));

        segment = new long[treeSize];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        setup(1,1,N);
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if(cmd == 1){
                int idx = Integer.parseInt(st.nextToken());
                long value = Integer.parseInt(st.nextToken());
                update(1,1,N,idx,value);
            }else{
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                bw.write(String.valueOf(findMin(1,1,N,start,end)));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
