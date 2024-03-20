package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 가장긴바이토닉부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int[] numbers = new int[N];

        input = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            int count = LisArrayCount(i, numbers);
            count += LdsArrayCount(i, numbers);
            answer = Math.max(answer, count - 1);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int LdsArrayCount(int targetIndex, int[] numbers) {
        List<Integer> dp = new ArrayList<>();
        dp.add(numbers[targetIndex]);

        for(int i = targetIndex + 1; i < numbers.length; i++) {

            if(dp.get(dp.size() - 1) > numbers[i]) {
                dp.add(numbers[i]);
                continue;
            }

            int idx = lowerBoundLds(dp, numbers[i]);
            if(idx == 0) {
                continue;
            }
            dp.set(idx, numbers[i]);
        }
        return dp.size();
    }

    private static int LisArrayCount(int targetIndex, int[] numbers) {

        List<Integer> dp = new ArrayList<>();
        dp.add(numbers[0]);

        for(int i = 1; i <= targetIndex; i++) {

            if(numbers[i] > numbers[targetIndex]) {
                continue;
            }

            if(dp.get(dp.size() - 1) < numbers[i]) {
                dp.add(numbers[i]);
                continue;
            }

            int idx = lowerBoundLis(dp, numbers[i]);
            dp.set(idx, numbers[i]);
        }
        return dp.size();
    }

    private static int lowerBoundLis(List<Integer> dp, int targetNumber) {
        int left = 0;
        int right = dp.size();

        while(left < right) {

            int mid = (left + right) / 2;

            if(dp.get(mid) >= targetNumber) {
                right = mid;
                continue;
            }

            left = mid + 1;
        }
        return right;
    }

    private static int lowerBoundLds(List<Integer> dp, int targetNumber) {
        int left = 0;
        int right = dp.size();

        while(left < right) {

            int mid = (left + right) / 2;

            if(dp.get(mid) <= targetNumber) {
                right = mid;
                continue;
            }

            left = mid + 1;
        }
        return right;
    }
}
