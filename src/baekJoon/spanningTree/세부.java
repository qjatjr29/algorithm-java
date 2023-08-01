package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 세부 {

  private static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int houses = Integer.parseInt(st.nextToken());
    int bridgeCount = Integer.parseInt(st.nextToken());
    int answer = 0;

    root = new int[houses + 1];
    for(int i = 0; i <= houses; i++) root[i] = i;

    st = new StringTokenizer(br.readLine());
    int startIsland = Integer.parseInt(st.nextToken());
    int destination = Integer.parseInt(st.nextToken());

    PriorityQueue<Bridge> queue = new PriorityQueue<>();

    for(int i = 0; i < bridgeCount; i++) {
      st = new StringTokenizer(br.readLine());
      int house1 = Integer.parseInt(st.nextToken());
      int house2 = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      queue.add(new Bridge(house1, house2, weight));
    }

    while(!queue.isEmpty()) {

      Bridge bridge = queue.poll();
      if(isCycle(bridge.house1, bridge.house2)) continue;

      union(bridge.house1, bridge.house2);

      if(isCycle(startIsland, destination)) {
        answer = bridge.weight;
        break;
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static int find(int x) {
    if(root[x] == x) return x;
    else return root[x] = find(root[x]);
  }

  private static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if(x != y) root[x] = y;
  }

  private static boolean isCycle(int x, int y) {
    x = find(x);
    y = find(y);
    if(x == y) return true;
    return false;
  }

  private static class Bridge implements Comparable<Bridge> {
    int house1;
    int house2;
    int weight;

    public Bridge(int house1, int house2, int weight) {
      this.house1 = house1;
      this.house2 = house2;
      this.weight = weight;
    }

    @Override
    public int compareTo(Bridge o) {
      return o.weight - this.weight;
    }
  }

}
