package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 단축키지정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int optionCnt = Integer.parseInt(input.nextToken());
        boolean[] assigned = new boolean[26];

        for(int i = 0; i < optionCnt; i++) {
            String[] words = br.readLine().split(" ");

            boolean shortcutAssigned = false;

            for(int j = 0; j < words.length; j++) {
                String word = words[j];
                char firstAlphabet = word.toLowerCase().charAt(0);

                if(!assigned[firstAlphabet - 'a']) {
                    assigned[firstAlphabet - 'a'] = true;
                    bw.write(formatShortcut(words, j, 0));
                    shortcutAssigned = true;
                    break;
                }
            }

            if(!shortcutAssigned) {
                for(int j = 0; j < words.length; j++) {
                    String word = words[j];
                    String lowerWord = word.toLowerCase();
                    for(int k = 0; k < word.length(); k++) {
                        char alphabet = lowerWord.charAt(k);
                        if(!assigned[alphabet - 'a']) {
                            assigned[alphabet - 'a'] = true;
                            bw.write(formatShortcut(words, j, k));
                            shortcutAssigned = true;
                            break;
                        }
                    }
                    if(shortcutAssigned) {
                        break;
                    }
                }
            }

            if(!shortcutAssigned) {
                bw.write(String.join(" ", words));
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static String formatShortcut(String[] words, int wordIndex, int charIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordIndex; i++) {
            sb.append(words[i]).append(" ");
        }
        sb.append(words[wordIndex], 0, charIndex)
                .append("[")
                .append(words[wordIndex].charAt(charIndex))
                .append("]")
                .append(words[wordIndex].substring(charIndex + 1))
                .append(" ");
        for (int i = wordIndex + 1; i < words.length; i++) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
