package baekJoon.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class 풍선맞추기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int[] arrows = new int[1_000_001]; // 해당 높이에 있는 화살 수

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int balloon = Integer.parseInt(input.nextToken());

            // 현재 풍선의 높이에 화살이 있는 경우
            if (arrows[balloon] > 0) {
                arrows[balloon]--;
            }

            arrows[balloon - 1]++;
        }

        int answer = 0;

        for (int arrow : arrows) {
            answer += arrow;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
