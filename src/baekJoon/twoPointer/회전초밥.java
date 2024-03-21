package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int d = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());

        int[] dishes = new int[N + k];
        int[] sushi = new int[d + 1];
        int answer = 0;
        int count = 0;

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            dishes[i] = Integer.parseInt(input.nextToken());
            if(i < k) {
                dishes[i + N] = dishes[i];
                if(sushi[dishes[i]] == 0) {
                    count++;
                }
                sushi[dishes[i]]++;
            }
        }

        answer = count;
        for(int i = k; i < N + k; i++) {

            sushi[dishes[i - k]]--;
            if(sushi[dishes[i - k]] == 0) {
                count--;
            }

            if(sushi[dishes[i]] == 0) {
                count++;
            }
            sushi[dishes[i]]++;
            int coupon = sushi[c] == 0 ? 1 : 0;

            answer = Math.max(answer, count + coupon);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
