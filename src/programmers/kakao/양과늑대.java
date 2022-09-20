package programmers.kakao;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;

public class 양과늑대 {

  private static List<Integer>[] connect;
  private static int[] infos;
  private static boolean[][][] visited;
  private static int answer;

  public static int solution(int[] info, int[][] edges) {
    answer = 0;
    infos = info;

    connect = new List[info.length];

    for(int i = 0; i < connect.length; i++) {
      connect[i] = new ArrayList<>();
    }

    for(int i = 0; i < edges.length; i++) {
      int node1 = edges[i][0];
      int node2 = edges[i][1];

      connect[node1].add(node2);
      connect[node2].add(node1);
    }
    visited = new boolean[infos.length][infos.length + 1][infos.length + 1];

    dfs(0, 0, 0);
    return answer;
  }

  private static void dfs(int sheep, int wolf, int index) {

    if(infos[index] == 0) sheep++;
    else if(infos[index] == 1) wolf++;

    if(wolf >= sheep) return;

    answer = max(answer, sheep);

    for(int i = 0; i < connect[index].size(); i++) {
      int next = connect[index].get(i);

      int kind = infos[index];

      if(visited[next][sheep][wolf]) continue;
      visited[next][sheep][wolf] = true;
      infos[index] = -1;
      dfs(sheep, wolf, next);
      infos[index] = kind;
      visited[next][sheep][wolf] = false;
    }
  }


  public static void main(String[] args) {

    int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
    int[][] edges = {
        {0, 1}, {1, 2}, {1, 4}, {0, 8},
        {8, 7}, {9, 10}, {9, 11}, {4, 3},
        {6, 5}, {4, 6}, {8, 9}
    };

    System.out.println(solution(info, edges));

  }

}
