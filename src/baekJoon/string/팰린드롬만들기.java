package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 팰린드롬만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        String str = input.nextToken();
        int answer = str.length();

        // 가장 긴 팰린드롬 길이를 구한다.
        int lp = 0;
        while(true) {
            if(isPalindorm(lp, str)) {
                break;
            }
            lp++;
        }

        answer += lp;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isPalindorm(int lp, String target) {
        int rp = target.length() - 1;
        while(lp < rp) {
            char left = target.charAt(lp);
            char right = target.charAt(rp);
            if(left != right) {
                return false;
            }
            lp++;
            rp--;
        }

        return true;
    }

}
