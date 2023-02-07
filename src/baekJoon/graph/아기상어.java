package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 아기상어 {

  private static final int SHARK_POSITION = 9;
  private static final int EMPTY_POSITION = 0;
  private static final int DISTANCE_INF = 500;
  private static int N;
  private static int[][] map;
  private static int sharkSize;
  private static int sharkX, sharkY;
  private static int eatCount;
  private static int answer;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    PriorityQueue<Fish> fishPriorityQueue = new PriorityQueue<>();
    sharkSize = 2;
    eatCount = 0;

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        int size = Integer.parseInt(st.nextToken());
        map[i][j] = size;
        // 아기상어
        if(size == SHARK_POSITION) {
          sharkX = i;
          sharkY = j;
          continue;
        }
        // 물고기들
        if(size != EMPTY_POSITION) {
          fishPriorityQueue.add(new Fish(i, j, size));
        }
      }
    }

    // 물고기들과 현재 상어 위치 거리 계산
    findEatenFish(fishPriorityQueue);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static class Fish implements Comparable<Fish> {
    int x, y;
    int size;
    int distance;

    public Fish(int x, int y, int size) {
      this.x = x;
      this.y = y;
      this.size = size;
      this.distance = DISTANCE_INF;
    }

    @Override
    public int compareTo(Fish o) {
      if(this.size > o.size) return 1;
      return -1;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Fish fish = (Fish) o;
      return x == fish.x && y == fish.y && size == fish.size && distance == fish.distance;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, size, distance);
    }
  }

  private static void findEatenFish(PriorityQueue<Fish> fishPriorityQueue) {

    while(true) {
      List<Fish> removeFishes = new ArrayList<>();
      Fish eatenFish = null;

      // 물고기 사이즈가 현재 상어 사이즈보다 작은 경우만 거리 구한다.
      while(!fishPriorityQueue.isEmpty()) {

        Fish curFish = fishPriorityQueue.poll();

        removeFishes.add(curFish);
        if(curFish.size >= sharkSize) break;

        bfs(curFish);
        if(curFish.distance == DISTANCE_INF) continue;

        if(eatenFish == null) {
          eatenFish = curFish;
          continue;
        }

        if(eatenFish.distance > curFish.distance) {
          eatenFish = curFish;
        }
        else if(eatenFish.distance == curFish.distance) {
          if(eatenFish.x > curFish.x) {
            eatenFish = curFish;
          }
          else if(eatenFish.x == curFish.x) {
            if(eatenFish.y > curFish.y) {
              eatenFish = curFish;
            }
          }
        }
      }

      // 먹을 수 있는 물고기가 없다.
      if(eatenFish == null) break;

      for (Fish fish : removeFishes) {
        if (fish.equals(eatenFish))
          continue;
        fish.distance = DISTANCE_INF;
        fishPriorityQueue.add(fish);
      }
      map[sharkX][sharkY] = 0;
      sharkX = eatenFish.x;
      sharkY = eatenFish.y;
      eatCount++;
      answer += eatenFish.distance;

      if(eatCount == sharkSize) {
        eatCount = 0;
        sharkSize++;
      }
    }
  }


  /**
   * 접근할 수 있다면 fish.distance 값이 달라짐.
   * 접근 못하면 Distance_INF
   * @param fish
   */
  private static void bfs(Fish fish) {

    PriorityQueue<Node> pq = new PriorityQueue<>();
    boolean[][] visited = new boolean[N][N];
    visited[sharkX][sharkY] = true;
    pq.add(new Node(sharkX, sharkY, 0));

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    while(!pq.isEmpty()) {

      Node here = pq.poll();
      int cx = here.x;
      int cy = here.y;

      if(cx == fish.x && cy == fish.y) {
        fish.distance = here.distance;
        break;
      }

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
        if(visited[nx][ny]) continue;
        if(map[nx][ny] != EMPTY_POSITION) {
          if(map[nx][ny] > sharkSize) continue;
        }

        visited[nx][ny] = true;
        pq.add(new Node(nx, ny, here.distance + 1));
      }
    }
  }

  private static class Node implements Comparable<Node> {
    int x, y, distance;

    public Node(int x, int y, int distance) {
      this.x = x;
      this.y = y;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
      if(this.distance > o.distance) return 1;
      else return -1;
    }
  }

}
