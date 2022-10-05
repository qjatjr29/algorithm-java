package baekJoon.graph;

import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소3 {

  private static int N, M;
  private static int[][] map;
  private static List<Coordination> virus;
  private static int answer;
  private static boolean[][] visited;
  private static int emptySpace;
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    answer = Integer.MAX_VALUE;
    virus = new ArrayList<>();

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 2) {
          virus.add(new Coordination(i, j, 0));
        }
        if(map[i][j] == 0) {
          emptySpace++;
        }
      }
    }

    if(emptySpace == 0) answer = 0;
    else dfs(0, 0, new ArrayList<>());

    if(answer == Integer.MAX_VALUE) answer = -1;
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Coordination {
    int x, y;
    int count;

    public Coordination(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }

  private static void move(List<Integer> viruses) {

    int emptyCount = emptySpace;
    Queue<Coordination> virusQueue = new LinkedList<>();
    visited = new boolean[N][N];

    for(int i = 0; i < viruses.size(); i++) {
      int idx = viruses.get(i);
      Coordination coordination = virus.get(idx);
      virusQueue.add(new Coordination(coordination.x, coordination.y, 0));
      visited[coordination.x][coordination.y] = true;
    }

    while(true) {
      if(virusQueue.isEmpty()) break;

      Coordination here = virusQueue.poll();
      int cx = here.x;
      int cy = here.y;
      int cnt = here.count;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
        if(map[nx][ny] == 1) continue;
        if(visited[nx][ny]) continue;

        if(map[nx][ny] == 0) {
          emptyCount--;
        }
        if(emptyCount == 0) {
          answer = min(answer, cnt + 1);
          return;
        }
        visited[nx][ny] = true;
        virusQueue.add(new Coordination(nx, ny, cnt + 1));
      }
    }
  }

  private static void dfs(int cnt, int idx, List<Integer> indexList) {

    if(idx > virus.size()) return;

    if(cnt == M) {
      move(indexList);
      return;
    }

    for(int i = idx; i < virus.size(); i++) {
      indexList.add(i);
      dfs(cnt + 1, i + 1, indexList);
      indexList.remove(indexList.size() - 1);
    }
  }
}
