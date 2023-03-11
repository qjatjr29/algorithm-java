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

// https://www.acmicpc.net/problem/2668
public class 숫자고르기 {

  private static List<Integer> answer;
  private static boolean[] select;
  private static int[] board;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    answer = new ArrayList<>();
    board = new int[N + 1];
    select = new boolean[N + 1];

    for(int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      board[i] = Integer.parseInt(st.nextToken());
    }

    for(int i = 1; i <= N; i++) {
      select[i] = true;
      dfs(i, i);
      select[i] = false;
    }

    Collections.sort(answer);
    bw.write(String.valueOf(answer.size()));
    bw.newLine();
    for (int ans : answer) {
      bw.write(String.valueOf(ans));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();

  }

  private static void dfs(int idx, int target) {
    if(!select[board[idx]]) {
      select[board[idx]] = true;
      dfs(board[idx], target);
      select[board[idx]] = false;
    }

    if(board[idx] == target) answer.add(target);
  }
}
