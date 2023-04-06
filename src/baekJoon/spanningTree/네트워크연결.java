package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 네트워크연결 {

  private static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int computers = Integer.parseInt(st.nextToken());
    int answer = 0;

    st = new StringTokenizer(br.readLine());
    int lines = Integer.parseInt(st.nextToken());

    Connection[] connections = new Connection[lines];
    boolean[][] visited = new boolean[computers + 1][computers + 1];
    root = new int[computers + 1];

    for(int i = 1; i <= computers; i++) {
      root[i] = i;
    }

    for(int i = 0; i < lines; i++) {
      st = new StringTokenizer(br.readLine());
      int computerA = Integer.parseInt(st.nextToken());
      int computerB = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      connections[i] = new Connection(computerA, computerB, cost);
    }

    Arrays.sort(connections, new Comparator<Connection>() {
      @Override
      public int compare(Connection o1, Connection o2) {
        return o1.cost - o2.cost;
      }
    });

    while(true) {

      boolean check = false;

      for(int i = 0; i < connections.length; i++) {

        int ca = connections[i].computerA;
        int cb = connections[i].computerB;
        int cost = connections[i].cost;

        if(!visited[ca][cb]) {
          // cycle 확인
          if(!isCycle(ca, cb)) {
            visited[ca][cb] = true;
            union(ca, cb);
            answer += cost;
            check = true;
          }
        }
      }
      if(!check) break;
    }
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();

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

  private static class Connection {
    int computerA, computerB;
    int cost;

    public Connection(int computerA, int computerB, int cost) {
      this.computerA = computerA;
      this.computerB = computerB;
      this.cost = cost;
    }
  }
}
