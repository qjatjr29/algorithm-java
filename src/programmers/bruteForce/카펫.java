package programmers.bruteForce;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class 카펫 {

  public static int[] solution(int brown, int yellow) {
    int[] answer = new int[2];

    int sum = brown + yellow;

    int cnt = 1;
    while(true) {
      if(yellow % cnt == 0) {
        int row = max(cnt, yellow / cnt);
        int col = min(cnt, yellow / cnt);

        if((row * 2 + col * 2 + 4) == brown) {
          answer[0] = row + 2;
          answer[1] = col + 2;
          break;
        }
      }
      cnt++;
    }
    return answer;
  }

  public static void main(String[] args) {

    int[] solution = solution(10, 2);
    System.out.println(solution[0]);
    System.out.println(solution[1]);

  }

}
