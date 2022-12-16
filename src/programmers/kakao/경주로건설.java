package programmers.kakao;

import static java.lang.Math.min;

import java.util.LinkedList;
import java.util.Queue;

public class 경주로건설 {

  // 상좌하우
  private static int[] dx = {-1, 0, 1, 0};
  private static int[] dy = {0, -1, 0, 1};
  private static int[][][] cost;
  private static int boardLength;

  public static int solution(int[][] board) {
    int answer = 0;
    boardLength = board.length;

    // 해당 칸 방문 여부 확인
    boolean[][] visited = new boolean[boardLength][boardLength];

    // 현재 위치에서의 가장 최소비용의 경주로 (상0 좌1 하2 우3)
    cost = new int[boardLength][boardLength][4];

    for(int i = 0; i < boardLength; i++) {
      for(int j = 0; j < boardLength; j++) {
        for(int z = 0; z < 4; z++) {
          cost[i][j][z] = Integer.MAX_VALUE;
//          cost[i][j][z] = 5000000;
        }
      }
    }

    Queue<Position> positionQueue = new LinkedList<>();

    if(board[1][0] == 0) {
      cost[1][0][2] = 100;
      positionQueue.add(new Position(1, 0, 2));
    }

    if(board[0][1] == 0) {
      cost[0][1][3] = 100;
      positionQueue.add(new Position(0, 1, 3));
    }

    while(true) {
      if(positionQueue.isEmpty()) break;

      Position here = positionQueue.poll();
      int currentX = here.x;
      int currentY = here.y;
      int currentDir = here.dir;

      for(int nextDir = 0; nextDir < 4; nextDir++) {
        int nextX = currentX + dx[nextDir];
        int nextY = currentY + dy[nextDir];

        if(nextX < 0 || nextX >= boardLength || nextY < 0 || nextY >= boardLength) continue;
        if(board[nextX][nextY] == 1) continue;

        // 직선인 경우
        if(currentDir == nextDir) {
          if(cost[nextX][nextY][nextDir] < cost[currentX][currentY][currentDir] + 100) continue;
          cost[nextX][nextY][nextDir] = cost[currentX][currentY][currentDir] + 100;
          positionQueue.add(new Position(nextX, nextY, nextDir));
        }

        // 방향이 다른 경우
        else {
          // 서로 반대 방향인 경우
          if(currentDir % 2 == nextDir % 2) continue;
          if(cost[nextX][nextY][nextDir] < cost[currentX][currentY][currentDir] + 600) continue;

          cost[nextX][nextY][nextDir] = cost[currentX][currentY][currentDir] + 600;
          positionQueue.add(new Position(nextX, nextY, nextDir));
        }
      }


    }
    answer = cost[boardLength - 1][boardLength - 1][0];

    for(int i = 1; i < 4; i++) {
      answer = min(answer, cost[boardLength - 1][boardLength - 1][i]);
    }

    return answer;
  }

  private static class Position {
    int x, y, dir;

    public Position(int x, int y, int dir) {
      this.x = x;
      this.y = y;
      this.dir = dir;
    }
  }

  public static void main(String[] args) {

//    int[][] board ={
//        {0, 0, 0},
//        {0, 0, 0},
//        {0, 0, 0}
//    };

    int[][] board ={
        {0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 1},
        {0, 0, 1, 0, 0, 0, 1, 0},
        {0, 1, 0, 0, 0, 1, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 0},
    };

    int solution = solution(board);

    System.out.println(solution);
  }

}
