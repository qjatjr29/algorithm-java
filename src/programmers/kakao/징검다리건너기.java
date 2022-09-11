package programmers.kakao;

import static java.lang.Math.max;

public class 징검다리건너기 {

  public static int solution(int[] stones, int k) {
    int answer = 0;

    int min = 0;
    int max = 200000000;

    while(true) {
      if(min > max) break;
      int mid = (min + max) / 2;
      if(chk(mid, stones, k)) {
        min = mid + 1;
        answer = max(answer, mid);
      }
      else {
        max = mid - 1;
      }
    }
    return answer;
  }

  private static boolean chk(int friends, int[] stones, int k) {
    int skipStone = 0;
    for (int stone : stones) {
      if(stone < friends) skipStone++;
      else skipStone = 0;
      if(skipStone == k) return false;
    }
    return true;
  }

  public static void main(String[] args) {

    int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
    int k = 3;

    System.out.println(solution(stones, k));
  }

}
