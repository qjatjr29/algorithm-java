package programmers.구현;

public class 예상대진표 {

  public int solution(int n, int a, int b) {
    int answer = 0;

    while(true) {
      answer++;
      int orderA = getOrder(a);
      int orderB = getOrder(b);

      if(orderA == orderB) break;

      a = orderA + 1;
      b = orderB + 1;

    }

    return answer;
  }

  private int getOrder(int number) {
    return (number - 1) / 2;
  }

}
