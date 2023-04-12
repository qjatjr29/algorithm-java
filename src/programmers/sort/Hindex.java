package programmers.sort;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42747
public class Hindex {

  private static int answer;
  public int solution(int[] citations) {
    answer = 0;

    Arrays.sort(citations);

    hIndex(citations);

    return answer;
  }

  private void hIndex(int[] citations) {

    int target = 0;
    int size = citations.length;

    while(true) {
      int index = bs(target, 0, size - 1, citations);

      int same = 0;
      for(int i = index; i < size - 1; i++) {
        if(citations[i] > target) break;
        same++;
      }
      // h 번 인용된 논문이 h 편 이상
      if(size - index >= target) {
        // 나머지 논문이 h번 이하 인용되었을 경우 값 갱신.
        if(index + same <= target) answer = target;
      }
      else break;
      target++;
    }

  }

  // target 이 나오는 첫 번째 인덱스 추출
  private int bs(int target, int left, int right, int[] citations) {

    if(left == right) return left;

    int mid = (left + right) / 2;

    int citation = citations[mid];

    if(citation >= target) return bs(target, left, mid, citations);
    else return bs(target, mid + 1, right, citations);
  }


}
