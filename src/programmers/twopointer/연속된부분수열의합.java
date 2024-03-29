package programmers.twopointer;

public class 연속된부분수열의합 {
  public int[] solution(int[] sequence, int k) {
    int[] answer = new int[2];

    int size = sequence.length;

    int length = size;

    int left = 0;
    int right = 0;
    // 처음 시작 부분수열의 합
    int sum = sequence[0];

    while(true) {

      if(sum == k) {
        int gap = right - left;
        if(length > gap) {
          length = gap;
          answer[0] = left;
          answer[1] = right;
        }
      }

      // 모든 경우를 찾은 경우
      if(left == size && right == size) break;

      // 부분수열의 합이 k보다 작은 경우
      // 부분 수열을 늘린다.
      if(sum <= k && right < size) {
        // 마지막 요소가 아닌 경우 => 오른쪽으로 이동
        right++;
        // 이동한 값이 크기를 넘어가지 않았다면 해당 값을 부분수열에 추가.
        if(right < size) {
          sum += sequence[right];
        }
      }

      // 부분수열의 합이 k보다 큰 경우
      else {
        if(left < size) sum -= sequence[left];
        left++;
      }
    }

    return answer;
  }
}
