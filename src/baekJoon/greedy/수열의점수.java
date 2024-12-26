package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 수열의점수 {

    private static boolean zero;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(input.nextToken());

            if (number > 0) {
                positive.add(number);
            }
            if (number < 0) {
                negative.add(number);
            }
            if (number == 0) {
                zero = true;
            }
        }

        Collections.sort(positive);
        Collections.sort(negative);

        long answer = 0;

        if (negative.size() % 2 == 0) {
            answer += solveEvenSize(negative);
        }
        else {
            answer += solveOddSize(negative);
        }
        if (positive.size() % 2 == 0) {
            answer += solveEvenSize(positive);
        }
        else {
            answer += solveOddSize(positive);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static long solveEvenSize(List<Integer> list) {
        long result = 0;
        if (list.isEmpty()) return result;
        if (list.get(0) > 0) {
            for(int i = 0; i < list.size(); i += 2) {
                int num1 = list.get(i);
                int num2 = list.get(i + 1);

                if(num1 == 1 || num2 == 1) {
                    result += (num1 + num2);
                    continue;
                }
                result += (long) num1 * num2;
            }
        }

        if (list.get(0) < 0) {
            for(int i = 0; i < list.size(); i += 2) {
                result += (long) list.get(i) * list.get(i + 1);
            }
        }

        return result;
    }

    private static long solveOddSize(List<Integer> list) {
        long result = 0;
        if (list.get(0) > 0) {
            result += list.get(0);
            for(int i = 1; i < list.size(); i += 2) {
                int num1 = list.get(i);
                int num2 = list.get(i + 1);

                if(num1 == 1 || num2 == 1) {
                    result += (num1 + num2);
                    continue;
                }
                result += (long) num1 * num2;
            }
        }

        if (list.get(0) < 0) {
            result += list.get(list.size() - 1);
            if (zero) {
                result = 0;
            }
            for(int i = list.size() - 2; i >= 0; i -= 2) {
                result += (long) list.get(i) * list.get(i - 1);
            }
        }

        return result;
    }

}
