package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class 택배배송 {

  private static Map<Integer, Integer>[] road;
  private static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int size = Integer.parseInt(st.nextToken());
    int path = Integer.parseInt(st.nextToken());
    road = new Map[size + 1];
    visited = new boolean[size + 1];
    for(int i = 1; i <= size; i++) {
      road[i] = new HashMap<>();
    }

    for(int i = 0; i < path; i++) {
      st = new StringTokenizer(br.readLine());
      int barn1 = Integer.parseInt(st.nextToken());
      int barn2 = Integer.parseInt(st.nextToken());
      int cow = Integer.parseInt(st.nextToken());

      if(road[barn1].containsKey(barn2)) {
        // 두 헛간이 2개 이상의 길로 연결되어있을 경우 최소값으로 설정
        road[barn1].computeIfPresent(barn2, (k, v) -> v = Math.min(v, cow));
        road[barn2].computeIfPresent(barn1, (k, v) -> v = Math.min(v, cow));
      }
      else {
        road[barn1].put(barn2, cow);
        road[barn2].put(barn1, cow);
      }
    }

    PriorityQueue<Barn> pq = new PriorityQueue<>();
    pq.add(new Barn(0, 1));

    while(!pq.isEmpty()) {

      Barn barn = pq.poll();
      int c_idx = barn.number;
      int c_cost = barn.cost;
      visited[c_idx] = true;

      if(c_idx == size) {
        bw.write(String.valueOf(c_cost));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
        return;
      }

      Set<Integer> keys = road[c_idx].keySet();
      for (Integer key : keys) {
        if(visited[key]) continue;
        int n_cost = road[c_idx].get(key);
        pq.add(new Barn(c_cost + n_cost, key));
      }
    }
  }
  private static class Barn implements Comparable<Barn>{
    int cost;
    int number;

    public Barn(int cost, int number) {
      this.cost = cost;
      this.number = number;
    }

    @Override
    public int compareTo(Barn o) {
      return this.cost - o.cost;
    }
  }

}
