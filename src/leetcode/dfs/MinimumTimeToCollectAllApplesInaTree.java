package leetcode.dfs;

// https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

import java.util.ArrayList;
import java.util.List;

public class MinimumTimeToCollectAllApplesInaTree {

  // 노드들 사이 연결 정보
  private static List<Integer>[] nodes;

  // 노드 방문 기록
  private static boolean[] visited;

  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {

    nodes = new List[n];
    visited = new boolean[n];

    for(int i = 0; i < n; i++) {
      nodes[i] = new ArrayList<>();
    }

    for(int i = 0; i < edges.length; i++) {
      int node1 = edges[i][0];
      int node2 = edges[i][1];
      nodes[node1].add(node2);
      nodes[node2].add(node1);
    }

    int answer = 0;
    visited[0] = true;

    for(int i = 0; i < nodes[0].size(); i++) {
      answer += dfs(nodes[0].get(i), hasApple);
    }
    return answer;
  }

  private int dfs(int number, List<Boolean> hasApple) {

    int rtn = 0;
    boolean isFind = false;
    visited[number] = true;

    // 사과가 있는 노드
    if(hasApple.get(number)) isFind = true;

    for(int i = 0; i < nodes[number].size(); i++) {
      int nNode = nodes[number].get(i);
      if(visited[nNode]) continue;
      int nResult = dfs(nNode, hasApple);
      rtn += nResult;
      if(nResult > 0) isFind = true;
    }

    if(isFind) rtn += 2;

    return rtn;
  }

}
