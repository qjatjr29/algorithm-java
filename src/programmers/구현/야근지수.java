package programmers.구현;

import java.util.Collections;
import java.util.PriorityQueue;

public class 야근지수 {

  private static long[] fatigues;

  public long solution(int n, int[] works) {
    long answer = 0;

    fatigues = new long[50001];

    for(int i = 1; i < 50001; i++) {
      fatigues[i] = i * i;
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    for(int i = 0; i < works.length; i++) {
      pq.add(works[i]);
    }

    while(!pq.isEmpty()) {

      if(n == 0) break;
      int count = pq.poll();
      if(count == 0) break;
      pq.add(count - 1);
      n--;
    }

    while(!pq.isEmpty()) {
      int count = pq.poll();
      answer += fatigues[count];
    }

    return answer;
  }
}
