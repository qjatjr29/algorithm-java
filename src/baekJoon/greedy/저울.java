package baekJoon.greedy;

import java.io.*;
import java.util.*;

public class 저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int answer = 0;
        int n = Integer.parseInt(input.nextToken());
        int[] weights = new int[n];

        input = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(input.nextToken());
        }

        Arrays.sort(weights);

        for (int weight : weights) {
            if (answer + 1 < weight) break;
            answer += weight;
        }

        bw.write(String.valueOf(answer + 1));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
