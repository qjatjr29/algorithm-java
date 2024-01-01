package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class ListOfUniqueNumbers {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        long answer = 0;

        int[] numbers = new int[N];

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        boolean[] checked = new boolean[100001];

        int lp = 0;
        int rp = 0;

        while(lp < N) {

            // 중복된 수를 찾을 떄 까지 rp 이동
            while(rp < N && !checked[numbers[rp]]) {
                checked[numbers[rp]] = true;
                rp++;
            }

            answer += (rp - lp);
            checked[numbers[lp]] = false;
            lp++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
