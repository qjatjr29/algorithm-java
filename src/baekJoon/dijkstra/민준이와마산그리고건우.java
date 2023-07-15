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

public class 민준이와마산그리고건우 {

  private static int V, E, GUNWOO;
  private static List<Road>[] roads;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());
    GUNWOO = Integer.parseInt(st.nextToken());

    roads = new List[V + 1];

    for(int i = 0; i <= V; i++) roads[i] = new ArrayList<Road>();

    for(int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());
      int distance = Integer.parseInt(st.nextToken());

      roads[v1].add(new Road(v2, distance));
      roads[v2].add(new Road(v1, distance));
    }

    if(bfs(1, V) == bfs(1, GUNWOO) + bfs(GUNWOO, V)) bw.write("SAVE HIM");
    else bw.write("GOOD BYE");
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static int bfs(int start, int dest) {
    if(start == dest) return 0;

    int distance = 987654321;
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(start, 0));
    boolean[] visited = new boolean[V + 1];

    while(!pq.isEmpty()) {
      Node currentNode = pq.poll();
      int here = currentNode.number;
      int cDistance = currentNode.distance;

      visited[here] = true;

      if(here == dest) {
        distance = cDistance;
        break;
      }

      for(Road road : roads[here]) {
        int next = road.destination;
        int dist = road.distance;

        if(visited[next]) continue;
        pq.add(new Node(next, cDistance + dist));
      }
    }
    return distance;
  }

  private static class Node implements Comparable<Node> {
    int number;
    int distance;

    public Node(int number, int distance) {
      this.number = number;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
      return this.distance - o.distance;
    }
  }

  private static class Road {
    int destination;
    int distance;

    public Road(int destination, int distance) {
      this.destination = destination;
      this.distance = distance;
    }
  }

}
