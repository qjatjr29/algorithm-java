package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 전기가부족해 {

  private static int[] root;
  private static Cable[] cables;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    root = new int[N + 1];
    cables = new Cable[M];

    for(int i = 1; i <= N; i++) {
      root[i] = i;
    }

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < K; i++) {
      int plant = Integer.parseInt(st.nextToken());
      root[plant] = -1;
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int city1 = Integer.parseInt(st.nextToken());
      int city2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      cables[i] = new Cable(city1, city2, cost);
    }

    Arrays.sort(cables);

    int answer = 0;
    for(int i = 0; i < cables.length; i++) {

      int city1 = cables[i].x;
      int city2 = cables[i].y;
      int cost = cables[i].cost;

      if(isCycle(city1, city2)) continue;

      union(city1, city2);
      answer += cost;
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();


  }

  private static int find(int x) {
    if(root[x] == -1) return -1;
    if(root[x] == x) return x;
    return root[x] = find(root[x]);
  }

  private static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if(x != y) {
      if(x == -1 && y == -1) return;
      else if(x == -1) {
        root[y] = x;
        return;
      }
      else if(y == -1) {
        root[x] = y;
        return;
      }
      root[y] = x;
    }
  }

  private static boolean isCycle(int x, int y) {
    x = find(x);
    y = find(y);
    return x == y;
  }

  private static class Cable implements Comparable<Cable> {
    int x, y, cost;

    public Cable(int x, int y, int cost) {
      this.x = x;
      this.y = y;
      this.cost = cost;
    }

    @Override
    public int compareTo(Cable o) {
      return this.cost - o.cost;
    }
  }

}
