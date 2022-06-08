package baekJoon.unionFind;

import java.io.*;
import java.util.StringTokenizer;

public class 피리부는사나이 {

    private static char[][] map;
    private static boolean[][] visited;
    private static int N, M;
    private static int[] rank;
    private static int[] root;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        setup();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'D') {
                    union(i * M + j, (i + 1) * M + j);
                } else if (map[i][j] == 'U') {
                    union(i * M + j, (i - 1) * M + j);
                } else if (map[i][j] == 'L') {
                    union(i * M + j, i * M + j - 1);
                } else if (map[i][j] == 'R') {
                    union(i * M + j, i * M + j + 1);
                }
            }
        }



        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(find(i * M + j) == i * M + j) answer++;
            }
        }


        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();

    }

    private static void setup() {
        root = new int[N * M];
        rank = new int[N * M];
        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                root[i * M + j] = i * M + j;
                rank[i * M + j] = 0;
            }
        }

    }

    private static int find (int x) {
        if(x == root[x]) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(rank[x] < rank[y]) root[x] = y;
        else {
            root[y] = x;
            if(rank[x] == rank[y]) rank[x]++;
        }
    }
}
