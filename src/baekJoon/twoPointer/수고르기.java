package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수고르기 {

  private static int answer = 2000000001;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int target = Integer.parseInt(st.nextToken());

    int[] numbers = new int[N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(numbers);
    checkGap(numbers, target);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void checkGap(int[] numbers, int target) {

    int left = 0;
    int right = 0;

    while(true) {

      if(left >= numbers.length || right >= numbers.length) break;
      int gap = numbers[right] - numbers[left];

      if(gap < target) {
        right++;
        continue;
      }

      if(gap == target) {
        answer = target;
        break;
      }

      answer = Math.min(answer, gap);
      left++;
    }
  }

//  private static void getGap(int left, int right, int target, int[] numbers) {
//    if(right >= numbers.length) return;
//    if(left >= right) return;
//    int gap = numbers[right] - numbers[left];
//    if(answer == target) return;
//    if(gap >= target) {
//      answer = Math.min(answer, gap);
//      getGap(left + 1, right, target, numbers);
//    }
//    else getGap(left, right + 1, target, numbers);
//  }
}
