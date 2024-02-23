package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 물대기 {

    private static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int answer = 0;

        root = new int[N + 1];
        int[] lands = new int[N + 1];
        PriorityQueue<Connect> connectList = new PriorityQueue<>();

        for(int i = 1; i <= N; i++) {
            input = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(input.nextToken());
            lands[i] = cost;
            root[i] = i;
        }

        for(int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 1; j <= N; j++) {
                if(i == j) {
                    connectList.add(new Connect(0, i, lands[i]));
                    continue;
                }
                int connectCost = Integer.parseInt(line[j - 1]);
                connectList.add(new Connect(i, j, connectCost));
            }
        }


        while(!connectList.isEmpty()) {

           Connect connect = connectList.poll();
           int land1 = connect.land1;
           int land2 = connect.land2;

           if(isCylce(land1, land2)) {
               continue;
           }

           union(land1, land2);

           answer += connect.cost;

        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findRoot(int x) {
        if(root[x] == x) return x;
        else return root[x] = findRoot(root[x]);
    }

    private static void union(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);
        if(x != y) root[x] = y;
    }

    private static boolean isCylce(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);
        return x == y;
    }

    private static class Connect implements Comparable<Connect> {
        int land1, land2;
        int cost;

        public Connect(int land1, int land2, int cost) {
            this.land1 = land1;
            this.land2 = land2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connect o) {
            return this.cost - o.cost;
        }
    }
}
