package baekJoon.graph.bfs;

import static java.util.Collections.sort;

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

public class 움직이는미로탈출 {

  private static int[][] map;
  // 아래, 위, 오른쪽, 왼쪽, 오른쪽아래, 왼쪽아래, 오른쪽위, 왼쪽위
  private static int[] dx = {0, -1, 1, 0, 0, 1, 1, -1, -1};
  private static int[] dy = {0, 0, 0, 1, -1, 1, -1, 1, -1};
  private static List<Location> walls;
  private static Queue<Location> character;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    walls = new ArrayList<>();
    character = new LinkedList<>();
    character.add(new Location(8, 1));
    map = new int[9][9];

    for(int i = 1; i <= 8; i++) {
      st = new StringTokenizer(br.readLine());
      String state = st.nextToken();
      for(int j = 1; j <= 8; j++) {
        if(state.charAt(j - 1) == '#') {
          map[i][j] = 1;
          walls.add(new Location(i, j));
        }
      }
    }

    sort(walls);
    int cnt = 1;
    while(true) {
      if(character.isEmpty()) break;
      if(cnt == 0) {
        cnt = character.size();
        moveWall();
        sort(walls);
      }
      Location here = character.poll();
      int cx = here.x;
      int cy = here.y;

      if(cx == 1 || cy == 8) {
        bw.write("1");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
        return;
      }
      moveCharacter(cx, cy);
      cnt--;
    }
    bw.write("0");
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void moveWall() {
    int size = walls.size();
    if(size == 0) return;
    for (int i = 0; i < size; i++) {
      Location wall = walls.get(i);
      int wallX = wall.x;
      int wallY = wall.y;
      map[wallX][wallY] = 0;
      if (wallX == 8) {
        continue;
      }
      walls.add(new Location(wallX + 1, wallY));
      map[wallX + 1][wallY] = 1;
    }
    for (int i = 0; i < size; i++) {
      walls.remove(0);
    }
  }

  private static void moveCharacter(int x, int y) {
    for(int i = 0; i < 9; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx <= 0 || nx > 8 || ny <= 0 || ny > 8) continue;
      if(map[nx][ny] == 1) continue;
      if(map[nx - 1][ny] == 1) continue;
      character.add(new Location(nx, ny));
    }
  }

  private static class Location implements Comparable<Location>{
    int x, y;

    public Location(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Location o) {
      if(x < o.x) return 1;
      else if (x == o.x) return 0;
      else return -1;
    }
  }


}
