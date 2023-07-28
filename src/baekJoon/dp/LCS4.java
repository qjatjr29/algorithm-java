package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LCS4 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int size = Integer.parseInt(st.nextToken());

    int[] array1 = new int[size + 1];
    int[] array2 = new int[size + 1];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < size; i++) {
      array1[Integer.parseInt(st.nextToken())] = i;
    }

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < size; i++) {
      array2[i] = array1[Integer.parseInt(st.nextToken())];
    }

    int answer = lcs(array2);
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static int lcs(int[] arr2) {

    int[] dp = new int[arr2.length + 1];

    int rtn = 0;

    for(int i = 0; i < arr2.length; i++) {

      int pos = Arrays.binarySearch(dp, 0, rtn, arr2[i]);

      if(pos < 0) pos = -pos - 1;

      dp[pos] = arr2[i];

      if(pos == rtn) rtn++;
    }

    return rtn;
  }

}
