package baekJoon.Implementation;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class 조합 {

    private static BigInteger answer;
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        BigInteger number1 = BigInteger.ONE;
        BigInteger number2 = BigInteger.ONE;

        for(int i = 0; i < m; i++) {
            number1 = number1.multiply(new BigInteger(String.valueOf(n - i)));
            number2 = number2.multiply(new BigInteger(String.valueOf(i + 1)));
        }

        answer = number1.divide(number2);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
