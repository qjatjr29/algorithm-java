package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 안정적인문자열 {

    private static final String END_COMMAND = "-";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input;

        int testcase = 1;
        while(true) {
            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();

            if(word.contains(END_COMMAND)) {
                break;
            }

            int openCount = 0;
            int closeCount = 0;
            int operCount = 0;

            for(char ch : word.toCharArray()) {

                if(ch == '}') {
                    if(openCount > 0) {
                        openCount--;
                        continue;
                    }
                    closeCount++;
                }

                if(ch == '{') {
                    openCount++;
                }
            }

            if(closeCount > 0) {
                int cnt = (int) Math.ceil((double) closeCount / 2);
                operCount += cnt;
                openCount += (cnt - closeCount / 2);
            }
            operCount += openCount / 2;

            bw.write(String.valueOf(testcase) + ". ");
            bw.write(String.valueOf(operCount));
            bw.newLine();
            testcase++;
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
