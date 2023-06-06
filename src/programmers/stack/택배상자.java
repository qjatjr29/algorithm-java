package programmers.stack;

import java.util.Stack;

public class 택배상자 {
  private Stack<Integer> subContainerBelt;

  public int solution(int[] order) {
    int answer = 0;
    int num = 0;

    subContainerBelt = new Stack<>();

    for(int i = 0; i < order.length; i++) {
      int number = order[i];

      if(!subContainerBelt.isEmpty()) {

        int sub = subContainerBelt.peek();
        if(sub == number) {
          answer++;
          subContainerBelt.pop();
          continue;
        }
      }

      if(number <= num) break;

      for(int j = num + 1; j <= order.length; j++) {
        if(j == number) {
          answer++;
          num = j;
          break;
        }
        subContainerBelt.push(j);
      }

    }
    return answer;
  }

}
