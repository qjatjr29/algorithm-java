package baekJoon.LIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// LDS
public class 가장긴감소하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int[] numbers = new int[N];
        input = new StringTokenizer(br.readLine());

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        int answer = lds(numbers);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lds(int[] numbers) {
        List<Integer> result = new ArrayList<>();

        result.add(numbers[0]);

        for(int i = 1; i < numbers.length; i++) {
            int number = numbers[i];

            // 수열중 가장 작은 수보다 지금 수가 더 작은 경우
            if(result.get(result.size() - 1) > number) {
                result.add(number);
                continue;
            }

            // 해당 수보다 작은 수들중에 가장 큰 수와 수를 바꿔준다.
            int index = lowerBound(result, number);
            result.set(index, number);
        }

        return result.size();
    }

    // target 넘버보다 작은수의 index를 찾음(작은 수중 가장 커야함)
    private static int lowerBound(List<Integer> dp, int target) {
        int left = 0;
        int right = dp.size();

        while(left < right) {
            int mid = (left + right) / 2;
            if(dp.get(mid) <= target) {
                right = mid;
                continue;
            }
            left = mid + 1;
        }
        return right;
    }

}
