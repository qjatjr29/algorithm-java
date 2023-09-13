package baekJoon.string;

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

public class 팰린드롬만들기 {

    private static final String IMPOSSIBLE = "I'm Sorry Hansoo";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        String originalName = input.nextToken();

        Map<Character, Integer> alphaMap = new HashMap<>();

        for(int i = 0; i < originalName.length(); i++) {
            Character alpha = originalName.charAt(i);
            alphaMap.put(alpha, alphaMap.getOrDefault(alpha, 0) + 1);
        }

        List<Character> keyList = new ArrayList<>(alphaMap.keySet());
        Collections.sort(keyList);

        StringBuilder stringBuilder = new StringBuilder();
        String answer = "";
        Character middleCharacter = null;

        for(Character key : keyList) {
            int count = alphaMap.get(key);
            if(count % 2 != 0 && originalName.length() % 2 == 0) {
                answer = IMPOSSIBLE;
                break;
            }
            else if(count % 2 != 0 && middleCharacter != null) {
                answer = IMPOSSIBLE;
                break;
            }
            else if(count % 2 != 0) {
                middleCharacter = key;
            }
            for(int i = 0; i < count / 2; i++) stringBuilder.append(key);
        }

        if(!answer.equals(IMPOSSIBLE)) {
            int size = stringBuilder.length();
            if(middleCharacter != null) {
                stringBuilder.append(middleCharacter);
            }
            for(int i = size - 1; i >= 0; i--) {
                stringBuilder.append(stringBuilder.charAt(i));
            }
            answer = stringBuilder.toString();
        }
        bw.write(answer);
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
