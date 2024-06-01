package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 접두사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(input.nextToken());
        String[] words = new String[count];

        for(int i = 0; i < count; i++) {
            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();
            words[i] = word;
        }

        Arrays.sort(words);
        int answer = 0;

        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean chk = true;
            for(int j = i + 1; j < words.length; j++) {
                String otherWord = words[j];
                if(otherWord.indexOf(word) == 0) {
                    chk = false;
                    break;
                }
            }
            if(chk) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
