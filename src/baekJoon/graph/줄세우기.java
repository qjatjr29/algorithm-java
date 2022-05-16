package baekJoon.graph;

import java.io.*;
import java.util.*;

public class 줄세우기 {

    private static int N, M;


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] dp = new int[N + 1];
        List<Integer>[] arr = new List[N + 1];

        for(int i = 1; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A].add(B);
            dp[B]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(dp[i] == 0) {
                q.add(i);
            }
        }

        while(true) {
            if(q.isEmpty()) break;
            int curNumber = q.poll();

            bw.write(String.valueOf(curNumber) + " ");
            for(int i = 0; i < arr[curNumber].size(); i++) {
                int nextNumber = arr[curNumber].get(i);
                dp[nextNumber]--;
                if(dp[nextNumber] == 0) q.add(nextNumber);
            }
        }


        bw.newLine();
        bw.flush();
        bw.close();
    }
}
