package programmers.dataStructure;

import java.util.Collections;
import java.util.PriorityQueue;

public class 이중우선순위큐 {

  private static int cnt;
  private static PriorityQueue<Integer> maxQueue;
  private static PriorityQueue<Integer> minQueue;

  public static int[] solution(String[] operations) {
    int[] answer = new int[2];

    maxQueue = new PriorityQueue<>(Collections.reverseOrder());
    minQueue = new PriorityQueue<>();

    for(int i = 0; i < operations.length; i++) {
      String[] info = operations[i].split(" ");
      String operation = info[0];
      int num = Integer.parseInt(info[1]);

      if(operation.equals("I")) {
        maxQueue.add(num);
        minQueue.add(num);
        cnt++;
      }
      else {
        if(cnt > 0) {
          if(num == -1) {
            minQueue.poll();
          }
          else if(num == 1) {
            maxQueue.poll();
          }
          cnt--;
          if(cnt == 0) {
            maxQueue = new PriorityQueue<>(Collections.reverseOrder());
            minQueue = new PriorityQueue<>();
          }
        } else continue;
      }
    }
    if(cnt > 0) {
      answer[0] = maxQueue.poll();
      answer[1] = minQueue.poll();
    }
    return answer;
  }

  public static void main(String[] args) {

//    String[] operations = {
//        "I 16", "I -5643", "D -1", "D 1", "I 123", "D -1"
//    };

//    String[] operations = {
//        "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"
//    };

    String[] operations = {
        "I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"
    };

    int[] result = solution(operations);

    for (int i : result) {
      System.out.println(i);
    }

  }
}
