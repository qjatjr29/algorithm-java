package baekJoon.unionFind;

import java.io.*;
import java.util.StringTokenizer;

public class 여행가자 {

    private static int[] connection;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(input.nextToken());

        connection = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            connection[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int conn = Integer.parseInt(input.nextToken());
                if (conn == 1) {
                    union(i, j);
                }
            }
        }

        input = new StringTokenizer(br.readLine());
        boolean check = true;
        int root = -1;
        for (int i = 0; i < m; i++) {
            int city = Integer.parseInt(input.nextToken());

            if (root == -1) {
                root = find(city);
                continue;
            }
            if (root != find(city)) {
                check = false;
                break;
            }
        }

        if (check) bw.write("YES");
        else bw.write("NO");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (x == connection[x]) return x;
        return connection[x] = find(connection[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) connection[y] = x;
    }
}
