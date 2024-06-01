package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class 다음팰린드롬수 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        String answer = "";
        String N = input.nextToken();
        int length = N.length();
        int halfLength = length / 2;

        // 9, 99, 999 => 11, 101, 1001
        if(N.equals("9".repeat(length))) {
            BigInteger bigInteger = new BigInteger(N);
            BigInteger add = bigInteger.add(BigInteger.valueOf(2));
            answer = add.toString();
        }

        else if(length == 1) {
            BigInteger bigInteger = new BigInteger(N);
            BigInteger add = bigInteger.add(BigInteger.valueOf(1));
            answer = add.toString();
        }

        else if(length % 2 == 1) {

            String left = N.substring(0, halfLength);
            String right = N.substring(halfLength + 1);
            char mid = N.charAt(halfLength);

            StringBuilder sb = new StringBuilder(left);
            String reverseLeft = sb.reverse().toString();
            sb.delete(0, sb.length());

            BigInteger lNumber = new BigInteger(reverseLeft);
            BigInteger rNumber = new BigInteger(right);

            if(lNumber.compareTo(rNumber) > 0) {
                sb.append(left);
                sb.append(mid);
                sb.append(reverseLeft);
                answer = sb.toString();
            }

            if(lNumber.compareTo(rNumber) <= 0) {
                int midValue = (mid - '0') + 1;
                if(midValue >= 10) {
                    midValue = 0;
                    BigInteger tempInteger = new BigInteger(left);
                    BigInteger add = tempInteger.add(BigInteger.valueOf(1L));
                    String temp = add.toString();
                    StringBuilder ssb = new StringBuilder(temp);
                    sb.append(temp);
                    sb.append(midValue);
                    sb.append(ssb.reverse());
                    answer = sb.toString();
                }
                else {
                    sb.append(left);
                    sb.append(midValue);
                    sb.append(reverseLeft);
                    answer = sb.toString();
                }
            }
        }

        else {

            String left = N.substring(0, halfLength);
            String right = N.substring(halfLength);

            StringBuilder sb = new StringBuilder(left);
            String reverseLeft = sb.reverse().toString();
            sb.delete(0, sb.length());

            BigInteger lNumber = new BigInteger(reverseLeft);
            BigInteger rNumber = new BigInteger(right);

            if(lNumber.compareTo(rNumber) > 0) {
                sb.append(left);
                sb.append(reverseLeft);
                answer = sb.toString();
            }

            if(lNumber.compareTo(rNumber) <= 0) {
                BigInteger tempInteger = new BigInteger(left);
                BigInteger add = tempInteger.add(BigInteger.valueOf(1L));
                String temp = add.toString();
                StringBuilder ssb = new StringBuilder(temp);
                sb.append(temp);
                sb.append(ssb.reverse());
                answer = sb.toString();
            }

        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
