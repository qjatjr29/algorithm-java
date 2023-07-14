package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 간선이어가기2 {

  private static List<Route>[] connects;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    connects = new List[n + 1];

    for(int i = 0; i <= n; i++) {
      connects[i] = new ArrayList<>();
    }

    for(int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      connects[node1].add(new Route(node2, c));
      connects[node2].add(new Route(node1, c));
    }

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    PriorityQueue<Node> pq = new PriorityQueue<>();
    boolean[] visited = new boolean[n + 1];
    int answer = 0;

    visited[start] = true;
    pq.add(new Node(start, 0));

    while(!pq.isEmpty()) {

      Node currentNode = pq.poll();
      int number = currentNode.number;
      int cost = currentNode.cost;
      List<Route> connectList = connects[number];

      if(number == end) {
        answer = cost;
        break;
      }

      visited[number] = true;

      for(int i = 0; i < connectList.size(); i++) {
        Route route = connectList.get(i);
        int next = route.nextNode;
        int nextCost = route.cost;

        if(visited[next]) continue;
        pq.add(new Node(next, cost + nextCost));
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Node implements Comparable<Node>{
    int number;
    int cost;

    public Node(int number, int cost) {
      this.number = number;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      return this.cost - o.cost;
    }
  }

  private static class Route {
    int nextNode, cost;

    public Route(int nextNode, int cost) {
      this.nextNode = nextNode;
      this.cost = cost;
    }
  }
}
