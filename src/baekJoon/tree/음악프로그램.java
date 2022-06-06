package baekJoon.tree;

import java.io.*;
import java.util.*;

public class 음악프로그램 {

    private static int[] check;
    private static List<Integer>[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        numbers = new List[N + 1];
        check = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();
        for(int i = 0; i <= N; i++) {
            numbers[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j < cnt; j++) {
                temp.add(Integer.parseInt(st.nextToken()));
            }

            for(int j = 0; j < cnt - 1; j++) {
                int here = temp.get(j);
                int next = temp.get(j + 1);
                numbers[here].add(next);
                check[next]++;
            }
        }

        for(int i = 1; i <= N; i++) {
            if(check[i] == 0) q.add(i);
        }

        while(true) {
            if(q.isEmpty()) break;

            Integer top = q.poll();
            int size = numbers[top].size();

            result.add(top);

            for(int i = 0; i < size; i++) {
                int next = numbers[top].get(i);
                check[next]--;
                if(check[next] == 0) q.add(next);
            }
        }

        if(result.size() == N) {
            while(true) {
                if(result.isEmpty()) break;
                Integer top = result.poll();
                bw.write(top + "\n");
            }
        } else bw.write("0");
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
