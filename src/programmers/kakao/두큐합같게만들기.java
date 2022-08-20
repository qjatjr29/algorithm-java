package programmers.kakao;

import java.util.ArrayList;
import java.util.List;

public class 두큐합같게만들기 {

  public static int solution(int[] queue1, int[] queue2) {
    int answer = -2;
    long sum1 = 0;
    long sum2 = 0;
    long size = queue1.length + queue2.length;

    List<Integer> sq = new ArrayList<>();

    for(int i = 0; i < queue1.length; i++) {
      sq.add(queue1[i]);
      sum1 += queue1[i];
    }

    for(int i = 0; i < queue2.length; i++) {
      sq.add(queue2[i]);
      sum2 += queue2[i];
    }

    for(int i = 0; i < queue1.length; i++) {
      sq.add(queue1[i]);
    }

    int qPointer1 = 0;
    int qPointer2 = queue1.length;
    int count = 0;

    if((sum1 + sum2) % 2 != 0) answer = -1;
    else {
      long target = (sum1 + sum2) / 2;
      while(true) {
        if(sum1 == target) break;
        if(qPointer1 >= size) {
          answer = -1;
          break;
        }
        if(qPointer2 >= size + queue1.length) {
          answer = -1;
          break;
        }

        if(sum1 > sum2) {
          Integer number = sq.get(qPointer1);
          sum1 -= number;
          sum2 += number;
          qPointer1++;
        }
        else {
          Integer number = sq.get(qPointer2);
          sum2 -= number;
          sum1 += number;
          qPointer2++;
        }
        count++;
      }

    }

    if(answer != -1) {
      answer = count;
    }
    return answer;
  }

  public static void main(String[] args) {

//    int[] queue1 = {3, 2, 7, 2};
//    int[] queue2 = {4, 6, 5, 1};

//    int[] queue1 = {1, 2, 1, 2};
//    int[] queue2 = {1, 10, 1, 2};

    int[] queue1 = {1, 1};
    int[] queue2 = {1, 5};

    int solution = solution(queue1, queue2);
    System.out.println(solution);

  }
}
