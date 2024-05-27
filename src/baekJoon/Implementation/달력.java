package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 달력 {

    private static final int DAY_OF_YEAR = 365;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int[] days = new int[DAY_OF_YEAR + 1];
        int answer = 0;

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            int sDay = Integer.parseInt(input.nextToken());
            int eDay = Integer.parseInt(input.nextToken());
            for(; sDay <= eDay; sDay++) {
                days[sDay] += 1;
            }
        }

        int height = 0;
        int width = 0;

        for(int i = 0; i <= DAY_OF_YEAR; i++) {
            if(days[i] == 0) {
                answer += height * width;
                height = 0;
                width = 0;
                continue;
            }

            width++;
            height = Math.max(height, days[i]);
        }
        answer += height * width;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

}
