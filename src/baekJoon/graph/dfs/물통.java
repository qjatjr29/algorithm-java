package baekJoon.graph.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2251
public class 물통 {

  private static Set<Integer> answer;
  private static int[] capacity;
  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    capacity = new int[3];
    answer = new HashSet<>();
    visited = new boolean[201][201];

    for(int i = 0; i < 3; i++) {
      capacity[i] = Integer.parseInt(st.nextToken());
    }

    dfs(0, 0, capacity[2]);

    List<Integer> answerList = new ArrayList<>(answer);

    Collections.sort(answerList);

    for (Integer ans : answerList) {
      bw.write(String.valueOf(ans) + " ");
    }
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void dfs(int a, int b, int c) {

    if(visited[a][b]) return;
    if(a == 0) answer.add(c);
    visited[a][b] = true;

    // a -> b
    if(a + b <= capacity[1]) dfs(0, a + b, c);
    else dfs((a + b) - capacity[1], capacity[1], c);

    // a -> c
    dfs(0, b, a + c);

    // b -> a
    if(a + b <= capacity[0]) dfs(a + b, 0, c);
    else dfs(capacity[0], (a + b) - capacity[0], c);

    // b -> c
    dfs(a, 0, b + c);

    // c -> a
    if(a + c <= capacity[0]) dfs(a + c, b, 0);
    else dfs(capacity[0], b, (a + c) - capacity[0]);

    // c -> b
    if(b + c <= capacity[1]) dfs(a, b + c, 0);
    else dfs(a, capacity[1], (b + c) - capacity[1]);

  }

}
