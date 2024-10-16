package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class IQTest {

    private static final String SEVERAL_ANSWER = "A";
    private static final String NON_ANSWER = "B";

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());

        int[] numbers = new int[N];
        input = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        if(N == 1 || (N == 2 && numbers[1] != numbers[0])) {
            bw.write(SEVERAL_ANSWER);
        }
        else if(N == 2) {
            bw.write(String.valueOf(numbers[0]));
        }
        else {
            int a, b;
            if(numbers[1] == numbers[0]) {
                a = 1;
                b = 0;
            }
            else {
                a = (numbers[2] - numbers[1]) / (numbers[1] - numbers[0]);
                b = numbers[1] - (a * numbers[0]);
            }

            boolean chk = true;
            for(int i = 1; i < N; i++) {
                if(numbers[i] != numbers[i - 1] * a + b) {
                    chk = false;
                    break;
                }
            }

            if(chk) {
                bw.write(String.valueOf(numbers[numbers.length - 1] * a + b));
            }
            else {
                bw.write(NON_ANSWER);
            }
        }

        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
