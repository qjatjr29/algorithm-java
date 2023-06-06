package programmers.구현;

public class 마법의엘리베이터 {

  private static final int TARGET_NUMBER = 0;

  public int solution(int storey) {
    int answer = 0;

    while(true) {

      if(storey == TARGET_NUMBER) break;
      int number = storey % 10;
      int next = (storey / 10) % 10;

      if(number < 5) {
        answer += number;
      }
      else if(number == 5) {
        answer += number;
        if(next >= 5) storey += 10;
      }

      else {
        answer += (10 - number);
        storey += 10;
      }
      storey /= 10;
    }

    return answer;
  }

}
