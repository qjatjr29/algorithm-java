package baekJoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 웜홀 {

    private static final Integer MAX = Integer.MAX_VALUE;

    private static ArrayList<ArrayList<Road>> roads;

    private static boolean sol(int N, int start) {
      int [] dist = new int[N + 1];
      for(int i = 1; i <= N; i++) {
          dist[i] = MAX;
      }

      dist[start] = 0;
      boolean update = false;

      for(int i = 1; i < N; i++) {
          update = false;
          for(int j = 1; j <= N; j++) {
              for (Road road : roads.get(j)) {
                  if(dist[j] != MAX && dist[road.idx] > dist[j] + road.time) {
                      dist[road.idx] = dist[j] + road.time;
                      update = true;
                  }
              }
          }
          if(!update) break;
      }
      if(update) {
          for (int i = 1; i <= N; i++) {
              for (Road road : roads.get(i)) {
                  if (dist[i] != MAX && dist[road.idx] > dist[i] + road.time) {
                      return true;
                  }
              }
          }
      }
      return false;
    }

    private static class Road {
        int idx, time;
        Road(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int TC = Integer.parseInt(st.nextToken());

        for(int testcase = 0; testcase < TC; testcase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            roads = new ArrayList<>();
            for(int i = 0; i <= N; i++) {
                roads.add(new ArrayList<>());
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                roads.get(S).add(new Road(E, T));
                roads.get(E).add(new Road(S, T));
            }

            for(int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                roads.get(S).add(new Road(E, -T));
            }

            boolean chk = false;
            for(int i = 1; i <= N; i++) {
                if(sol(N, i)) {
                    chk = true;
                    bw.write("YES");
                    break;
                }
            }
            if(!chk) bw.write("NO");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
