package baekJoon.divideConquer;

import static java.lang.Math.abs;
import static java.util.Arrays.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 세용액 {

  private static int N;
  private static long[] liquid;
  private static Long answerValue;
  private static long[] answer;

  public static void main(String[] args) throws IOException {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    liquid = new long[N];
    answer = new long[3];
    int[] temp = new int[3];
    answerValue = 3000000001l;

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      liquid[i] = Integer.parseInt(st.nextToken());
    }

    sort(liquid);

    for(int i = 0; i < N; i++) {
      dc(i);
    }

    for (long result : answer) {
      bw.write(result + " ");
    }
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void dc(int index) {

    int left = index + 1;
    int right = liquid.length - 1;

    while(true) {
      if(left >= right) break;

      long value = liquid[index] + liquid[left] + liquid[right];

      long chk = abs(value);

      if(chk < answerValue) {
        answerValue = chk;
        answer[0] = liquid[index];
        answer[1] = liquid[left];
        answer[2] = liquid[right];
      }
      if(value < 0) left++;
      else right--;
    }
  }

}
