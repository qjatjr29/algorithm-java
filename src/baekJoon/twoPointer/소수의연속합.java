package baekJoon.twoPointer;

import static java.lang.Math.sqrt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 소수의연속합 {

  private static int N, answer;
  private static boolean[] primes;
  private static List<Integer> numbers;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    numbers = new ArrayList<>();
    if(N < 2) {
      bw.write("0");
    }
    else {
      prime(N);

      for(int i = 0; i < primes.length; i++) {
        if(!primes[i]) numbers.add(i);
      }
      int start = 0;
      int end = 0;
      int sum = 0;

      while(true) {
        if(sum >= N) {
          sum -= numbers.get(start);
          start++;
        }
        else if(end == numbers.size()) break;
        else {
          sum += numbers.get(end);
          end++;
        }
        if(N == sum) answer++;
      }

      bw.write(String.valueOf(answer));
    }
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void prime(int N) {
    primes = new boolean[N + 1];
    primes[0] = primes[1] = true;
    for(int i = 2; i * i <= N; i++) {
      if(primes[i]) continue;
      for(int j = i * i; j <= N; j = j + i) {
        primes[j] = true;
      }
    }
  }

}
