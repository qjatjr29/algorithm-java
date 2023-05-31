package programmers.구현;

import java.util.*;

public class 귤고르기 {
  private static Map<Integer, Integer> tangerineMap;

  public int solution(int k, int[] tangerine) {
    int answer = 0;
    tangerineMap = new HashMap<>();

    for(int type : tangerine) {
      int count = tangerineMap.getOrDefault(type, 0) + 1;
      tangerineMap.put(type, count);
    }

    List<Integer> countList = new ArrayList<>();

    for(int key : tangerineMap.keySet()) {
      int count = tangerineMap.get(key);
      countList.add(count);
    }


    Collections.sort(countList, Comparator.reverseOrder());

    for(int count : countList) {
      if(k <= 0) break;
      k -= count;
      answer++;
    }
    return answer;
  }
}
