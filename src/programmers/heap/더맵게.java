package programmers.heap;

import java.util.PriorityQueue;

public class 더맵게 {

  public static int solution(int[] scoville, int K) {
    int answer = 0;

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for(int i = 0; i < scoville.length; i++) {
      pq.add(scoville[i]);
    }

    while(true) {
      if(pq.isEmpty()) {
        answer = -1;
        break;
      }

      Integer first = pq.poll();
      if(first >= K) {
        break;
      }

      if(pq.isEmpty()) {
        answer = -1;
        break;
      }
      Integer second = pq.poll();

      int next = first + (second * 2);

      pq.add(next);
      answer++;
    }

    return answer;
  }

  public static void main(String[] args) {

    int[] s = {1, 2, 3, 9, 10, 12};
    int K = 7;

    System.out.println(solution(s, K));

  }

}
