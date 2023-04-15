package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2467
public class 용액 {

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    long[] numbers = new long[N];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      numbers[i] = Long.parseLong(st.nextToken());
    }

    Arrays.sort(numbers);

    int l_point = 0;
    int r_point = N - 1;
    int l_result = 0;
    int r_result = 0;

    long answer = 2000000000;
    while(true) {

      if(l_point >= r_point) break;

      long left = numbers[l_point];
      long right = numbers[r_point];

      if(Math.abs(left + right) < answer) {
        answer = Math.abs(left + right);
        l_result = l_point;
        r_result = r_point;
      }

      if(left + right < 0) {
        l_point++;
      }
      else r_point--;

    }

    bw.write(String.valueOf(numbers[l_result] + " " + numbers[r_result]));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

}
