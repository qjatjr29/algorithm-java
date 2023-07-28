package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열5 {

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int size = Integer.parseInt(st.nextToken());
    int[] inputs = new int[size];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < size; i++) {
      int number = Integer.parseInt(st.nextToken());
      inputs[i] = number;
    }

    int[] answer = lis(inputs);

    bw.write(String.valueOf(answer.length));
    bw.newLine();
    for(int ans : answer) {
      bw.write(String.valueOf(ans) + " ");
    }
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static int[] lis(int[] arr) {
    int[] indexes = new int[arr.length];
    int[] dp = new int[arr.length];

    dp[0] = arr[0];
    indexes[0] = 0;

    int size = 1;

    for(int i = 1; i < arr.length; i++) {

      // 현재 증가하는 부분 수열의 가장 큰 수보다 arr[i] 값이 더 큰 경우
      if(dp[size - 1] < arr[i]) {
        dp[size] = arr[i];
        size++;
        // 해당 i번째 수의 위치는 가장 뒤임.
        indexes[i] = size - 1;
      }
      else {
        int pos = Arrays.binarySearch(dp, 0, size, arr[i]);
        if(pos < 0) pos = -pos - 1;
        // 해당 i 번째 수의 위치는 pos
        indexes[i] = pos;
        // pos의 값을 갱신
        dp[pos] = arr[i];
      }
    }

    List<Integer> rtn = new ArrayList<>();

    // 해당 수의 인덱스값이 dp 배열에 저장된 마지막 값과 같다면 해당 수는
    // 증가하는 수열에 포함되는 수이다.
    for(int i = arr.length - 1; i >= 0; i--) {
      if(indexes[i] == size - 1) {
        size--;
        rtn.add(arr[i]);
      }
    }

    Collections.reverse(rtn);
    return rtn.stream().mapToInt(Integer::intValue).toArray();
  }

}
