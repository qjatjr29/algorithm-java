package codeTest.devPass;

import static java.util.Arrays.sort;

public class 악성코드 {

  public static int solution(String S, String pattern) {
    int answer = 0;

    int patternLength = pattern.length();

    char[] patternList = pattern.toCharArray();
    sort(patternList);

    int index = 0;
    while(true) {

      if(index + patternLength > S.length()) break;

      String substring = S.substring(index, index + patternLength);

      char[] subList = substring.toCharArray();
      sort(subList);
      boolean chk = true;

      for(int i = 0; i < subList.length; i++) {
        if(subList[i] != patternList[i]) {
          chk = false;
          break;
        }
      }

      if(chk) answer++;
      index++;
    }

    return answer;
  }

  public static void main(String[] args) {

//    String s = "tegsornamwaresomran";
//    String pattern = "ransom";

//    String s = "wreawerewa";
//    String pattern = "ware";

//    String s = "ababababababa";
//    String pattern = "aabba";

    String s = "ababababababa";
    String pattern = "aabba";

    System.out.println(solution(s, pattern));

  }

}
