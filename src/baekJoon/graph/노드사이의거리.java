package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 노드사이의거리 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] distance = new int[N + 1][N + 1];

    for(int i = 1; i <= N; i++) {
      for(int j = 1; j <= N; j++) {
        if(i == j) continue;
        distance[i][j] = 1000001;
      }
    }

    for(int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      distance[node1][node2] = cost;
      distance[node2][node1] = cost;
    }

    for(int i = 1; i <= N; i++) {
      for(int j = 1; j <= N; j++) {
        for(int z = 1; z <= N; z++) {
          if(j == z) continue;
          distance[j][z] = Math.min(distance[j][z], distance[j][i] + distance[i][z]);
        }
      }
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      bw.write(String.valueOf(distance[start][end]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();
  }

}
