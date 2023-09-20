package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15927
public class 회문은회문아니야 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    String str = st.nextToken();
    int answer = -1;

    // 해당 문자열이 팰린드롬인가?
    // 만약 맞다면 해당 문자열이 같은 문자로만 이루어져있는지 확인
      // 같은 문자로만 이루어진 팰린드롬 문자열 -> -1 출력
      // 아니라면 (문자열 길이 - 1) 출력
    // 문자열이 팰린드롬이 아니라면, 문자열 길이 출력

    char c = str.charAt(0);

    int left = 0;
    int right = str.length() - 1;
    boolean isPalindrome = true;

    while(left < right) {

      char c1 = str.charAt(left);
      char c2 = str.charAt(right);

      if(c1 != c2) {
        isPalindrome = false;
        break;
      }
      left++;
      right--;
    }

    if(!isPalindrome) answer = str.length();
    else {

      boolean isSameCharacter = true;
      char alpha = str.charAt(0);
      for(int i = 1; i < str.length(); i++) {
        if(alpha != str.charAt(i)) {
          isSameCharacter = false;
          break;
        }
      }

      if(!isSameCharacter) answer = str.length() - 1;
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
