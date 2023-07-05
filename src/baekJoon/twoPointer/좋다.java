package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 좋다 {

  private static int[] numbers;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = 0;
    int N = Integer.parseInt(st.nextToken());
    numbers = new int[N];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(numbers);

    for(int i = 0; i < numbers.length; i++) {
      int number = numbers[i];
      if(isGood(number, i, 0, N - 1)) answer++;
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static boolean isGood(int target, int targetIndex, int left, int right) {

    if(left == targetIndex) return isGood(target, targetIndex, left + 1, right);
    if(right == targetIndex) return isGood(target, targetIndex, left, right - 1);
    if(left >= right) return false;

    int leftNumber = numbers[left];
    int rightNumber = numbers[right];

    if(target == leftNumber + rightNumber) return true;

    if(target > leftNumber + rightNumber) return isGood(target, targetIndex, left + 1, right);

    else return isGood(target, targetIndex,  left, right - 1);

  }

}
