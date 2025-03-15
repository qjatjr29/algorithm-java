package baekJoon.greedy;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(input.nextToken());

        for (int testcase = 0; testcase < t; testcase++) {

            input = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(input.nextToken());

            Stack<Integer> stock = new Stack<>();

            input = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                stock.add(Integer.parseInt(input.nextToken()));
            }

            // 마지막날부터의 가장 큰 주가
            int price = stock.pop();
            long answer = 0;

            while (!stock.isEmpty()) {

                // 현재 날짜의 주가
                int p = stock.pop();

                // 지금껏 큰 주가 값보다 현재 값이 작다면
                // 판다.
                if (p <= price) {
                    answer += (price - p);
                    continue;
                }

                // 가장 큰 주가 값 갱신
                price = p;
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
