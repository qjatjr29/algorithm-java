package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 도둑 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int testcase = Integer.parseInt(input.nextToken());

        while (testcase-- > 0) {
            input = new StringTokenizer(br.readLine());
            int houses = Integer.parseInt(input.nextToken());
            int M = Integer.parseInt(input.nextToken());
            int minMoneyAmount = Integer.parseInt(input.nextToken());

            int[] money = new int[houses + M];
            input = new StringTokenizer(br.readLine());

            int stolenMoney = 0;
            int count = 0;
            for (int i = 0; i < houses; i++) {
                money[i] = Integer.parseInt(input.nextToken());
                if (i < M) {
                    money[i + houses] = money[i];
                    stolenMoney += money[i];
                }
            }

            if (stolenMoney < minMoneyAmount) {
                count++;
            }

            if(houses == M) {
                bw.write(String.valueOf(count));
                bw.newLine();
                continue;
            }

            for (int i = M; i < houses + M - 1; i++) {

                stolenMoney -= money[i - M];
                stolenMoney += money[i];
                if (stolenMoney < minMoneyAmount) {
                    count++;
                }
            }

            bw.write(String.valueOf(count));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
