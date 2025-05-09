package baekJoon.twoPointer;

import java.io.*;
import java.util.*;

public class 대표선수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int[][] classes = new int[n][m];
        int[] points = new int[n];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                classes[i][j] = Integer.parseInt(input.nextToken());
            }
            Arrays.sort(classes[i]);
        }


        while (true) {

            int max = 0;
            int min = Integer.MAX_VALUE;
            int minIdx = -1;

            for (int i = 0; i < n; i++) {

                int point = points[i]; // 현재 i번째 학급의 학생 순서
                int value = classes[i][point];

                max = Math.max(max, value);
                if (min > value) {
                    min = value;
                    minIdx = i;
                }
            }

            answer = Math.min(answer, max - min);

            points[minIdx]++;
            if (points[minIdx] == m) break;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
