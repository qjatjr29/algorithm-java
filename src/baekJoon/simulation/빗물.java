package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14719
public class 빗물 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());
    int answer = 0;

    int[] blocks = new int[W];

    // 자신보다 왼쪽에서 처음 만나는 자신과 같거나 자신보다 높은 낮은 높이
    int[] leftDp = new int[W + 2];

    // 자신의 오른쪽에서 자신과 같거나 자신보다 높은 높이
    int[] rightDp = new int[W + 2];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < W; i++) {
      blocks[i] = Integer.parseInt(st.nextToken());
      leftDp[i] = blocks[i];
      rightDp[i] = blocks[i];
    }

    for(int i = 1; i < W - 1; i++) {
      if(leftDp[i] <= leftDp[i - 1]) leftDp[i] = leftDp[i - 1];
    }

    for(int i = W - 1; i > 0; i--) {
      if(rightDp[i] >= rightDp[i - 1]) rightDp[i - 1] = rightDp[i];
    }

    for(int i = 1; i < W - 1; i++) {
      int height = Math.min(leftDp[i], rightDp[i]);
      if(height > blocks[i]) answer += height - blocks[i];
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
}
