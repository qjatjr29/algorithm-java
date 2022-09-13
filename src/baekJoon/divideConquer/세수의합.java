package baekJoon.divideConquer;

import static java.util.Collections.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 세수의합 {

  private static int N;
  private static int[] u;
  private static List<Integer> sumArray;
  private static long answer;

  public static void main(String[] args) throws IOException {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    u = new int[N];
    sumArray = new ArrayList<>();

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      u[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(u);
    sumNumber();
    sort(sumArray);
    for(int i = N - 1; i > 0; i--) {
      for(int j = 0; j < i; j++) {
        if(binarySearch(0,
            sumArray.size() - 1,
            u[i] - u[j])) {
          answer = u[i];
          break;
        }
      }
      if(answer != 0) break;
    }


    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void sumNumber() {
    for(int i = 0; i < N; i++) {
      for(int j = i; j < N; j++) {
        sumArray.add(u[i] + u[j]);
      }
    }
  }

  private static boolean binarySearch(int left, int right, int target) {
    if(left > right) return false;

    int mid = (left + right) / 2;

    int value = sumArray.get(mid);

    if(value == target) return true;

    if(value > target) return binarySearch(left, mid - 1, target);

    else return binarySearch(mid + 1, right, target);

  }

}
