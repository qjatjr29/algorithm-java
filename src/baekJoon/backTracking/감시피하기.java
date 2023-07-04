package baekJoon.backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시피하기 {

  private static final Character EMPTY = 'X';
  private static final Character TEACHER = 'T';
  private static final Character STUDENT = 'S';
  private static final Character OBSTACLE = 'O';

  private static Character[][] map;
  private static Boolean isCan;
  private static List<int[]> teachers;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    map = new Character[N][N];
    isCan = false;
    teachers = new ArrayList<>();

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        map[i][j] = st.nextToken().charAt(0);
        if(map[i][j] == TEACHER) {
          teachers.add(new int[] {i, j});
        }
      }
    }

    List<int[]> obstacleList = new ArrayList<>();
    bfs(0, 0, 0, obstacleList);

    if (isCan) bw.write("YES");
    else bw.write("NO");

    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void bfs(int count, int x, int y, List<int[]> obstacleList) {

    int size = map.length;
    if(count == 3) {
      if(monitor(size, obstacleList)) isCan = true;
      return;
    }

    for(int i = x; i < size; i++) {
      if(i == x) {
        for(int j = y + 1; j < size; j++) {
          if(map[i][j] == EMPTY) {
            obstacleList.add(new int[] {i, j});
            bfs(count + 1, i, j, obstacleList);
            obstacleList.remove(obstacleList.size() - 1);
          }
        }
      }

      else {
        for(int j = 0; j < size; j++) {
          if(map[i][j] == EMPTY) {
            obstacleList.add(new int[] {i, j});
            bfs(count + 1, i, j, obstacleList);
            obstacleList.remove(obstacleList.size() - 1);
          }
        }
      }
    }
  }

  private static boolean monitor(int n, List<int[]> obstacleList) {

    int cnt = 0;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    for(int[] obstacle : obstacleList) {
      int oX = obstacle[0];
      int oY = obstacle[1];
      map[oX][oY] = OBSTACLE;
    }

    for(int[] teacher : teachers) {
      if(cnt != 0) break;
      int x = teacher[0];
      int y = teacher[1];

      for(int i = 0; i < 4; i++) {
        if(cnt != 0) break;
        int tempX = x += dx[i];
        int tempY = y += dy[i];

        while(true) {

          if(tempX < 0 || tempX >= n || tempY < 0 || tempY >= n) break;
          if(map[tempX][tempY] == STUDENT) {
            cnt++;
            break;
          }
          if(map[tempX][tempY] == OBSTACLE) break;

          tempX += dx[i];
          tempY += dy[i];
        }
      }
    }
    for(int[] obstacle : obstacleList) {
      int oX = obstacle[0];
      int oY = obstacle[1];
      map[oX][oY] = EMPTY;
    }

    return cnt <= 0;
  }


}
