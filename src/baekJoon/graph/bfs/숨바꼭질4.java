package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숨바꼭질4 {

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] dx = {-1 , 1};
    int[] prePoint = new int[100002];

    for(int i = 0; i < 100002; i++) {
      prePoint[i] = -1;
    }

    PriorityQueue<Point> pq = new PriorityQueue<>();
    pq.add(new Point(N, 0));
    prePoint[N] = N;

    while(!pq.isEmpty()) {
      Point here = pq.poll();

      int x = here.x;
      int time = here.time;

      if(x == K) {

        bw.write(String.valueOf(here.time));
        bw.newLine();

        List<Integer> path = new ArrayList<>();
        while(true) {
          path.add(x);
          if(x == prePoint[x]) break;
          x = prePoint[x];
        }

        for(int i = path.size() - 1; i >= 0; i--) {
          bw.write(String.valueOf(path.get(i)) + " ");
        }
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
        return;
      }

      for(int i = 0; i < 3; i++) {
        int nx;
        if(i == 2) nx = x * 2;
        else nx = x + dx[i];
        if(nx < 0 || nx > 100001) continue;
        if(prePoint[nx] != -1) continue;
        prePoint[nx] = x;
        pq.add(new Point(nx, time + 1));
      }
    }
  }

  private static class Point implements Comparable<Point> {
    int x, time;

    public Point(int x, int time) {
      this.x = x;
      this.time = time;
    }

    @Override
    public int compareTo(Point o) {
      return this.time - o.time;
    }
  }

}
