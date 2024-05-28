package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 졸려 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        String word = input.nextToken();
        int tailStart = word.length() - 1 - (word.length() % 2 == 1 ? 1 : 0);
        Set<String> convertedWord = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        convertedWord.add(word);

        while(true) {
            String prev = convertedWord.stream()
                    .reduce((first, second) -> second)
                    .orElse(null);

            sb.delete(0, sb.length());

            for (int i = 0; i < prev.length(); i += 2) {
                sb.append(prev.charAt(i));
            }

            for (int j = tailStart; j >= 0; j -= 2) {
                sb.append(prev.charAt(j));
            }

            if (!convertedWord.add(sb.toString())) {
                break;
            }
        }

        int size = convertedWord.size();
        int order = count % size;

        Object[] wordList = convertedWord.toArray();
        String answer = (String) wordList[order];

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
