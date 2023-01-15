package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 최소비용구하기2 {

  private static int answer;
  private static int[] preCity;
  private static int[] result;
  private static List<Pair>[] move_cost;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int m = Integer.parseInt(st.nextToken());

    move_cost = new List[n + 1];
    preCity = new int[n + 1];
    result = new int[n + 1];
    Arrays.fill(result, Integer.MAX_VALUE);

    for(int i = 0; i < n + 1; i++) {
      move_cost[i] = new ArrayList<>();
    }

    for(int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());

      int s_city = Integer.parseInt(st.nextToken());
      int e_city = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      move_cost[s_city].add(new Pair(e_city, cost));
    }

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    move(start);

    answer = result[end];

    Stack<Integer> stack = new Stack<>();
    stack.push(end);

    while (preCity[end] != 0) {
      stack.push(preCity[end]);
      end = preCity[end];
    }

    int size = stack.size();

    bw.write(String.valueOf(answer));
    bw.newLine();

    bw.write(String.valueOf(size));
    bw.newLine();

    while(!stack.isEmpty()) {
      bw.write(String.valueOf(stack.pop() + " "));
    }
    bw.newLine();

    bw.flush();
    bw.close();
    br.close();
  }

  private static void move(int start) {
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(new Pair(start));
    result[start] = 0;

    while(!pq.isEmpty()) {
      Pair here = pq.poll();
      int c_city = here.city;
      int c_cost = here.cost;

      if(result[c_city] < c_cost) continue;

      for(int i = 0; i < move_cost[c_city].size(); i++) {
        Pair next = move_cost[c_city].get(i);
        int n_city = next.city;
        int n_cost = next.cost;

        if(result[n_city] > result[c_city] + n_cost) {
          result[n_city] = result[c_city] + n_cost;
          preCity[n_city] = c_city;
          pq.offer(new Pair(n_city, result[n_city]));
        }
      }
    }
  }

  private static class Pair implements Comparable<Pair> {
    int city;
    int cost;

    public Pair(int city) {
      this.city = city;
      this.cost = 0;
    }

    public Pair(int city, int cost) {
      this.city = city;
      this.cost = cost;
    }

    @Override
    public int compareTo(Pair o) {
      return this.cost - o.cost;
    }
  }

}
