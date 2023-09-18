package baekJoon.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 나머지합 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        long answer = 0;

        long[] sum = new long[N + 1];
        long[] remainder = new long[M];

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int value = Integer.parseInt(input.nextToken());
            sum[i] = (sum[i - 1] + value) % M;

            // i번째까지의 누적합의 나머지가 0이면 조건 충족
            if(sum[i] == 0) answer++;
            remainder[(int) sum[i]]++;
        }

        for(int i = 0; i < M; i++) {
            if(remainder[i] > 1) {
                // 같은 나머지를 가지고 있는 누적합의 개수를 통해 결과를 찾는다.
                answer += (remainder[i] * (remainder[i] - 1) / 2);
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
