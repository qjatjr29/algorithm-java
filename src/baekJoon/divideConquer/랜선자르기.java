package baekJoon.divideConquer;

import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 랜선자르기 {

  private static int N, K;
  private static long maxNum, answer;
  private static int[] lines;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    K = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());

    lines = new int[K];

    for(int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      lines[i] = Integer.parseInt(st.nextToken());
      maxNum = max(lines[i], maxNum);
    }

    maxNum++;

    dc();

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void dc() {

    long min = 0;
    long mid = 0;

    while(min < maxNum) {

      mid = (maxNum + min) / 2;

      long count = 0;

      for(int i = 0; i < K; i++) {
        count += (lines[i] / mid);
      }

      if(count < N) {
        maxNum = mid;
      }
      else min = mid + 1;
    }
    answer = min - 1;
  }
}
