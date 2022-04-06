package baekJoon.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class 최솟값과최댓값 {

    static int N,M;
    static int[] arr,minTree,maxTree;

    public static int setMinTree(int node,int left, int right){
        if(left == right) return minTree[node] = arr[left];

        int mid = (left + right) / 2;
        int leftSide = setMinTree(node * 2 , left, mid);
        int rightSide = setMinTree(node * 2 + 1, mid + 1, right);

        return minTree[node] = min(leftSide, rightSide);
    }
    public static int setMaxTree(int node,int left, int right){
        if(left == right) return maxTree[node] = arr[left];

        int mid = (left + right) / 2;
        int leftSide = setMaxTree(node * 2 , left, mid);
        int rightSide = setMaxTree(node * 2 + 1, mid + 1, right);

        return maxTree[node] = max(leftSide, rightSide);
    }
    public static int getMinTree(int node, int start, int end, int left, int right){
        if(start > right || end < left ) return -1;
        if(start >= left && end <= right) return minTree[node];

        int mid = (start + end) / 2;
        int leftSide = getMinTree(node * 2 , start , mid ,left, right);
        int rightSide = getMinTree(node * 2 + 1 , mid + 1, end, left, right);
        if(leftSide == -1) return rightSide;
        else if(rightSide == -1 ) return leftSide;
        return min(leftSide, rightSide);
    }
    public static int getMaxTree(int node, int start, int end, int left, int right){
        if(start > right || end < left ) return -1;
        if(start >= left && end <= right) return maxTree[node];

        int mid = (start + end) / 2;
        int leftSide = getMaxTree(node * 2 , start , mid ,left, right);
        int rightSide = getMaxTree(node * 2 + 1 , mid + 1, end, left, right);
        if(leftSide == -1) return rightSide;
        else if(rightSide == -1 ) return leftSide;
        return max(leftSide, rightSide);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        int treeHeight = (int) ceil(log10(N) / log10(2));
        int treeSize = (1 << (treeHeight + 1));

        minTree = new int[treeSize];
        maxTree = new int[treeSize];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        setMinTree(1,1,N);
        setMaxTree(1,1,N);

        int a, b;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int minNumber = getMinTree(1,1,N,a,b);
            int maxNumber = getMaxTree(1,1,N,a,b);
            bw.write(String.valueOf(minNumber) + " " + String.valueOf(maxNumber));
            bw.newLine();
        }
        bw.flush();
        bw.close();


    }
}
