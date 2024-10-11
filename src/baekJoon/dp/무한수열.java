package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 무한수열 {

    private static Map<Long, Long> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        long N = Long.parseLong(input.nextToken());
        int P = Integer.parseInt(input.nextToken());
        int Q = Integer.parseInt(input.nextToken());

        dp = new HashMap<>();

        bw.write(String.valueOf(getAnswer(N, P, Q)));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static Long getAnswer(long number, int p, int q) {

        if(number == 0) return 1L;

        if(dp.containsKey(number)) return dp.get(number);

        long a = (long) Math.floor(number / p);
        long b = (long) Math.floor(number / q);
        long aRes = getAnswer(a, p, q);
        long bRes = getAnswer(b, p, q);

        dp.put(number, aRes + bRes);
        return dp.get(number);
    }
}
