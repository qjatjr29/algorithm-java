package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11497
public class 통나무건너뛰기 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < T; testcase++) {

      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());

      int[] trees = new int[size];
      st = new StringTokenizer(br.readLine());

      for(int i = 0; i < size; i++) {
        trees[i] = Integer.parseInt(st.nextToken());
      }

      Arrays.sort(trees);
      int answer = makeArray(trees);

      bw.write(String.valueOf(answer));
      bw.newLine();
    }

    bw.flush();
    bw.close();
    br.close();
  }

  private static int makeArray(int[] trees) {

    int length = trees.length;
    int height = trees[1] - trees[0];

    for(int i = 2; i < length; i++) {
      if(i == length - 1) {
        height = Math.max(height, trees[i] - trees[i - 1]);
        height = Math.max(height, trees[i] - trees[i - 2]);
      }
      else height = Math.max(height, trees[i] - trees[i - 2]);
    }

    return height;
  }
}
