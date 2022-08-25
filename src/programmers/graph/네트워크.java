package programmers.graph;

import java.util.LinkedList;
import java.util.Queue;

public class 네트워크 {

  private static boolean[] visited;

  public static int solution(int n, int[][] computers) {
    int answer = 0;

    visited = new boolean[n];

    for(int i = 0; i < n; i++) {
      if(visited[i]) continue;
      network(i, n, computers);
      answer++;
    }

    return answer;
  }

  private static void network(int node, int n, int[][] computers) {
    Queue<Integer> q = new LinkedList<>();
    q.add(node);

    while(true) {
      if(q.isEmpty()) break;
      Integer here = q.poll();

      for(int i = 0; i < n; i++) {
        if(visited[i]) continue;
        if(here == i) continue;
        if(computers[here][i] == 1) {
          q.add(i);
          visited[i] = true;
        }
      }
    }
  }

  public static void main(String[] args) {

//    int n = 3;
//    int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

    int n = 3;
    int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

    System.out.println(solution(n, computers));


  }

}
