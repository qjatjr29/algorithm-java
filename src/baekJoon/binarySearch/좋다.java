package baekJoon.binarySearch;

import java.io.*;
import java.util.*;

public class 좋다 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int[] number = new int[n];

        input = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(input.nextToken());
        }

        Arrays.sort(number);
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (checkGood(i, number, 0, n - 1)) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkGood(int index, int[] number, int left, int right) {

        int target = number[index];
        if (left >= right) return false;

        if (index == left) return checkGood(index, number, left + 1, right);
        if (index == right) return checkGood(index, number, left, right - 1);

        int sum = number[left] + number[right];

        if (target == sum) return true;
        if (target > sum) {
            return checkGood(index, number, left + 1, right);
        }

        return checkGood(index, number, left, right - 1);
    }
}
