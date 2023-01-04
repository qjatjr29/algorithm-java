package baekJoon.binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 부분수열의합2 {

  private static long[] numbers;
  private static List<Long> leftSum;
  private static List<Long> rightSum;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    long answer = 0;
    int N = Integer.parseInt(st.nextToken());
    int target = Integer.parseInt(st.nextToken());
    numbers = new long[N];
    leftSum = new ArrayList<>();
    rightSum = new ArrayList<>();

    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < N; i++) {
      String numberStr = st.nextToken();
      numbers[i] = Integer.parseInt(numberStr);
    }

//    Arrays.sort(numbers);

    // 왼쪽, 오른쪽의 모든 부분집합의 합들을 구함.
    getSubSet(0, N / 2, 0, leftSum);
    getSubSet(N / 2, N, 0, rightSum);

    Collections.sort(leftSum);
    Collections.sort(rightSum);

    // 투포인터를 이용해서 더했을 때 target과 같은 값을 찾는다.
    answer = twoPointer(target);

    // 공집합 + 공집합 = 0 (예외)
    if(target == 0) answer--;

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
  private static void getSubSet(int index, int end, long sum, List<Long> list) {

    if(index == end) {
      list.add(sum);
      return;
    }
    // 더한 경우
    getSubSet(index + 1, end, sum + numbers[index], list);
    // 더하지않은 경우
    getSubSet(index + 1, end, sum, list);
  }

  private static long twoPointer(int target) {

    int leftPointer = 0;
    int rightPointer = rightSum.size() - 1;
    long count = 0;

    while (true) {
      if(leftPointer >= leftSum.size() || rightPointer < 0) break;

      long sum = leftSum.get(leftPointer) + rightSum.get(rightPointer);

      if(sum == target) {
        // 왼쪽, 오른쪽의 값과 같은 것이 더 있을 수 있다.
        long lcnt = 0;
        long rcnt = 0;

        long leftNum = leftSum.get(leftPointer);
        long rightNum = rightSum.get(rightPointer);

        while(true) {
          if(leftPointer >= leftSum.size() || leftSum.get(leftPointer) != leftNum) break;

          leftPointer++;
          lcnt++;
        }

        while(true) {
          if(rightPointer < 0 || rightSum.get(rightPointer) != rightNum) break;

          rightPointer--;
          rcnt++;
        }

        count += lcnt * rcnt;
      }
      else if(sum < target) {
        leftPointer++;
      }
      else rightPointer--;
    }

    return count;
  }

}
