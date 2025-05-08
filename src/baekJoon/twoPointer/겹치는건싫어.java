package baekJoon.twoPointer;

import java.io.*;
import java.util.StringTokenizer;

public class 겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());
        int[] number = new int[n + 1];
        int[] counts = new int[100001];

        input = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            number[i] = Integer.parseInt(input.nextToken());
        }

        int lp = 1;
        int rp = 1;

        int answer = 0;

        while(true) {

            if (rp > n) {
                answer = Math.max(answer, rp - lp);
                break;
            }
            int num = number[rp];

            if (counts[num] == k) {
                answer = Math.max(answer, rp - lp);
                while (true) {
                    int left = number[lp];
                    lp++;
                    counts[left]--;
                    if (left == num) {
                        break;
                    }
                }
            }
            counts[num]++;
            rp++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
