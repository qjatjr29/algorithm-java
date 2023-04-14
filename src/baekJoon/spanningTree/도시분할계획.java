package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1647
public class 도시분할계획 {

  private static int[] root;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int answer = 0;
    root = new int[N + 1];
    Route[] routes = new Route[M];

    for(int i = 1; i <= N; i++) {
      root[i] = i;
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int houseA = Integer.parseInt(st.nextToken());
      int houseB = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      routes[i] = new Route(houseA, houseB, cost);
    }

    Arrays.sort(routes, (h1, h2) -> {
      return h1.cost - h2.cost;
    });

    int maxCost = 0;
    for(int i = 0; i < M; i++) {
      Route route = routes[i];

      int h1 = route.houseA;
      int h2 = route.houseB;

      if(isCycle(h1, h2)) continue;

      union(h1, h2);
      maxCost = Math.max(maxCost, route.cost);
      answer += route.cost;
    }

    answer -= maxCost;

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Route {
    int houseA, houseB;
    int cost;

    Route(int houseA, int houseB, int cost) {
      this.houseA = houseA;
      this.houseB = houseB;
      this.cost = cost;
    }
  }

  private static boolean isCycle(int x, int y) {
    x = find(x);
    y = find(y);
    if(x == y) return true;
    return false;
  }

  private static int find(int x) {
    if(root[x] == x) return x;
    return root[x] = find(root[x]);
  }

  private static void union(int x, int y) {
    x = find(x);
    y = find(y);

    if(x != y) root[y] = x;
  }

}
