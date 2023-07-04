package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 도시건설 {

  private static final int NOT_CONNECT = -1;
  private static int[] root;
  private static long total;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int buildings = Integer.parseInt(st.nextToken());
    int roads = Integer.parseInt(st.nextToken());

    setRoot(buildings);
    Road[] roadList = new Road[roads];

    for(int i = 0; i < roads; i++) {
      st = new StringTokenizer(br.readLine());
      int building1 = Integer.parseInt(st.nextToken());
      int building2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      total += cost;
      roadList[i] = new Road(building1, building2, cost);
    }

    Arrays.sort(roadList);

    for(int i = 0; i < roads; i++) {
      int building1 = roadList[i].building1;
      int building2 = roadList[i].building2;
      int cost = roadList[i].cost;

      if(isCycle(building1, building2)) continue;

      union(building1, building2);
      total -= cost;
    }

    if(isConnect(buildings)) bw.write(String.valueOf(total));
    else bw.write(String.valueOf(NOT_CONNECT));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void setRoot(int count) {
    root = new int[count + 1];
    for(int i = 0; i <= count; i++) {
      root[i] = i;
    }
  }

  private static int find(int x) {
    if(root[x] == x) return x;
    else return root[x] = find(root[x]);
  }

  private static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if(x != y) root[y] = x;
  }

  private static boolean isCycle(int x, int y) {
    x = find(x);
    y = find(y);
    if(x == y) return true;
    return false;
  }

  private static boolean isConnect(int count) {

    int rootNum = find(1);

    for(int i = 2; i <= count; i++) {
      if(rootNum != find(i)) return false;
    }
    return true;
  }

  private static class Road implements Comparable<Road> {
    int building1, building2, cost;

    public Road(int building1, int building2, int cost) {
      this.building1 = building1;
      this.building2 = building2;
      this.cost = cost;
    }

    @Override
    public int compareTo(Road o) {
      return this.cost - o.cost;
    }
  }

}
