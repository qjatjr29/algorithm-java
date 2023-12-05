package baekJoon.backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 암호만들기 {

    private static List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
    private static List<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());
        answer = new ArrayList<>();

        Character[] alphas = new Character[C];

        input = new StringTokenizer(br.readLine());

        for(int i = 0; i < C; i++) {
            alphas[i] = input.nextToken().charAt(0);
        }

        Arrays.sort(alphas);
        combinePassword(0, L, new ArrayList<>(), alphas);

        for(String ans : answer) {
            bw.write(ans);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combinePassword(int index, int targetCount, List<Character> result, Character[] alphas) {

        if(result.size() == targetCount) {

            // 모음이 들어가 있는지 확인
            int vowelCount = 0;
            StringBuilder sb = new StringBuilder();
            for (Character character : result) {
                if (vowels.contains(character)) {
                    vowelCount++;
                }
                sb.append(character);
            }

            if(vowelCount == 0) return;

            // 자음이 2개 이상인지 확인
            int consonantCount = result.size() - vowelCount;
            if(consonantCount < 2) return;

            answer.add(sb.toString());
        }

        for(int i = index; i < alphas.length; i++) {
            result.add(alphas[i]);
            combinePassword(i + 1, targetCount, result, alphas);
            result.remove(result.size() - 1);
        }

    }

}
