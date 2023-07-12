package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 친구비 {

  private static final String IMPOSSIBLE = "Oh no";
  private static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int students = Integer.parseInt(st.nextToken());
    int relation = Integer.parseInt(st.nextToken());
    int money = Integer.parseInt(st.nextToken());

    root = new int[students + 1];
    List<Relation> relations = new ArrayList<>();

    st = new StringTokenizer(br.readLine());

    for(int i = 0; i <= students; i++) {
      root[i] = i;
    }

    for(int i = 1; i <= students; i++) {
      relations.add(new Relation(0, i, Integer.parseInt(st.nextToken())));
    }

    for(int i = 0; i < relation; i++) {
      st = new StringTokenizer(br.readLine());
      int student1 = Integer.parseInt(st.nextToken());
      int student2 = Integer.parseInt(st.nextToken());

      relations.add(new Relation(student1, student2, 0));
    }

    Collections.sort(relations);

    int usedCost = 0;
    for(int i = 0; i < relations.size(); i++) {
      Relation r = relations.get(i);

      int student1 = r.student1;
      int student2 = r.student2;
      int cost = r.cost;

      if(isCycle(student1, student2)) continue;

      union(student1, student2);
      usedCost += cost;
    }

    if(usedCost > money) bw.write(IMPOSSIBLE);
    else bw.write(String.valueOf(usedCost));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static int findRoot(int x) {
    if(root[x] == x) return x;
    else return root[x] = findRoot(root[x]);
  }

  private static void union(int x, int y) {
    x = findRoot(x);
    y = findRoot(y);
    if(x != y) root[x] = y;
  }

  private static boolean isCycle(int x, int y) {
    x = findRoot(x);
    y = findRoot(y);
    if(x == y) return true;
    return false;
  }

  private static class Relation implements Comparable<Relation> {
    int student1, student2;
    int cost;

    public Relation(int student1, int student2, int cost) {
      this.student1 = student1;
      this.student2 = student2;
      this.cost = cost;
    }

    @Override
    public int compareTo(Relation o) {
      return this.cost - o.cost;
    }
  }

}
