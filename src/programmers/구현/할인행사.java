package programmers.구현;

import java.util.HashMap;
import java.util.Map;

public class 할인행사 {

  private static final int SALE_DAY = 10;

  private static Map<String, Integer> wantMap;

  public int solution(String[] want, int[] number, String[] discount) {
    int answer = 0;

    wantMap = new HashMap<>();

    for(int i = 0; i < want.length; i++) {
      String product = want[i];
      wantMap.put(product, i);
    }

    int[] count = new int[want.length];
    for(int i = 0; i < discount.length; i++) {

      if(i + SALE_DAY > discount.length) break;
      boolean register = true;
      count = new int[want.length];

      for(int j = i; j < i + SALE_DAY; j++) {
        String target = discount[j];
        if(!wantMap.containsKey(target)) {
          register = false;
          break;
        }

        int idx = wantMap.get(target);
        if(count[idx] + 1 > number[idx]) {
          register = false;
          break;
        }
        count[idx]++;
      }
      if(register) {
        answer++;
      }
    }

    return answer;
  }

}
