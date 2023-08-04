package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.StringTokenizer;

public class 여러분의다리가되어드리겠습니다 {

  private static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int islands = Integer.parseInt(st.nextToken());

    root = new int[islands + 1];
    int[] answer = new int[2];

    for(int i = 1; i <= islands; i++) root[i] = i;

    for(int i = 0; i < islands - 2; i++) {
      st = new StringTokenizer(br.readLine());
      int island1 = Integer.parseInt(st.nextToken());
      int island2 = Integer.parseInt(st.nextToken());

      if(isCycle(island1, island2)) continue;
      union(island1, island2);
    }

    for(int i = 2; i <= islands; i++) {
      if(find(i - 1) != find(i)) {
        answer[0] = i - 1;
        answer[1] = i;
        break;
      }
    }

    for(int ans : answer) bw.write(String.valueOf(ans) + " ");
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
    return x == y;
  }
}
