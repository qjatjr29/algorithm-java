package programmers.graph;

import java.util.*;

public class 부대복귀 {
  private static final int MAX_REGION_COUNT = 100000;
  private static List<Integer>[] routes;
  private static int[] distances;

  public int[] solution(int n, int[][] roads, int[] sources, int destination) {

    List<Integer> answer = new ArrayList<>();
    routes = new List[MAX_REGION_COUNT + 1];
    distances = new int[MAX_REGION_COUNT + 1];


    for(int i = 1; i <= MAX_REGION_COUNT; i++) {
      routes[i] = new ArrayList<>();
      distances[i] = MAX_REGION_COUNT;
    }
    distances[destination] = 0;

    for(int[] road : roads) {
      routes[road[0]].add(road[1]);
      routes[road[1]].add(road[0]);
    }

    bfs(destination);

    for(int source : sources) {
      int distance = distances[source] == MAX_REGION_COUNT ? -1 : distances[source];
      answer.add(distance);
    }

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }
  private void bfs(int destination) {

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(destination, 0));

    boolean[] visited = new boolean[MAX_REGION_COUNT + 1];
    visited[destination] = true;

    while(!pq.isEmpty()) {

      Node here = pq.poll();

      int number = here.number;
      int count = here.count;

      distances[number] = count;

      for(int i = 0; i < routes[number].size(); i++) {
        int next = routes[number].get(i);

        if(visited[next]) continue;
        visited[next] = true;
        pq.add(new Node(next, count + 1));
      }
    }

  }
  private class Node implements Comparable<Node> {
    int number;
    int count;
    Node(int number, int count) {
      this.number = number;
      this.count = count;
    }
    @Override
    public int compareTo(Node o) {
      return this.count - o.count;
    }
  }

}
