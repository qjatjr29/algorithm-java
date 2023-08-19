package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 가장긴짝수연속한부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int deleteCnt = Integer.parseInt(input.nextToken());
        int[] numbers = new int[size];
        int answer = 0;

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        int lp = 0;
        int rp = 0;
        int checkCnt = 0;

        while(lp <= rp && rp < size) {

            // 더 지울 수 있는 경우
            if(checkCnt < deleteCnt) {

                if(numbers[rp] % 2 != 0) checkCnt++;
                rp++;
                answer = Math.max(rp - lp - checkCnt, answer);
            }
            // 지울 수 없고, 현재 숫자 짝수
            else if (numbers[rp] % 2 == 0) {
                rp++;
                answer = Math.max(rp - lp - checkCnt, answer);
            }
            // 지울 수 없고, 현재 숫자가 홀수
            else {
                if(numbers[lp] % 2 != 0) {
                    checkCnt--;
                }
                lp++;
            }

        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
