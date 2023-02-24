package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1700
public class 멀티탭스케줄링 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] order = new int[K];

    // 가전제품 번호 : key
    // 해당 가전제품을 사용하는 순서들(배열) : value
    Map<Integer, List<Integer>> applianceUses = new HashMap<>();

    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < K; i++) {
      order[i] =  Integer.parseInt(st.nextToken());
    }


    // 현재 멀티탭에 꽂혀있는지 확인
    boolean[] use = new boolean[101];
    int answer = 0;

    // 1. 현재 멀티탭에 꽂혀 있는 전기용품이라면 넘어간다.
    // 2. 꽂혀있지 않은 전기용품인 경우 멀티탭의 구멍이 남아있다면 꽂는다.
    // 3. 다 꽂혀 있다면 현재 꽂혀 있는 전기용품이 나중에 다시 사용되는지 확인한다.
    // 4. 사용되지 않는 전기용품이 있다면 그 제품과 바꾼다.
    // 5. 다 사용한다면 가장 늦게 사용되는 전기용품과 바꾼다.

    // 현재 사용중인 전기용품 번호
    List<Integer> numberInUse = new ArrayList<>();

    int count = 0;

    for(int i = 0; i < K; i++) {
      int appliance = order[i];

      // (1)
      if(use[appliance]) continue;

      // (2)
      if(count < N) {
        use[appliance] = true;
        count++;
        continue;
      }

      List<Integer> nextSockets = new ArrayList<>();
      // (3) 지금부터 끝까지 현재 사용중인 전기용품을 또 사용하는지 확인
      for(int j = i; j < K; j++) {
        // 다음에 사용하는 경우
        if(use[order[j]] && !nextSockets.contains(order[j])) nextSockets.add(order[j]);
      }
      // (4)
      // 현재 꽂혀있는 모든 전기용품이 나중에 사용되지는 않는 경우
      // 꽂혀있는 전기용품 중 나중에 사용되지 않는 전기용품을 찾아 뽑는다
      if(nextSockets.size() != N) {
        for(int j = 0; j < 101; j++) {
          if(use[j] && !nextSockets.contains(j)) {
            use[j] = false;
            break;
          }
        }
      }

      // (5)
      // 현재 꽂혀있는 모든 전기용품이 나중에 다 사용되는 경우
      // 가장 늦게 사용되는 전기용품를 뽑는다.
      else {
        int removeNumber = nextSockets.get(nextSockets.size() - 1);
        use[removeNumber] = false;
      }

      // 1, 2번 경우가 아니라면 무조건 뽑고 새로 꽂아야 한다.
      answer++;
      use[appliance] = true;
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
}
