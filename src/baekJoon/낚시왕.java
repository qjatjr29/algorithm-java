package baekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 낚시왕 {

  private static int R, C, M;
  private static long answer;
  private static List<Shark> sharks;
  private static int map[][];
  // 위 아래 오 왼
  private static int[] dx = {-1, 1, 0, 0};
  private static int[] dy = {0, 0, 1, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[R][C];
    sharks = new ArrayList<>();

    sharks.add(new Shark());
    for(int i = 0; i < M; i++) {
      int r, c, s, d, z;
      st = new StringTokenizer(br.readLine());
      r = Integer.parseInt(st.nextToken()) - 1;
      c = Integer.parseInt(st.nextToken()) - 1;
      s = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
      z = Integer.parseInt(st.nextToken());

      if(d == 1 || d == 2) {
        s = s % ((R - 1) * 2);
      } else {
        s = s % ((C - 1) * 2);
      }

      sharks.add(new Shark(r, c, s, d, z, i + 1));
      map[r][c] = i + 1;
    }

    for(int i = 0; i < C; i++) {

      // catch
      for(int j = 0; j < R; j++) {
        if(map[j][i] != 0) {
          int sharkIdx = map[j][i];
          answer += sharks.get(sharkIdx).size;
          sharks.get(sharkIdx).die();
          map[j][i] = 0;
          break;
        }
      }

      // move
      move();

    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Shark {
    int x, y, speed, direction, size, index;
    boolean die;

    public Shark() {
    }

    public Shark(int x, int y, int speed, int direction, int size, int index) {
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.direction = direction;
      this.size = size;
      this.index = index;
      this.die = false;
    }

    public void die() {
      this.die = true;
    }

    public void move(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public void turn() {
      switch (this.direction) {
        case 1:
          this.direction = 2;
          break;
        case 2:
          this.direction = 1;
          break;
        case 3:
          this.direction = 4;
          break;
        case 4:
          this.direction = 3;
          break;
      }
    }


  }
  private static void move() {

    int[][] newMap = new int[R][C];

    for (Shark shark : sharks) {
      if(shark.die) continue;

      for(int i = 0; i < shark.speed; i++) {
        int nx = shark.x + dx[shark.direction - 1];
        int ny = shark.y + dy[shark.direction - 1];

        if(!isIn(nx, ny)) {
          shark.turn();
          nx = shark.x + dx[shark.direction - 1];
          ny = shark.y + dy[shark.direction - 1];
        }
        shark.move(nx, ny);
      }

      int checkShark = newMap[shark.x][shark.y];

      if(checkShark == 0) {
        newMap[shark.x][shark.y] = shark.index;
      }

      else if(sharks.get(checkShark).size < shark.size){
        sharks.get(checkShark).die();
        newMap[shark.x][shark.y] = shark.index;
      }
      else {
        shark.die();
      }
    }
    copyMap(newMap);
  }

  private static boolean isIn(int x, int y) {
    if (0 <= x && x < R && 0 <= y && y < C)
      return true;
    return false;
  }
  private static void copyMap(int[][] newMap) {
    for (int i = 0; i < R; i++) {
      System.arraycopy(newMap[i], 0, map[i], 0, C);
    }
  }

}
