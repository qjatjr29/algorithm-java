package baekJoon.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 합구하기 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    long[] sum = new long[N + 1];

    st = new StringTokenizer(br.readLine());
    for(int i = 1; i <= N; i++) {
      int number = Integer.parseInt(st.nextToken());
      sum[i] = sum[i - 1] + number;
    }

    st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      long result = sum[end] - sum[start - 1];
      bw.write(String.valueOf(result));
      bw.newLine();
    }

    bw.flush();
    bw.close();
    br.close();
  }

}
