package baekJoon.dp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int[] numbers = new int[n];

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        int answer = findLongestIncreasingSubsequence(numbers);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static int findLongestIncreasingSubsequence(int[] numbers) {
        List<Integer> list = new ArrayList<>();

        list.add(numbers[0]);

        for (int i = 1; i < numbers.length; i++) {

            if (list.get(list.size() - 1) < numbers[i]) {
                list.add(numbers[i]);
                continue;
            }
            int idx = binarySearch(list, numbers[i]);
            list.set(idx, numbers[i]);
        }

        return list.size();
    }

    private static int binarySearch(List<Integer> numbers, int target) {
        int start = 0;
        int end = numbers.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midVal = numbers.get(mid);
            if (midVal >= target) {
                end = mid - 1;
            }
            if (midVal < target) {
                start = mid + 1;
            }
        }
        return start;
    }
}
