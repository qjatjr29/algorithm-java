package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int testcase = Integer.parseInt(input.nextToken());

        while(--testcase >= 0) {

            input = new StringTokenizer(br.readLine());
            int days = Integer.parseInt(input.nextToken());
            input = new StringTokenizer(br.readLine());

            long answer = 0;
            ArrayDeque<Integer> stocks = new ArrayDeque<>();

            for(int i = 0; i < days; i++) {
                stocks.push(Integer.parseInt(input.nextToken()));
            }

            int cStockPrice = stocks.pop();

            while(!stocks.isEmpty()) {

                int price = stocks.pop();

                if(cStockPrice > price) {
                    answer += (cStockPrice - price);
                    continue;
                }

                cStockPrice = price;
            }

            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
