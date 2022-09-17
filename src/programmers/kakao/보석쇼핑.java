package programmers.kakao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 보석쇼핑 {

  private static Set<String> gemSet;
  private static Map<String, Integer> gemMap;
  private static int gap;

  public static int[] solution(String[] gems) {
    int[] answer = new int[2];
    gemMap = new HashMap<>();
    gemSet = new HashSet<>();

    for(int i = 0; i < gems.length; i++) {
      String str = gems[i];
      gemSet.add(str);
    }
    gap = gems.length + 1;

    int left = 0;
    int right = 0;

    while(true) {

      if(gemMap.size() == gemSet.size()) {
        gemMap.put(gems[left], gemMap.get(gems[left]) - 1);

        if(gemMap.get(gems[left]) == 0) {
          gemMap.remove(gems[left]);
        }
        left++;
      }
      else if(right == gems.length) break;
      else {
        gemMap.put(gems[right], gemMap.getOrDefault(gems[right], 0) + 1);
        right++;
      }

      if(gemMap.size() == gemSet.size()) {
        if (gap > right - left) {
          gap = right - left;
          answer[0] = left + 1;
          answer[1] = right;
        }
      }
    }

    return answer;
  }

  public static void main(String[] args) {


//    String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//    String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//    String[] gems = {"XYZ", "XYZ", "XYZ"};
    String[] gems = {"XYZ"};
//    String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

    int[] result = solution(gems);

    for (int i : result) {
      System.out.println(i);
    }

  }

}
