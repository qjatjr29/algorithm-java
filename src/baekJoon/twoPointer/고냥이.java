package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 고냥이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int maxAlpha = Integer.parseInt(input.nextToken());

        // 사용한 알파벳 갯수 저장
        int[] alpha = new int[26];
        input = new StringTokenizer(br.readLine());
        String str = input.nextToken();

        int left = 0;
        int right = 0;
        int count = 0;
        int answer = 0;

        while(right < str.length()) {

            int num = str.charAt(right) - 'a';
            if(alpha[num] == 0) count++;
            alpha[num]++;

            if(count > maxAlpha) {
                while(true) {
                    if(left >= right) break;
                    int start = str.charAt(left) - 'a';
                    alpha[start]--;
                    left++;
                    if(alpha[start] == 0) {
                        count--;
                        break;
                    }
                }
            }

            answer = Math.max(answer, right - left + 1);
            right++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
