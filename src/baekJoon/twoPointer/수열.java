package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int day = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] temperature = new int[day];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < day; i++) temperature[i] = Integer.parseInt(st.nextToken());

        int sum = 0;

        for(int i = 0; i < K; i++) sum += temperature[i];
        int answer = sum;

        int lp = 0;
        int rp = K;

        for(; rp < day; rp++) {
            sum += (temperature[rp] - temperature[lp]);
            answer = Math.max(answer, sum);
            lp++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
