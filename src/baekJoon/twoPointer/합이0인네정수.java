package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0인네정수 {

  private static int[][] numbers;
  private static int[] AB, CD;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    numbers = new int[4][n];
    AB = new int[n * n];
    CD = new int[n * n];

    for(int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < 4; j++) {
        numbers[j][i] = Integer.parseInt(st.nextToken());
      }
    }
    int totalIndex = 0;

    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        AB[totalIndex] = numbers[0][i] + numbers[1][j];
        CD[totalIndex] = numbers[2][i] + numbers[3][j];
        totalIndex++;
      }
    }
    Arrays.sort(AB);
    Arrays.sort(CD);

    long answer = twoPointer(totalIndex, 0);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }


  private static long twoPointer(int size, int target) {
    long count = 0;

    int abp = 0;
    int cdp = size - 1;

    while(abp < size && cdp >= 0) {

      int sum = AB[abp] + CD[cdp];

      if(sum == target) {
        int leftSameCount = 1;
        int rightSameCount = 1;
        while(abp < size - 1 && AB[abp] == AB[abp + 1]) {
          abp++;
          leftSameCount++;
        }

        while(cdp > 0 && CD[cdp] == CD[cdp - 1]) {
          cdp--;
          rightSameCount++;
        }
        count += (long) leftSameCount * rightSameCount;
        abp++;
      }

      if(sum < target) {
        abp++;
      }

      if(sum > target) {
        cdp--;
      }

    }
    return count;
  }

}
