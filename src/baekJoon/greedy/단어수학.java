package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 단어수학 {

    private static Map<Character, Integer> values;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int problems = Integer.parseInt(input.nextToken());

        int answer = 0;
        String[] words = new String[problems];
        Map<Character, Alphabet> alphaMap = new HashMap<>();
        List<Alphabet> alphabets = new ArrayList<>();
        values = new HashMap<>();

        for(int i = 0; i < problems; i++) {
            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();
            words[i] = word;

            for(int j = 0; j < word.length(); j++) {
                char alpha = word.charAt(j);
                Alphabet alphabet;
                if(alphaMap.containsKey(alpha)) {
                    alphabet = alphaMap.get(alpha);
                }
                else {
                    alphabet = new Alphabet(alpha, 0);
                    alphabets.add(alphabet);
                }
                alphabet.count += Math.pow(10, word.length() - 1 -j);
                alphaMap.put(alpha, alphabet);
            }
        }

        Collections.sort(alphabets);

        int value = 9;

        for(Alphabet alphabet : alphabets) {
            char alpha = alphabet.alpha;
            values.put(alpha, value);
            value--;
        }

        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            int digit = word.length() - 1;
            int number = 0;
            for(int j = 0; j < word.length(); j++) {
                number += Math.pow(10, digit) * values.get(word.charAt(j));
                digit--;
            }
            answer += number;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Alphabet implements Comparable<Alphabet> {
        char alpha;
        int count;

        public Alphabet(char alpha, int count) {
            this.alpha = alpha;
            this.count = count;
        }

        @Override
        public int compareTo(Alphabet o) {
            return o.count - this.count;
        }
    }
}
