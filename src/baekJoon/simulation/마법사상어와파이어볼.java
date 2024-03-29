package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20056
public class 마법사상어와파이어볼 {

  private static final int EQUAL = 0;
  private static final int NOT_EQUAL = 1;
  private static final int[][] DIVIDE_DIRECTION= {{0, 2, 4, 6}, {1, 3, 5, 7}};

  private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
  private static int size;
  private static Queue<FireBall>[][] map;
  private static Queue<FireBall> moveQueue;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = 0;
    size = Integer.parseInt(st.nextToken());
    int fireballCount = Integer.parseInt(st.nextToken());
    int command = Integer.parseInt(st.nextToken());

    map = new Queue[size + 1][size + 1];
    moveQueue = new LinkedList<>();

    for(int i = 1; i <= size; i++) {
      for(int j = 1; j <= size; j++) {
        map[i][j] = new LinkedList<>();
      }
    }

    for(int i = 0; i < fireballCount; i++) {
      st = new StringTokenizer(br.readLine());

      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      map[x][y].add(new FireBall(x, y, w, s, d));
    }

    while(command > 0) {
      for(int i = 1; i <= size; i++) {
        for(int j = 1; j <= size; j++) {
          if(map[i][j].size() > 0) {
            Queue<FireBall> queue = map[i][j];
            int qSize = queue.size();
            for(int z = 0; z < qSize; z++) {
              FireBall fireBall = map[i][j].poll();
              fireBall.move();
            }
          }
        }
      }

      while(!moveQueue.isEmpty()) {
        FireBall fireBall = moveQueue.poll();
        map[fireBall.x][fireBall.y].add(fireBall);
      }


      // 이동 후 파이어볼 합치고 나누기
      for(int i = 1; i <= size; i++) {
        for(int j = 1; j <= size; j++) {
          if(map[i][j].size() > 1) {
            unionAndDivide(i, j);
          }
        }
      }

      command--;
    }

    for(int i = 1; i <= size; i++) {
      for(int j = 1; j <= size; j++) {
        if(map[i][j].size() > 0) {
          for(FireBall fireBall : map[i][j]) {
            answer += fireBall.weight;
          }
        }
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class FireBall {
    int x, y;
    int weight;
    int direction;
    int speed;

    public FireBall(int x, int y, int weight, int speed, int direction) {
      this.x = x;
      this.y = y;
      this.weight = weight;
      this.speed = speed;
      this.direction = direction;
    }

    public void move() {
      int nx = this.x;
      int ny = this.y;

      for(int i = 0; i < speed; i++) {
        nx += dx[direction];
        ny += dy[direction];

        if(nx == 0) nx = size;
        if(nx == size + 1) nx = 1;
        if(ny == 0) ny = size;
        if(ny == size + 1) ny = 1;
      }

      this.x = nx;
      this.y = ny;

      moveQueue.add(this);
    }
  }

  private static void unionAndDivide(int x, int y) {

    int directionType = EQUAL;

    Queue<FireBall> fireballs = map[x][y];

    FireBall first = map[x][y].poll();
    int weightSum = first.weight;
    int speedSum = first.speed;
    int direction = first.direction % 2;
    int count = 1;

    while(!fireballs.isEmpty()) {
      count++;
      FireBall fireBall = fireballs.poll();
      weightSum += fireBall.weight;
      speedSum += fireBall.speed;
      if(fireBall.direction % 2 != direction) directionType = NOT_EQUAL;
    }

    int nextWeight = weightSum / 5;

    if(nextWeight == 0) return;

    int nextSpeed = speedSum / count;
    int[] nextDirection = DIVIDE_DIRECTION[directionType];

    for(int i = 0; i < nextDirection.length; i++) {
      map[x][y].add(new FireBall(x, y, nextWeight, nextSpeed, nextDirection[i]));
    }
  }


}
