package goorm;
import java.io.*;
import java.util.*;

public class 회문 {

    private static final int PALINDROME = 0;
    private static final int PSEUDO_PALINDROME = 1;
    private static final int STRING = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int StringCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < StringCount; i++) {
            input = new StringTokenizer(br.readLine());
            String str = input.nextToken();

            int result = checkPalindrome(str, false);

            bw.write(String.valueOf(result));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static int checkPalindrome(String str, boolean isMisMatch) {

        int lp = 0;
        int rp = str.length() - 1;

        while(true) {

            if(lp >= rp) {
                if(isMisMatch) return PSEUDO_PALINDROME;
                return PALINDROME;
            }

            char leftCharacter = str.charAt(lp);
            char rightCharacter = str.charAt(rp);

            if(leftCharacter != rightCharacter) {
                if(isMisMatch) return STRING;
                StringBuilder sb = new StringBuilder(str);

                StringBuilder removeLeftCharacterString = sb.deleteCharAt(lp);

                sb = new StringBuilder(str);
                StringBuilder removeRightCharacterString = sb.deleteCharAt(rp);

                return Math.min(
                    checkPalindrome(removeLeftCharacterString.toString(), true),
                    checkPalindrome(removeRightCharacterString.toString(), true)
                );
            }
            lp++;
            rp--;
        }
    }

}
