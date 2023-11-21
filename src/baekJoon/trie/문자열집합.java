package baekJoon.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 문자열집합 {

    private static final int ROOT = 1;
    private static final int MAX_SIZE = 10000 * 500 + 5;
    private static boolean[] check;
    private static int[][] next;
    private static int unused;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int inputCount = Integer.parseInt(input.nextToken());
        int queryCount = Integer.parseInt(input.nextToken());
        check = new boolean[MAX_SIZE];
        next = new int[MAX_SIZE][26];
        unused = 2;
        int answer = 0;

        for(int i = 0; i < inputCount; i++) {
            input = new StringTokenizer(br.readLine());
            String word = input.nextToken();
            makeTrie(word);
        }

        for(int i = 0; i < queryCount; i++) {
            input = new StringTokenizer(br.readLine());
            String queryWord = input.nextToken();
            if(isContains(queryWord)) answer++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void makeTrie(String input) {
        int cNode = ROOT;

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int index = convertToInt(ch);

            if(next[cNode][index] == 0) {
                next[cNode][index] = unused++;
            }
            cNode = next[cNode][index];
        }
        check[cNode] = true;
    }

    private static boolean isContains(String query) {
        int cNode = ROOT;

        for(int i = 0; i < query.length(); i++) {
            char ch = query.charAt(i);
            int index = convertToInt(ch);

            if(next[cNode][index] == 0) {
                return false;
            }
            cNode = next[cNode][index];
        }
        return check[cNode];
    }

    private static int convertToInt(char ch) {
        return ch - 'a';
    }
}
