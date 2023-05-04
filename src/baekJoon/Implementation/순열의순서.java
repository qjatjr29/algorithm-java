package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 순열의순서 {

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    long dp[] = new long[N + 1];

    dp[0] = 1;
    for(int i = 1; i < dp.length; i++) {
      dp[i] = dp[i - 1] * i;
    }

    st = new StringTokenizer(br.readLine());

    int command = Integer.parseInt(st.nextToken());
    // 숫자를 사용했는지 안했는지 확인하는 부울 배열
    boolean[] visited = new boolean[N + 1];

    if(command == 1) {

      // 구하고자하는 순열배열의 인덱스
      long index = Long.parseLong(st.nextToken());

      List<Integer> answerList = new ArrayList<>();

      // 자리 수
      for(int i = 1; i <= N; i++) {
        // 숫자
        for(int j = 1; j <= N; j++) {
          // 해당 숫자를 이미 사용한 경우
          if (visited[j]) continue;

          // 해당 숫자를 사용했을 때 구하고자 하는 index 값보다 더 작은 경우
          if (dp[N - i] < index) {
            index -= dp[N - i];
          }
          // 넘치는 경우 다음 자리수로 넘어갸야 한다.
          else {
            answerList.add(j);
            visited[j] = true;
            break;
          }
        }
      }

      for(int answer : answerList) {
        bw.write(answer + " ");
      }
      bw.newLine();

    } else {
      int[] numbers = new int[N + 1];
      long answer = 1;

      for(int i = 1; i <= N; i++) {
        numbers[i] = Integer.parseInt(st.nextToken());
      }

      // 자리수
      for(int i = 1; i <= N; i++) {
        // 현재 자리에 있는 숫자
        for(int j = 1; j < numbers[i]; j++) {
          if(visited[j]) continue;
          answer += dp[N - i];
        }
        visited[numbers[i]] = true;
      }
      bw.write(String.valueOf(answer));
      bw.newLine();
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
