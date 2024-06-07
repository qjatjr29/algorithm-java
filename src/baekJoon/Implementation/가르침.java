package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 가르침 {

    private static final int PREFIX_INDEX = 4;
    private static final int SUFFIX_INDEX = 4;
    private static boolean[] usedAlpha;
    private static String[] words;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        words = new String[N];
        usedAlpha = new boolean[26];
        answer = 0;
        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();
            words[i] = word.substring(PREFIX_INDEX, word.length() - SUFFIX_INDEX);
        }

        // a n t i c 는 무조건 포함
        usedAlpha['a' - 'a'] = true;
        usedAlpha['n' - 'a'] = true;
        usedAlpha['t' - 'a'] = true;
        usedAlpha['i' - 'a'] = true;
        usedAlpha['c' - 'a'] = true;

        if(K < 5) {
            answer = 0;
        }
        else {
            dfs(0, 5, K);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int idx, int count, int k) {
        if (count == k) {
            int readableWordCount = checkWord();
            answer = Math.max(answer, readableWordCount);
        }

        for(int i = idx; i < 26; i++) {
            if (usedAlpha[i]) {
                continue;
            }

            usedAlpha[i] = true;
            dfs(i + 1, count + 1, k);
            usedAlpha[i] = false;
        }
    }

    private static int checkWord() {
        int count = 0;
        for(int i = 0; i < words.length; i++) {
            char[] alphabets = words[i].toCharArray();
            boolean chk = true;

            for(char alpha : alphabets) {
                if (!usedAlpha[alpha - 'a']) {
                    chk = false;
                    break;
                }
            }
            if (chk) {
                count++;
            }
        }
        return count;
    }
}
