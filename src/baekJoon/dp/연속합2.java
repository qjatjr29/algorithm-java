package baekJoon.dp;

import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 연속합2 {

  private static int[] numbers, rightDp, leftDp;
  private static int n, answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    numbers = new int[n];
    rightDp = new int[n];
    leftDp = new int[n];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }
    rightDp[0] = numbers[0];
    leftDp[n - 1] = numbers[n - 1];
    answer = rightDp[0];

    for(int i = 1; i < n; i++) {
      rightDp[i] = max(rightDp[i - 1] + numbers[i], numbers[i]);
      answer = max(answer, rightDp[i]);
    }

    for(int i = n - 2; i >= 0; i--) {
      leftDp[i] = max(leftDp[i + 1] + numbers[i], numbers[i]);
    }

    for(int i = 1; i < n - 1; i++) {
      answer = max(answer, rightDp[i - 1] + leftDp[i + 1]);
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

}
