package goorm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 부분팰린드롬문자열 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        String str = input.nextToken();
        int answer = 1;
        boolean[][] isPalidrome = new boolean[str.length()][str.length()];

        for(int i = 0; i < str.length(); i++) isPalidrome[i][i] = true;

        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i - 1) == str.charAt(i)) isPalidrome[i - 1][i] = true;
        }

        for(int i = 2; i < str.length(); i++) {
            for(int j = 0; j < str.length() - i; j++) {
                int cmp = i + j;
                if(str.charAt(j) == str.charAt(cmp) && isPalidrome[j + 1][cmp - 1]) {
                    isPalidrome[j][cmp] = true;
                    answer = Math.max(answer, cmp - j + 1);
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
