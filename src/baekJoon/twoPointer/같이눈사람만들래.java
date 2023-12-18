package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 같이눈사람만들래 {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        answer = Integer.MAX_VALUE;
        int N = Integer.parseInt(input.nextToken());

        int[] snows = new int[N];

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snows[i] = Integer.parseInt(input.nextToken());
        }

        Arrays.sort(snows);

        for(int i = 0; i < N; i++) {
            for(int j = N - 1; j >= i + 1; j--) {
                makeSnowman(i, j, snows);
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }


    private static void makeSnowman(int left, int right, int[] snows) {
        int start = left + 1;
        int end = right - 1;

        int height1 = snows[left] + snows[right];
        while(start < end) {
            int height2 = snows[start] + snows[end];

            int gap = Math.abs(height1 - height2);

            answer = Math.min(answer, gap);

            if(height1 > height2) {
                start++;
                continue;
            }
            end--;
        }
    }

}
