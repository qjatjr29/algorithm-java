package baekJoon.graph;

import java.io.*;
import java.util.StringTokenizer;

public class 가장가까운공통조상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for (int testcase = 0; testcase < T; testcase++) {
            input = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(input.nextToken());
            int[] root = new int[n + 1];

            for (int i = 0; i < n - 1; i++) {
                input = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(input.nextToken());
                int child = Integer.parseInt(input.nextToken());
                root[child] = parent;
            }

            input = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(input.nextToken());
            int v2 = Integer.parseInt(input.nextToken());

            int temp;
            while (true) {

                if (v1 == root[v1]) break; // 루트인경우

                temp = v2;

                while (true) {
                    if (temp == root[temp]) break; // 루트까지 탐색함 -> 못찾거나 v1이 루트임.

                    if (temp == v1) break; // 최소 부모 조상 찾은 경우
                    temp = root[temp];
                }

                // 최소 부모 조상 찾은 경우
                if (temp == v1) {
                    bw.write(String.valueOf(v1));
                    bw.newLine();
                    break;
                }
                v1 = root[v1];
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
