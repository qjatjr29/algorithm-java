package baekJoon.dijkstra;

import java.io.*;
import java.util.*;

public class 택배 {

    private static final int INF = 999999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int[][] routes = new int[n + 1][n + 1];
        int[][] first = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(routes[i], INF);
        }

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(input.nextToken());
            int n2 = Integer.parseInt(input.nextToken());
            int length = Integer.parseInt(input.nextToken());
            routes[n1][n2] = Math.min(routes[n1][n2], length);
            routes[n2][n1] = Math.min(routes[n2][n1], length);
            first[n1][n2] = n2;
            first[n2][n1] = n1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (j == k) continue;
                    if (routes[j][k] > routes[j][i] + routes[i][k]) {
                        routes[j][k] = routes[j][i] + routes[i][k];
                        first[j][k] = first[j][i];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) bw.write("-");
                else bw.write(String.valueOf(first[i][j]));
                bw.write(" ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
