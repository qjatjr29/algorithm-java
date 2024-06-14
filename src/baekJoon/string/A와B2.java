package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A와B2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        String str = input.nextToken();

        input = new StringTokenizer(br.readLine());
        String target = input.nextToken();

        StringBuilder sb = new StringBuilder();
        sb.append(target);

        int answer = checkConvert(str, target) ? 1 : 0;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkConvert(String str, String target) {

        if(str.length() == target.length()) {
            return str.equals(target);
        }

        if(target.charAt(target.length() - 1) == 'A') {
            if(checkConvert(str, target.substring(0, target.length() - 1))) {
                return true;
            }
        }

        if(target.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder();
            sb.append(target.substring(1));
            sb.reverse();
            if(checkConvert(str, sb.toString())) {
                return true;
            }
        }

        return false;
    }
}
