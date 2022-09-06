package programmers.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 가장먼노드 {

  private static List<Integer>[] adj;
  private static boolean[] visited;
  private static Pair answer;

  public static int solution(int n, int[][] edge) {

    answer = new Pair(-1, 0);

    adj = new List[n + 1];
    visited = new boolean[n + 1];
    for(int i = 0; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }

    for(int i = 0; i < edge.length; i++) {
      int node1 = edge[i][0];
      int node2 = edge[i][1];

      adj[node1].add(node2);
      adj[node2].add(node1);
    }
    sol();

    return answer.cnt;
  }

  private static void sol() {
    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(1, 0));
    visited[1] = true;

    while(true) {
      if(q.isEmpty()) break;
      Pair here = q.poll();
      int cNode = here.value;
      int cCnt = here.cnt;

      for(int i = 0; i < adj[cNode].size(); i++) {
        int next = adj[cNode].get(i);

        if(visited[next]) continue;
        visited[next] = true;
        if(cCnt > answer.value) answer = new Pair(cCnt, 1);
        else if(cCnt == answer.value) answer.cnt++;
        q.add(new Pair(next, cCnt + 1));
      }
    }
  }

  private static class Pair {
    int value, cnt;

    public Pair(int value, int cnt) {
      this.value = value;
      this.cnt = cnt;
    }
  }

  public static void main(String[] args) {

    int[][] vertex = {
        {3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}
    };
    System.out.println(solution(6, vertex));
  }
}
