package programmers.stack;
import java.util.*;

public class 뒤에있는큰수찾기 {

  public int[] solution(int[] numbers) {

    Stack<Integer> stack = new Stack<>();

    // 정답 배열 생성
    int[] answer = new int[numbers.length];

    stack.push(0);

    for (int i = 1; i < numbers.length; i++) {

      while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
        answer[stack.pop()] = numbers[i];
      }
      stack.push(i);
    }

    // stack에 남아 있는 수 = 모든 index를 탐색 후 뒤에 있는 큰 수가 없는 경우
    while (!stack.isEmpty()) {
      answer[stack.pop()] = -1;
    }

    return answer;
  }

}
