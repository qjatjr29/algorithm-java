package baekJoon.twoPointer;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 부분합 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = arr[0];
        int answer = Integer.MAX_VALUE;

        while(true) {
            if(start >= N) break;

            if(sum >= S) {
                int len = end - start + 1;
                answer = min(answer, len);
                sum -= arr[start];
                start++;
                continue;
            }
            end++;
            if(end >= N) break;
            sum += arr[end];
        }
        if(answer == Integer.MAX_VALUE) answer = 0;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
