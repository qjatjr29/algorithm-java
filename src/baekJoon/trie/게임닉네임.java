package baekJoon.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 게임닉네임 {

    private static final int ROOT = 1;
    private static final int MAX_SIZE = 100000 * 10 + 5;
    private static int[] nicknameCount;
    private static boolean[] checked;
    private static String[] alias;
    private static int[][] next;
    private static int nIndex;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        next = new int[MAX_SIZE][26];
        nicknameCount = new int[MAX_SIZE];
        checked = new boolean[MAX_SIZE];
        nIndex = 2;

        int userCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < userCount; i++) {
            input = new StringTokenizer(br.readLine());
            String nickname = input.nextToken();
            String alias = makeTrie(nickname);
            if(alias.isBlank()) {
                int count = findNicknameCount(nickname);
                alias = nickname;
                if(count != 1) alias += String.valueOf(count);
            }
            bw.write(alias);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static String makeTrie(String nickname) {
        int cNode = ROOT;
        StringBuilder sb = new StringBuilder();
        String alias = "";
        for(int i = 0; i < nickname.length(); i++) {
            char alpha = nickname.charAt(i);
            int index = convertToInt(alpha);
            sb.append(alpha);
            if(next[cNode][index] == 0) {
                next[cNode][index] = nIndex++;
                if(alias.isBlank()) alias = sb.toString();
            }
            cNode = next[cNode][index];
        }
        nicknameCount[cNode]++;
        return alias;
    }

    private static int findNicknameCount(String nickname) {
        int cNode = ROOT;
        for(int i = 0; i < nickname.length(); i++) {
            char alpha = nickname.charAt(i);
            int index = convertToInt(alpha);
            cNode = next[cNode][index];
        }
        return nicknameCount[cNode];
    }

    private static int convertToInt(char ch) {
        return ch - 'a';
    }
}
