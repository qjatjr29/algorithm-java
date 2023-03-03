package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12015
public class 가장긴증가하는부분수열2 {

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    int[] numbers = new int[N];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    // 증가하는 부분 배열
    int[] dp = new int[N];
    // dp 배열에 들어간 element 수
    int len = 0;

    for(int i = 0; i < N; i++) {
      // dp 배열에서 numbers[i] 값이 들어갈 자리를 찾는다.
      int pos = Arrays.binarySearch(dp, 0, len, numbers[i]);

      // 이분탐색에서 찾지 못한 경우, 음수값으로 반환되기 때문에
      if(pos < 0) pos = -pos - 1;

      dp[pos] = numbers[i];

      // 길이가 증가하는 경우에만 최대 길이를 갱신
      if(pos == len) len++;
    }

    bw.write(String.valueOf(len));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
