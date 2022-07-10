package baekJoon.twoPointer;

import static java.util.Arrays.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 배열합치기 {

  private static int N, M;
  private static int[] A, B;
  private static List<Integer> answer;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    A = new int[N];
    B = new int[M];
    answer = new ArrayList<>();

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      int number = Integer.parseInt(st.nextToken());
      A[i] = number;
    }

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < M; i++) {
      int number = Integer.parseInt(st.nextToken());
      B[i] = number;
    }
    sort(A);
    sort(B);

    int aPointer = 0;
    int bPointer = 0;

    while(true) {
      if(aPointer >= N) {
        for(int i = bPointer; i < M; i++) {
          answer.add(B[i]);
        }
        break;
      }
      else if(bPointer >= M) {
        for(int i = aPointer; i < N; i++) {
          answer.add(A[i]);
        }
        break;
      }

      int a = A[aPointer];
      int b = B[bPointer];

      if(a < b) {
        answer.add(a);
        aPointer++;
      } else {
        answer.add(b);
        bPointer++;
      }
    }

    for(int i = 0; i < answer.size(); i++) {
      bw.write(String.valueOf(answer.get(i)) + " ");
    }
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
