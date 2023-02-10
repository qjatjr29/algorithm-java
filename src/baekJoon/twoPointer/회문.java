package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17609

public class 회문 {

  private static final int PALINDROME = 0;
  private static final int PSEUDO_PALINDROME = 1;
  private static final int GENERAL_STRING = 2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int stringCount = Integer.parseInt(st.nextToken());
    int[] answers = new int[stringCount];

    for (int i = 0; i < stringCount; i++) {
      st = new StringTokenizer(br.readLine());
      String str = st.nextToken();

      answers[i] = decidePalindrome(str, false);
    }

    for (int answer : answers) {
      bw.write(String.valueOf(answer));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();
  }

  private static int decidePalindrome(String str, boolean isDifferent) {

    int l_point = 0;
    int r_point = str.length() - 1;

    while(true) {
      if(l_point >= r_point) {
        if(isDifferent) return PSEUDO_PALINDROME;
        return PALINDROME;
      }

      char left_char = str.charAt(l_point);
      char right_char = str.charAt(r_point);

      if(left_char != right_char) {

        if(isDifferent) return GENERAL_STRING;
        else {
          StringBuilder sb = new StringBuilder(str);
          StringBuilder removeLeftCharacter = sb.deleteCharAt(l_point);

          sb = new StringBuilder(str);
          StringBuilder removeRightCharacter = sb.deleteCharAt(r_point);

          return Math.min(
              decidePalindrome(removeLeftCharacter.toString(), true),
              decidePalindrome(removeRightCharacter.toString(), true));
        }
      }

      l_point++;
      r_point--;
    }
  }

}
