package leetcode.simulation;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/contain-virus/
public class ContainVirus {

  private static final int VIRUS = 1;
  private static final int NOT_VIRUS = 0;
  private static final int ISOLATION = -1;

  private static int row;
  private static int col;
  private static int[] dx = {-1, 1, 0 ,0};
  private static int[] dy = {0, 0, -1, 1};
  private static int notVirusCnt;
  private static int virusCnt;
  private static int answer;

  public int containVirus(int[][] isInfected) {

    row = isInfected.length;
    col = isInfected[0].length;
    answer = 0;

    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        if(isInfected[i][j] == VIRUS) virusCnt++;
        else if(isInfected[i][j] == NOT_VIRUS) notVirusCnt++;
      }
    }

    while(true) {

      // 바이러스가 다 격리된 경우
      if(virusCnt == 0) break;

      // 모두 바이러스에 전파된 경우
      if(notVirusCnt == 0) break;

      int maxSpreadAreaCnt = 0;
      boolean[][] visited = new boolean[row][col];
      int isolationX = -1;
      int isolationY = -1;


      for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
          if(isInfected[i][j] == VIRUS && !visited[i][j]) {
            int count = nextSpreadArea(i, j, isInfected, visited);
            if(maxSpreadAreaCnt < count) {
              maxSpreadAreaCnt = count;
              isolationX = i;
              isolationY = j;
            }
          }
        }
      }

      // 바이러스가 전파되지 않는 경우
      if(isolationX == -1 && isolationY == -1) break;

      // 해당 바이러스 지역 격리
      isolation(isolationX, isolationY, isInfected);

      // 다른 바이러스 지역 전파 시작
      spread(isInfected);


    }

    return answer;
  }

  // 격리할 영역 찾기
  private int nextSpreadArea(int x, int y, int[][] isInfected, boolean[][] visited) {

    int count = 0;

    boolean[][] emptyVisited = new boolean[row][col];

    Queue<Virus> virusQueue = new LinkedList<>();
    virusQueue.add(new Virus(x, y));
    visited[x][y] = true;


    while(!virusQueue.isEmpty()) {
      Virus virus = virusQueue.poll();

      int cx = virus.x;
      int cy = virus.y;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (check(isInfected, visited, nx, ny)) continue;

        if(isInfected[nx][ny] == NOT_VIRUS && !emptyVisited[nx][ny]) {
          count++;
          emptyVisited[nx][ny] = true;
        }
        else if(isInfected[nx][ny] == VIRUS) {
          visited[nx][ny] = true;
          virusQueue.add(new Virus(nx, ny));
        }
      }
    }

    return count;
  }

  // 바이러스 지역 격리하기
  private void isolation(int x, int y, int[][] isInfected) {

    Queue<Virus> virusQueue = new LinkedList<>();
    virusQueue.add(new Virus(x, y));

    boolean[][] visited = new boolean[row][col];
    visited[x][y] = true;


    while(!virusQueue.isEmpty()) {

      Virus virus = virusQueue.poll();
      int cx = virus.x;
      int cy = virus.y;
      isInfected[cx][cy] = ISOLATION;

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(check(isInfected, visited, nx, ny)) continue;
        if(isInfected[nx][ny] == NOT_VIRUS) {
          answer++;
          continue;
        }
        visited[nx][ny] = true;
        if(isInfected[nx][ny] == VIRUS) virusQueue.add(new Virus(nx, ny));
      }
      virusCnt--;
    }
  }


  // 바이러스 전파
  private void spread(int[][] isInfected) {

    boolean[][] visited = new boolean[row][col];

    for(int x = 0; x < row; x++) {
      for(int y = 0; y < col; y++) {
        // 바이러스인 경우.
        if(isInfected[x][y] == VIRUS && !visited[x][y]) {
          visited[x][y] = true;
          for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(check(isInfected, visited, nx, ny)) continue;

            if(isInfected[nx][ny] == NOT_VIRUS) {
              isInfected[nx][ny] = VIRUS;
              virusCnt++;
              notVirusCnt--;
              visited[nx][ny] = true;
            }
          }
        }
      }
    }


  }

  private boolean check(int[][] isInfected, boolean[][] visited, int nx, int ny) {
    if(nx < 0 || nx >= row || ny < 0 || ny >= col)
      return true;
    if(visited[nx][ny])
      return true;
    return isInfected[nx][ny] == ISOLATION;
  }

  private class Virus {
    int x, y;

    public Virus(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
