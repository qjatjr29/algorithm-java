package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21758

public class 꿀따기 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int answer = 0;

    // 해당 위치에서의 꿀양
    int[] honeys = new int[N];

    // 0 부터 시작해 N - 1 까지의 꿀 누적합
    int[] honeySumFromLeft = new int[N];

    // N - 1 부터 시작해 0 까지의 꿀 누적합
    int[] honeySumFromRight = new int[N];

    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < N; i++) {
      honeys[i] = Integer.parseInt(st.nextToken());
      if(i == 0) honeySumFromLeft[i] = honeys[i];
      else honeySumFromLeft[i] = honeySumFromLeft[i - 1] + honeys[i];
    }

    for(int i = N - 1; i >= 0; i--) {
      if(i == N - 1) honeySumFromRight[i] = honeys[i];
      else honeySumFromRight[i] = honeySumFromRight[i + 1] + honeys[i];
    }

    int bee1;
    int bee2;

    // 벌꿀통이 맨 끝에 있는 경우 - 벌 하나는 반대 끝에 있어야 최대가 된다.
    // 벌이 맨 왼쪽, 그리고 i 번째에 있는 경우 ( 벌통은 맨 오른쪽 )
    for(int i = 1; i < N - 1; i++) {
      bee1 = honeySumFromLeft[N - 1] - (honeys[0] + honeys[i]);
      bee2 = honeySumFromLeft[N - 1] - honeySumFromLeft[i];
      answer = Math.max(answer, bee1 + bee2);
    }

    // 벌이 맨 오른쪽, 그리고 i 번째에 있는 경우 ( 벌통은 맨 왼쪽 )
    for(int i = N - 2; i > 0; i--) {
      bee1 = honeySumFromRight[0] - (honeys[N - 1] + honeys[i]);
      bee2 = honeySumFromRight[0] - honeySumFromRight[i];
      answer = Math.max(answer, bee1 + bee2);
    }

    // 벌꿀통이 가운데 있는 경우 - 벌들이 양 끝에 있어야 최대가 된다.
    for(int i = 1; i < N - 1; i++) {
      bee1 = honeySumFromLeft[i] - honeys[0];
      bee2 = honeySumFromRight[i] - honeys[N - 1];
      answer = Math.max(answer, bee1 + bee2);
    }
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
