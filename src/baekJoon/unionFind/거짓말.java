package baekJoon.unionFind;

import java.io.*;
import java.util.*;

public class 거짓말 {

    static int N, M;
    static int[] root;
    static int[] rank;

    private static void setup() {
        root = new int[N + 1];
        rank = new int[N + 1];

        for(int i = 0; i < N + 1; i++) {
            root[i] = i;
            rank[i] = 0;
        }
    }

    private static int findRoot(int index) {
        if(index == root[index]) return index;
        return root[index] = findRoot(root[index]);
    }

    private static void union(int x,  int y) {
        x = findRoot(x);
        y = findRoot(y);

        if(x == y) return;

        if(rank[x] < rank[y]) root[x] = y;
        else {
            root[y] = x;
            if(rank[x] == rank[y]) rank[x]++;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int knowTruth = Integer.parseInt(st.nextToken());

        List<Integer> truthNumber = new ArrayList<>();

        setup();

        for(int i = 0; i < knowTruth; i++) {
            int truth = Integer.parseInt(st.nextToken());
            truthNumber.add(truth);
        }

        int cnt = 0;

        List<Integer>[] party = new List[M];

        for(int i = 0; i < M; i++) {
            party[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());

            int preNumber = Integer.parseInt(st.nextToken());
            party[i].add(preNumber);
            int number;

            for(int j = 1; j < size; j++) {
                number = Integer.parseInt(st.nextToken());
                union(preNumber, number);
                party[i].add(number);
            }
        }

        for(int i = 0; i < M; i++) {
            boolean chk = false;
            for(int j = 0; j < party[i].size(); j++) {
                int cmp = party[i].get(j);
                cmp = findRoot(cmp);
                for(int z = 0; z < truthNumber.size(); z++) {
                    int tr = findRoot(truthNumber.get(z));
                    if(cmp == tr) {
                        chk = true;
                        break;
                    }
                }
            }
            if(!chk) cnt++;
        }


        bw.write(String.valueOf(cnt));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
