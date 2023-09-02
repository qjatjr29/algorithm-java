package goorm;

import java.io.*;
import java.util.*;

public class 사은품교환하기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {

            input = new StringTokenizer(br.readLine());
            long N = Long.parseLong(input.nextToken());
            long M = Long.parseLong(input.nextToken());
            long result = Math.min((N + M) / 12, N / 5);

            bw.write(String.valueOf(result));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
