package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int gameNumber = 0; gameNumber < T; gameNumber++) {

            List<Integer>[] alphaList = new List[26];
            for(int i = 0; i < 26; i++) {
                alphaList[i] = new ArrayList<>();
            }

            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();
            input = new StringTokenizer(br.readLine());
            int targetNumber = Integer.parseInt(input.nextToken());

            for(int i = 0; i < word.length(); i++) {
                int aNum = word.charAt(i) - 'a';
                alphaList[aNum].add(i);
            }

//            if(targetNumber == 1) {
//                bw.write("1  1");
//                continue;
//            }

            int minLength = Integer.MAX_VALUE;
            int maxLength = -1;

            for(int i = 0; i < 26; i++) {
                if(alphaList[i].size() >= targetNumber) {
                    for(int j = 0; j < alphaList[i].size() - targetNumber + 1; j++) {
                        int length = alphaList[i].get(j + targetNumber - 1) - alphaList[i].get(j) + 1;
                        minLength = Math.min(minLength, length);
                        maxLength = Math.max(maxLength, length);
                    }
                }
            }

            if(minLength == Integer.MAX_VALUE) {
                bw.write("-1");
            }
            else {
                bw.write(minLength + " " + maxLength);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
