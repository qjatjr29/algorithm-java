package baekJoon.unionFind;

import java.io.*;
import java.util.StringTokenizer;

public class 여행가자 {

    static int N, M;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] root;
    static int[] rank;
    static int[] cities;

    public static void setUnionFind() {
        root = new int[N + 1];
        rank = new int[N+1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
            rank[i] = 0;
        }
    }

    public static int Find(int x) {
        if (root[x] == x) return x;
        else return root[x] = Find(root[x]);
    }

    public static void Union(int x, int y) {
        int rtnX = Find(x);
        int rtnY = Find(y);

        if(rtnX == rtnY) return;

        // 트리의 높이 비교
        if (rank[rtnX] < rank[rtnY]) root[x] = y;
        else {
            root[y] = x;
            if (rank[rtnX] == rank[rtnY]) rank[rtnX]++;
        }
    }

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        cities = new int[M];
        setUnionFind();

        int connection;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                connection = Integer.parseInt(st.nextToken());
                if (connection == 1) {
                    Union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        boolean chk = true;
        for (int i = 0; i < M; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }
        int unionNumber = Find(cities[0]);

        for (int i = 1; i < M; i++) {
            if (unionNumber != Find(cities[i])) {
                chk = false;
                break;
            }
        }

        if (chk == true)
            bw.write("YES");
        else bw.write("NO");

        bw.newLine();
        bw.flush();
        bw.close();


    }
}
