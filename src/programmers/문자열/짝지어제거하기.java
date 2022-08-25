package programmers.문자열;

import java.util.ArrayList;
import java.util.List;

public class 짝지어제거하기 {

  public static int solution(String s)
  {
    int answer = -1;

    List<Character> remainList = new ArrayList<>();

    remainList.add(s.charAt(0));

    for(int i = 1; i < s.length(); i++) {

      if(remainList.isEmpty()) {
        remainList.add(s.charAt(i));
        continue;
      }
      // 앞의 것과 같은 경우
      if(remainList.get(remainList.size() - 1) == s.charAt(i)) {
        remainList.remove(remainList.size() - 1);
      }
      else remainList.add(s.charAt(i));
    }

    if(remainList.isEmpty()) answer = 1;
    else answer = 0;
    return answer;
  }

  public static void main(String[] args) {

//    String s = "baabaa";
    String s = "cdcd";

    System.out.println(solution(s));

  }

}
