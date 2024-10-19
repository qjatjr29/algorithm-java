package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 피자굽기 {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int depth = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());
        int[] oven = new int[depth + 1];
        oven[0] = Integer.MAX_VALUE;

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= depth; i++) {
            int next = Integer.parseInt(input.nextToken());
            oven[i] = Math.min(oven[i - 1], next);
        }

        answer = depth;
        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int pizza = Integer.parseInt(input.nextToken());
            putPizza(pizza, oven);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void putPizza(int pizza, int[] oven) {

        boolean chk = false;

        for(int i = answer; i > 0; i--) {
            if(pizza <= oven[i]) {
                answer = i;
                oven[i] = 0;
                chk = true;
                break;
            }
        }

        if(!chk) {
            answer = 0;
        }
    }
}
