package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class IOIOI {

    private static final char START_STR = 'I';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int answer = 0;

        int N = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        char[] word = input.nextToken().toCharArray();

        int[] dp = new int[M];

        for(int i = 1; i < M - 1; i++) {
            if(word[i] == 'O' && word[i + 1] == 'I') {
                dp[i + 1] = dp[i - 1] + 1;
                if(dp[i + 1] >= N && word[i - 2 * N + 1] == START_STR) {
                    answer++;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
