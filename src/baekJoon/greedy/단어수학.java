package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 단어수학 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int problems = Integer.parseInt(input.nextToken());

        int answer = 0;
        String[] words = new String[problems];

        for(int i = 0; i < problems; i++) {
            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();
            words[i] = word;
        }

        answer = calculateSumByWords(words);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int calculateSumByWords(String[] words) {

        Map<Character, Integer> alphabetValues = new HashMap<>();
        Map<Character, Alphabet> alphaMap = new HashMap<>();
        List<Alphabet> usedAlphabets = new ArrayList<>();

        setAlphabetCount(words, alphaMap, usedAlphabets);

        Collections.sort(usedAlphabets);

        decideAlphabetValue(usedAlphabets, alphabetValues);

        return Arrays.stream(words)
                .mapToInt(word -> convertToNumbers(word, alphabetValues))
                .sum();
    }

    private static int convertToNumbers(String word, Map<Character, Integer> alphabetValues) {
        int number = 0;
        int digit = word.length() - 1;
        for(int j = 0; j < word.length(); j++) {
            number += Math.pow(10, digit) * alphabetValues.get(word.charAt(j));
            digit--;
        }
        return number;
    }

    private static void decideAlphabetValue(List<Alphabet> alphabets, Map<Character, Integer> alphabetValues) {
        int value = 9;

        for(Alphabet alphabet : alphabets) {
            char alpha = alphabet.alpha;
            alphabetValues.put(alpha, value);
            value--;
        }
    }

    private static void setAlphabetCount(String[] words, Map<Character, Alphabet> alphaMap,
                                         List<Alphabet> usedAlphabets) {
        for(String word : words) {
            for(int i = 0; i < word.length(); i++) {
                char alpha = word.charAt(i);
                Alphabet alphabet;
                if(alphaMap.containsKey(alpha)) {
                    alphabet = alphaMap.get(alpha);
                }
                else {
                    alphabet = new Alphabet(alpha, 0);
                    usedAlphabets.add(alphabet);
                }
                alphabet.count += Math.pow(10, word.length() - 1 - i);
                alphaMap.put(alpha, alphabet);
            }
        }
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
