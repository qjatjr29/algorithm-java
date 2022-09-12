package baekJoon.divideConquer;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 놀이공원 {

  private static int N, M;
  private static int[] amusements;
  private static long answer;


  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    amusements = new int[M];
    answer = -1;

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < M; i++) {
      amusements[i] = Integer.parseInt(st.nextToken());
    }

    if(N <= M) {
      bw.write(String.valueOf(N));
    }

    else {
      divideConquer();
      long time = answer - 1;
      long child = M;

      for(int i = 0; i < M; i++) {
        child += time / amusements[i];
      }

      int leftNumber = N - (int) child;
      int cnt = 0;
      int iter = 0;

      while(true) {
        if(((time + 1) / amusements[iter]) != (time / amusements[iter])) cnt++;
        iter++;
        if(cnt == leftNumber) break;
      }
      bw.write(String.valueOf(iter));
    }

    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void divideConquer() {
    long left = 0;
    long right = 2000000000l * 30l;

    while(true) {
      if(left > right) break;

      long mid = (left + right) / 2;
      long child = M;

      for(int i = 0 ; i < amusements.length; i++) {
        child += mid / amusements[i];
      }
      if(child >= N) {
        answer = mid;
        right = mid - 1;
      }
      else {
        left = mid + 1;
      }
    }
  }

}
