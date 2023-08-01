package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 불우이웃돕기 {

  private static Map<Character, Integer> LANLine;
  private static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    setMap();

    int rooms = Integer.parseInt(st.nextToken());
    root = new int[rooms + 1];
    for(int i = 0; i <= rooms; i++) root[i] = i;
    int total = 0;
    List<Connection> connections = new ArrayList<>();

    for(int i = 1; i <= rooms; i++) {
      st = new StringTokenizer(br.readLine());
      String connectionInfo = st.nextToken();

      for(int j = 1; j <= connectionInfo.length(); j++) {
        char state = connectionInfo.charAt(j - 1);
        int length = LANLine.getOrDefault(state, 0);
        total += length;
        connections.add(new Connection(i, j, length));
      }
    }
    Collections.sort(connections);

    int cycleCount = 1;

    for(int i = 0; i < connections.size(); i++) {
      Connection connection = connections.get(i);
      if(connection.length == 0) continue;
      if(isCycle(connection.computerA, connection.computerB)) continue;
      cycleCount++;
//      answer += connection.length;
      total -= connection.length;
      union(connection.computerA, connection.computerB);
    }

    if(cycleCount != rooms) total = -1;

    bw.write(String.valueOf(total));
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
    if(x != y) root[y] = x;
  }

  private static boolean isCycle(int x, int y) {
    x = find(x);
    y = find(y);
    if(x == y) return true;
    return false;
  }

  private static void setMap() {
    LANLine = new HashMap<>();
    for(int i = 1; i <= 26; i++) {
      LANLine.put((char) (96 + i), i);
    }
    for(int i = 27; i <= 52; i++) {
      LANLine.put((char) (38 + i), i);
    }
  }

  private static class Connection implements Comparable<Connection> {
    int computerA, computerB;
    int length;

    public Connection(int computerA, int computerB, int length) {
      this.computerA = computerA;
      this.computerB = computerB;
      this.length = length;
    }

    @Override
    public int compareTo(Connection o) {
      return this.length - o.length;
    }
  }

}
