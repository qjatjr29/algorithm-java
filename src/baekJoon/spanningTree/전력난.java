package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6497
public class 전력난 {

  private static int[] root;
  private static boolean[][] checked;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while(true) {

      StringTokenizer st = new StringTokenizer(br.readLine());

      int house = Integer.parseInt(st.nextToken());
      int roadCount = Integer.parseInt(st.nextToken());

      if(house == 0 && roadCount == 0) break;

      root = new int[house];
      Road[] roads = new Road[roadCount];
      checked = new boolean[house][house];
      int cost = 0;

      for(int i = 0; i < house; i++) {
        root[i] = i;
      }

      for(int i = 0; i < roadCount; i++) {
        st = new StringTokenizer(br.readLine());

        int house1 = Integer.parseInt(st.nextToken());
        int house2 = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        cost += length;
        roads[i] = new Road(house1, house2, length);
      }

      Arrays.sort(roads, (r1, r2) -> r1.length - r2.length);

      for(int i = 0; i < roads.length; i++) {

        Road road = roads[i];
        int h1 = road.house1;
        int h2 = road.house2;
        int length = road.length;

        if(isCycle(h1, h2)) continue;
        if(checked[h1][h2]) continue;
        union(h1, h2);
        cost -= length;

      }
      bw.write(String.valueOf(cost));
      bw.newLine();
    }
    bw.flush();
    bw.close();

  }

  private static class Road {
    int house1, house2;
    int length;
    Road(int house1, int house2, int length) {
      this.house1 = house1;
      this.house2 = house2;
      this.length = length;
    }
  }

  private static boolean isCycle(int x, int y) {
    x = find(x);
    y = find(y);
    return x == y;
  }

  private static int find(int x) {
    if(root[x] == x) return x;
    return root[x] = find(root[x]);
  }

  private static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if(x != y) root[x] = y;
  }

}
