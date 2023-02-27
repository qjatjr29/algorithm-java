package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/10942
public class 팰린드롬 {

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    boolean[][] isPalindrome = new boolean[N + 1][N + 1];
    int[] numbers = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for(int i = 1; i <= N; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
      isPalindrome[i][i] = true;
      for(int j = 1; j < i; j++) {
        if(j + 1 == i) isPalindrome[i][j] = (numbers[i] == numbers[j]);
        isPalindrome[j][i] = (isPalindrome[j + 1][i - 1] && numbers[i] == numbers[j]);
      }
    }

    st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      if(isPalindrome[start][end]) bw.write("1 \n");
      else bw.write("0 \n");
    }
    bw.flush();
    bw.close();
    br.close();
  }

}
