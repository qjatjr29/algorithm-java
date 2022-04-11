package baekJoon.segmentTree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Integer.sum;
import static java.lang.Math.ceil;
import static java.lang.Math.log10;

public class 공장 {

    static int[] A;
    static Map<Integer,Integer> B;
    static long[] segment;

    public static long find(int node, int left, int right, int start,int end){
        if(left > end || right < start) return 0;
        if(left >= start && right <= end) return segment[node];
        int mid = (left + right) / 2;
        long leftSide = find(node * 2, left , mid ,start, end);
        long rightSide = find(node * 2 + 1, mid + 1, right, start, end);
        return leftSide + rightSide;
    }

    public static void update(int node, int left, int right, int index){
        if(right < index || index < left) return ;
        segment[node]++;
        if(left == right) return;
        int mid = (left + right) / 2;
        update(node * 2, left , mid ,index);
        update(node * 2 + 1, mid + 1, right, index);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int treeHeight = (int) ceil(log10(N) / log10(2));
        int treeSize = (1 << (treeHeight + 1));
        segment = new long[treeSize];

        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        B = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <=N; i++){
            B.put(Integer.parseInt(st.nextToken()),i);
        }

        long answer = 0;
        for(int i = 1; i <= N; i++){
            int value = A[i];
            int index = B.get(value);

            answer += find(1, 1, N, index + 1, N);

            update(1, 1, N , index);
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();

    }

}
