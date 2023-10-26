package baekJoon.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 수들의합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int targetNumber = Integer.parseInt(input.nextToken());
        long answer = 0;

        int[] numbers = new int[size + 1];
        int[] sum = new int[size + 1];
        Map<Integer, Integer> prefixMap = new HashMap<>();

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= size; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
            sum[i] = sum[i - 1] + numbers[i];

            if(sum[i] == targetNumber) answer++;
        }

        for(int i = 1; i <= size; i++) {
            // i 번째 까지의 누적합에서 구하고자하는 값을 뺀 값에 대한 경우가 있는 경우 그 횟수만큼 추가
            answer += prefixMap.getOrDefault(sum[i] - targetNumber, 0);
            prefixMap.put(sum[i], prefixMap.getOrDefault(sum[i], 0) + 1);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
