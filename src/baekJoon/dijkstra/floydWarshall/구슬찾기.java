package baekJoon.dijkstra.floydWarshall;

import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구슬찾기 {

  private static int[][] distance;
  private static final int INF = 101;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int answer = 0;

    distance = new int[N + 1][N + 1];
    for (int[] row : distance) Arrays.fill(row, INF);

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      distance[start][end] = 1;
    }

    floyd(N);

    int[] row = new int[N + 1];
    int[] col = new int[N + 1];

    for(int i = 1; i <= N; i++) {
      for(int j = 1; j <= N; j++) {
        if(i == j || distance[i][j] == INF) continue;
        row[i]++;
        col[j]++;
      }
    }

    int half = (N + 1) / 2;
    for(int i = 1; i <= N; i++) {
      if(row[i] >= half || col[i] >= half) answer++;
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void floyd(int N) {
    for(int i = 1; i <= N; i++) {
      for(int j = 1; j <= N; j++) {
        for(int z = 1; z <= N; z++) {
          if(j == z) continue;
          distance[j][z] = min(distance[j][z], distance[j][i] + distance[i][z]);
        }
      }
    }
  }

}
