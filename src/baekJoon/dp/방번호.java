package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class 방번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        input = new StringTokenizer(br.readLine());
        int minCost = Integer.MAX_VALUE;
        int[] numbers = new int[n];
        int minNumber = 0;
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(input.nextToken());
            numbers[i] = c;
            if (minCost >= c) {
                minCost = c;
                minNumber = i;
            }
        }

        input = new StringTokenizer(br.readLine());
        int cost = Integer.parseInt(input.nextToken());

        int div = cost / minCost; // 최대 자릿수
        int useCost = div * minCost;
        int[] result = new int[div];
        int index = 0;

        for (int i = 0; i < div; i++) {
            result[i] = minNumber;
            for (int j = numbers.length - 1; j > minNumber; j--) {
                // 번호를 교체하는데 필요한 값.
                int requiredCost = numbers[j] - minCost;
                // 해당 자릿수의 번호를 교체할 수 있는 경우
                if (requiredCost + useCost <= cost) {
                    useCost += requiredCost;
                    result[i] = j;
                    break;
                }
            }
            if (result[index] == 0) {
                index++;
                useCost -= minCost;
            }
        }
        for (int i = index; i < result.length; i++) {
            bw.write(String.valueOf(result[i]));
        }
        if (index == result.length) bw.write("0");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
