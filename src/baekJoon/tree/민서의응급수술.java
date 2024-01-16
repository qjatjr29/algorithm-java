package baekJoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 민서의응급수술 {

    private static int[] connect;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        Set<Integer> unionCountSet = new HashSet<>();
        int answer = 0;

        connect = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            connect[i] = i;
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(input.nextToken());
            int n2 = Integer.parseInt(input.nextToken());

            // 이미 연결되어 있는 경우
            if(isCycle(n1, n2)) {
                answer += 1;
                continue;
            }

            unionConnect(n1, n2);
        }

        for(int i = 1; i <= N; i++) {
            unionCountSet.add(findConnectInfo(i));
        }

        answer += unionCountSet.size() - 1;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static boolean isCycle(int n1, int n2) {
        n1 = findConnectInfo(n1);
        n2 = findConnectInfo(n2);
        return n1 == n2;
    }

    private static int findConnectInfo(int n) {
        if(connect[n] == n) return n;
        else return connect[n] = findConnectInfo(connect[n]);
    }

    private static void unionConnect(int n1, int n2) {
        n1 = findConnectInfo(n1);
        n2 = findConnectInfo(n2);
        if(n1 != n2) connect[n1] = n2;
    }
}
