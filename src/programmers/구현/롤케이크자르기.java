package programmers.구현;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 롤케이크자르기 {

  public int solution(int[] topping) {
    int answer = 0;

    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> left = new HashSet<>();

    left.add(topping[0]);
    int right = 0;

    for(int i = 1; i < topping.length; i++) {

      int t = topping[i];

      int count = map.getOrDefault(t, 0);
      if(count == 0) right++;
      map.put(t, count + 1);
    }

    for(int i = 1; i < topping.length - 1; i++) {

      int cTopping = topping[i];
      if(left.size() == right) answer++;
      left.add(cTopping);
      int c = map.get(cTopping);
      if(c == 0) continue;
      map.put(cTopping, c - 1);
      if(c - 1 == 0) right--;
    }

    return answer;
  }

}
