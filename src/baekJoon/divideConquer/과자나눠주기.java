package baekJoon.divideConquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 과자나눠주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());
        int[] snacks = new int[N + 1];

        int answer = 0;
        input = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            snacks[i] = Integer.parseInt(input.nextToken());
        }

        Arrays.sort(snacks);

        int left = 1;
        int right = snacks[N];

        while(left <= right) {

            int mid = (left + right) / 2;

            int snackCount = 0;
            for(int i = 1; i <= N;  i++) {
                snackCount += snacks[i] / mid;
            }

            if(snackCount >= M) {
                left = mid + 1;
                answer = mid;
            }
            else {
                right = mid - 1;
            }
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
