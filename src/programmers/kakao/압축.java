package programmers.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {

  private static Map<String, Integer> dictionary;
  private static List<Integer> result;

  public static int[] solution(String msg) {
    int[] answer;

    dictionary = new HashMap();
    result = new ArrayList<>();

    setup();

    recursive("", -1, msg);

    answer = new int[result.size()];

    for(int i = 0; i < result.size(); i++) {
      answer[i] = result.get(i);
    }
    return answer;
  }

  private static void recursive(String rtn, int idx, String msg) {

    if(msg.length() > idx + 1) {
      String temp = rtn + msg.charAt(idx + 1);
      if(dictionary.containsKey(temp))  recursive(temp, idx + 1, msg);
      else {
        dictionary.put(temp, dictionary.size() + 1);
        result.add(dictionary.get(rtn));
        recursive(String.valueOf(msg.charAt(idx + 1)), idx + 1, msg);
      }
    }
    else result.add(dictionary.get(rtn));
  }

  private static void setup() {
    for(int i = 1; i <= 26; i++) {
      char alpha = (char) (64 + i);
      dictionary.put(String.valueOf(alpha), i);
    }
  }


  public static void main(String[] args) {

    solution("KAKAO");

  }

}
