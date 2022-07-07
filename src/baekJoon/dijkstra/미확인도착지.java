package baekJoon.dijkstra;

import static java.lang.Math.min;
import static java.util.Collections.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 미확인도착지 {

  private static int n, m , t;
  private static int s, g, h;
  private static List<Edge>[] adj;
  private static int[] dp;
  private static boolean[] visited;


  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int caseSize = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < caseSize; testcase++) {

      st = new StringTokenizer(br.readLine());

      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      t = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());

      s = Integer.parseInt(st.nextToken());
      g = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());

      adj = new List[n + 1];
      List<Integer> answer = new ArrayList<>();
      dp = new int[n + 1];

      for(int i = 0 ; i <= n; i++) {
        adj[i] = new ArrayList<>();
        dp[i] = Integer.MAX_VALUE;
      }

      for(int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        if((a == g && b == h) || (a == h && b == g)) {
          adj[a].add(new Edge(b, d * 2 - 1));
          adj[b].add(new Edge(a, d * 2 - 1));
        }
        else {
          adj[a].add(new Edge(b, d * 2));
          adj[b].add(new Edge(a, d * 2));
        }
      }

      dijkstra();
      for(int i = 0; i < t; i++) {
        st = new StringTokenizer(br.readLine());

        int dest = Integer.parseInt(st.nextToken());
        if(dp[dest] % 2 == 1 && dp[dest] != Integer.MAX_VALUE) {
          answer.add(dest);
        }
      }

      sort(answer);

      for(int i = 0; i < answer.size(); i++) {
        bw.write(String.valueOf(answer.get(i)));
        bw.write(" ");
      }
      bw.newLine();
    }
    br.close();
    bw.flush();
    bw.close();
  }

  private static class Edge implements Comparable<Edge> {
    int node, dist;

    public Edge(int node, int dist) {
      this.node = node;
      this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
      return dist - o.dist;
    }
  }

  private static void dijkstra() {
    visited = new boolean[n + 1];
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.add(new Edge(s, 0));
    dp[s] = 0;

    while(true) {
      if(pq.isEmpty()) break;
      Edge here = pq.poll();
      int current = here.node;

      if(visited[current]) continue;
      visited[current] = true;

      for(int i = 0; i < adj[current].size(); i++) {
        int next = adj[current].get(i).node;
        int cost = adj[current].get(i).dist;
        if(visited[next]) continue;
        if(dp[next] > dp[current] + cost) {
          dp[next] = dp[current] + cost;
          pq.add(new Edge(next, dp[next]));
        }
      }

    }
  }

}
