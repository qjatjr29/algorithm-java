package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 떡먹는호랑이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int day = Integer.parseInt(input.nextToken());
        int riceCakeAmount = Integer.parseInt(input.nextToken());

        int cnt = 0;
        while(true) {

            boolean check = true;
            int[] riceCake = new int[day + 1];
            riceCake[day] = riceCakeAmount;
            riceCake[day - 1] = riceCakeAmount - cnt;

            for(int i = day - 2; i > 0; i--) {
                riceCake[i] = riceCake[i + 2] - riceCake[i + 1];
                if(riceCake[i] < 1 || riceCake[i] > riceCake[i + 1]) {
                    check = false;
                    break;
                }
            }

            if(check) {
                bw.write(String.valueOf(riceCake[1]));
                bw.newLine();
                bw.write(String.valueOf(riceCake[2]));
                bw.newLine();
                break;
            }
            cnt++;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
