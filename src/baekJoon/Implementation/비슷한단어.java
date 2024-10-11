package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 비슷한단어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int wordCount = Integer.parseInt(input.nextToken());
        int answer = 0;

        input = new StringTokenizer(br.readLine());
        String firstWord = input.nextToken();
        char[] firstWordAlphabets = firstWord.toCharArray();

        for(int i = 0; i < wordCount - 1; i++) {
            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();
            int[] wordAlphabets = new int[27];

            for(char alpha : firstWordAlphabets) {
                wordAlphabets[alpha - 'A']++;
            }

            int cnt = 0;
            for(char alpha : word.toCharArray()) {
                if(wordAlphabets[alpha - 'A'] > 0) {
                    cnt++;
                    wordAlphabets[alpha - 'A']--;
                }
            }

            if(firstWord.length() == word.length() && (firstWord.length() == cnt || firstWord.length() - 1 == cnt)) {
                answer++;
            }
            if(firstWord.length() == word.length() - 1 && word.length() - 1 == cnt) {
                answer++;
            }
            if(firstWord.length() == word.length() + 1 && word.length() == cnt) {
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
