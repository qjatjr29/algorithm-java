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

// https://www.acmicpc.net/problem/18405
public class 경쟁적전염 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int S, X, Y;
    PriorityQueue<Virus> virusQueue = new PriorityQueue<>();

    int[][] map = new int[N][N];
    boolean[][] visited = new boolean[N][N];
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] != 0) {
          virusQueue.add(new Virus(i, j, map[i][j]));
          visited[i][j] = true;
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    S = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());
    Y = Integer.parseInt(st.nextToken());

    List<Virus> addVirusList;
    boolean isSpread = false;

    while(S > 0) {
      addVirusList = new ArrayList<>();
      while(!virusQueue.isEmpty()) {
        Virus virus = virusQueue.poll();
        int vx = virus.x;
        int vy = virus.y;
        int type = virus.type;

        if(vx == X - 1 && vy == Y - 1) {
          isSpread = true;
          break;
        }

        for(int i = 0; i < 4; i++) {

          int nx = vx + dx[i];
          int ny = vy + dy[i];

          if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
          if(visited[nx][ny] || map[nx][ny] != 0) continue;

          visited[nx][ny] = true;
          map[nx][ny] = type;
          addVirusList.add(new Virus(nx, ny, type));
        }
      }
      if(isSpread) break;
      virusQueue.addAll(addVirusList);
      S--;
    }

    int answer = map[X - 1][Y - 1];
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Virus implements Comparable<Virus> {
    int x, y;
    int type;

    public Virus(int x, int y, int type) {
      this.x = x;
      this.y = y;
      this.type = type;
    }

    @Override
    public int compareTo(Virus o) {
      return this.type - o.type;
    }
  }

}
