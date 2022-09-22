package programmers.kakao;

import java.util.PriorityQueue;

public class 블록이동하기 {

  private static int[] dx = {-1, 1, 0 ,0};
  private static int[] dy = {0, 0, -1, 1};
  private static int[][] boardInfo;

  // 가로세로 / 가로또는세로 / 가로 세로 두개
  private static boolean[][][][] visited;
  private static PriorityQueue<Position> pq;
  private static int rowSize, colSize;


  public static int solution(int[][] board) {
    int answer = 0;
    boardInfo = board;

    visited = new boolean[2][101][101][101];

    rowSize = boardInfo.length;
    colSize = boardInfo[0].length;

    pq = new PriorityQueue<>();
    pq.add(new Position(0, 0, 0, 0, 1, "row"));

    while(true) {

      if(pq.isEmpty()) break;
      Position here = pq.poll();

      int cnt = here.cnt;
      String dir = here.direction;
      Direction direction = Direction.getDirection(dir);

      int x1 = here.x1;
      int y1 = here.y1;
      int x2 = here.x2;
      int y2 = here.y2;

      if(x1 == rowSize - 1 && y1 == colSize - 1) {
        answer = cnt;
        break;
      }

      if(x2 == rowSize - 1 && y2 == colSize - 1) {
        answer = cnt;
        break;
      }

      direction.moveLeft(cnt, x1, y1, x2, y2);
      direction.moveRight(cnt, x1, y1, x2, y2);
      direction.moveUp(cnt, x1, y1, x2, y2);
      direction.moveDown(cnt, x1, y1, x2, y2);
      direction.rotateLeftUp(cnt, x1, y1, x2, y2);
      direction.rotateLeftDown(cnt, x1, y1, x2, y2);
      direction.rotateRightUp(cnt, x1, y1, x2, y2);
      direction.rotateRightDown(cnt, x1, y1, x2, y2);
    }


    return answer;

  }

  private static class Position implements Comparable<Position> {
    int cnt, x1, x2, y1, y2;
    String direction;

    public Position(int cnt, int x1, int y1, int x2, int y2, String direction) {
      this.cnt = cnt;
      this.x1 = x1;
      this.x2 = x2;
      this.y1 = y1;
      this.y2 = y2;
      this.direction = direction;
    }

    @Override
    public int compareTo(Position o) {
      if(this.cnt > o.cnt) return 1;
      else if(this.cnt == o.cnt) return 0;
      else return -1;
    }
  }

  enum Direction {

    ROW(){
      @Override
      public void moveLeft(int cnt, int cx1, int cy1, int cx2, int cy2) {
        // 왼쪽으로 이동
        if(cy1 == 0) return;
        if(boardInfo[cx1][cy1 - 1] == 1) return;
        if(visited[0][cx1][cy1 - 1][cy1]) return;
        visited[0][cx1][cy1 - 1][cy1] = true;

        pq.add(new Position(cnt + 1, cx1, cy1 - 1, cx2, cy1, "row"));
      }

      @Override
      public void moveRight(int cnt, int cx1, int cy1, int cx2, int cy2) {
       // 오른쪽으로 이동
       if(cy2 == colSize - 1) return;
       if(boardInfo[cx2][cy2 + 1] == 1) return;
       if(visited[0][cx2][cy2][cy2 + 1]) return;

        visited[0][cx2][cy2][cy2 + 1] = true;

        pq.add(new Position(cnt + 1, cx2, cy2, cx2, cy2 + 1, "row"));
      }

      @Override
      public void moveUp(int cnt, int cx1, int cy1, int cx2, int cy2) {
        // 위로 이동
        if(cx1 == 0) return;
        if(boardInfo[cx1 - 1][cy1] == 1) return;
        if(boardInfo[cx2 - 1][cy2] == 1) return;
        if(visited[0][cx1 - 1][cy1][cy2]) return;
        visited[0][cx1 - 1][cy1][cy2] = true;

        pq.add(new Position(cnt + 1, cx1 - 1, cy1, cx2 - 1, cy2, "row"));
      }

      @Override
      public void moveDown(int cnt, int cx1, int cy1, int cx2, int cy2) {
        // 아래로 이동
        if(cx1 == rowSize - 1) return;
        if(boardInfo[cx1 + 1][cy1] == 1) return;
        if(boardInfo[cx2 + 1][cy2] == 1) return;
        if(visited[0][cx1 + 1][cy1][cy2]) return;
        visited[0][cx1 + 1][cy1][cy2] = true;

        pq.add(new Position(cnt + 1, cx1 + 1, cy1, cx2 + 1, cy2, "row"));
      }

      @Override
      public void rotateLeftUp(int cnt, int cx1, int cy1, int cx2, int cy2) {

        if(cx1 == 0) return;
        if(boardInfo[cx1 - 1][cy1] == 1) return;
        if(boardInfo[cx2 - 1][cy2] == 1) return;
        if(visited[1][cy2][cx1 - 1][cx2]) return;

        visited[1][cy2][cx1 - 1][cx2] = true;

        pq.add(new Position(cnt + 1, cx1 - 1, cy2, cx2, cy2, "col"));
      }

      @Override
      public void rotateLeftDown(int cnt, int cx1, int cy1, int cx2, int cy2) {
        if(cx1 == rowSize - 1) return;
        if(boardInfo[cx1 + 1][cy1] == 1) return;
        if(boardInfo[cx2 + 1][cy2] == 1) return;
        if(visited[1][cy2][cx2][cx2 + 1]) return;

        visited[1][cy2][cx2][cx2 + 1] = true;

        pq.add(new Position(cnt + 1, cx2, cy2, cx2 + 1, cy2, "col"));
      }

      @Override
      public void rotateRightUp(int cnt, int cx1, int cy1, int cx2, int cy2) {
        if(cx2 == 0) return;
        if(boardInfo[cx2 - 1][cy2] == 1) return;
        if(boardInfo[cx1 - 1][cy1] == 1) return;
        if(visited[1][cy1][cx1 - 1][cx1]) return;

        visited[1][cy1][cx1 - 1][cx1] = true;

        pq.add(new Position(cnt + 1, cx1 - 1, cy1, cx1, cy1, "col"));
      }

      @Override
      public void rotateRightDown(int cnt, int cx1, int cy1, int cx2, int cy2) {
        if(cx2 == rowSize - 1) return;
        if(boardInfo[cx2 + 1][cy2] == 1) return;
        if(boardInfo[cx1 + 1][cy1] == 1) return;
        if(visited[1][cy1][cx1][cx1 + 1]) return;

        visited[1][cy1][cx1][cx1 + 1] = true;

        pq.add(new Position(cnt + 1, cx1, cy1, cx1 + 1, cy1, "col"));
      }
    },
    COL(){
      @Override
      public void moveLeft(int cnt, int cx1, int cy1, int cx2, int cy2) {
        //왼쪽으로 이동
        if(cy1 == 0) return;
        if(boardInfo[cx1][cy1 - 1] == 1) return;
        if(boardInfo[cx2][cy2 - 1] == 1) return;
        if(visited[1][cy1 - 1][cx1][cx2]) return;

        visited[1][cy1 - 1][cx1][cx2] = true;

        pq.add(new Position(cnt + 1, cx1, cy1 - 1, cx2, cy2 - 1, "col"));
      }

      @Override
      public void moveRight(int cnt, int cx1, int cy1, int cx2, int cy2) {
        // 오른쪽으로 이동
        if(cy2 == colSize - 1) return;
        if(boardInfo[cx1][cy1 + 1] == 1) return;
        if(boardInfo[cx2][cy2 + 1] == 1) return;
        if(visited[1][cy2 + 1][cx1][cx2]) return;

        visited[1][cy2 + 1][cx1][cx2] = true;

        pq.add(new Position(cnt + 1, cx1, cy1 + 1, cx2, cy2 + 1, "col"));
      }

      @Override
      public void moveUp(int cnt, int cx1, int cy1, int cx2, int cy2) {
        //위로 이동
        if(cx1 == 0) return;
        if(boardInfo[cx1 - 1][cy1] == 1) return;
        if(visited[1][cy1][cx1 - 1][cx1]) return;

        visited[1][cy1][cx1 - 1][cx1] = true;

        pq.add(new Position(cnt + 1, cx1 - 1, cy1, cx1 ,cy1, "col"));
      }

      @Override
      public void moveDown(int cnt, int cx1, int cy1, int cx2, int cy2) {
        //아래로 이동
        if(cx2 == rowSize - 1) return;
        if(boardInfo[cx2 + 1][cy2] == 1) return;
        if(visited[1][cy2][cx2][cx2 + 1]) return;

        visited[1][cy2][cx2][cx2 + 1] = true;

        pq.add(new Position(cnt + 1, cx2, cy2, cx2 + 1, cy2, "col"));
      }

      @Override
      public void rotateLeftUp(int cnt, int cx1, int cy1, int cx2, int cy2) {
        if(cy1 == 0) return;
        if(boardInfo[cx2][cy1 - 1] == 1) return;
        if(boardInfo[cx1][cy1 - 1] == 1) return;
        if(visited[0][cx1][cy1 - 1][cy1]) return;

        visited[0][cx1][cy1 - 1][cy1] = true;

        pq.add(new Position(cnt + 1, cx1, cy1 - 1, cx1, cy1, "row"));
      }

      @Override
      public void rotateLeftDown(int cnt, int cx1, int cy1, int cx2, int cy2) {
        if(cy1 == 0) return;
        if(boardInfo[cx1][cy1 - 1] == 1) return;
        if(boardInfo[cx2][cy2 - 1] == 1) return;
        if(visited[0][cx2][cy2 - 1][cy2]) return;

        visited[0][cx2][cy2 - 1][cy2] = true;

        pq.add(new Position(cnt + 1, cx2, cy2 - 1, cx2, cy2, "row"));
      }

      @Override
      public void rotateRightUp(int cnt, int cx1, int cy1, int cx2, int cy2) {
        if(cy1 == colSize - 1) return;
        if(boardInfo[cx2][cy2 + 1] == 1) return;
        if(boardInfo[cx1][cy1 + 1] == 1) return;
        if(visited[0][cx1][cy1][cy1 + 1]) return;

        visited[0][cx1][cy1][cy1 + 1] = true;

        pq.add(new Position(cnt + 1, cx1, cy1, cx1, cy1 + 1, "row"));
      }

      @Override
      public void rotateRightDown(int cnt, int cx1, int cy1, int cx2, int cy2) {
        if(cy1 == colSize - 1) return;
        if(boardInfo[cx1][cy1 + 1] == 1) return;
        if(boardInfo[cx2][cy2 + 1] == 1) return;
        if(visited[0][cx2][cy2][cy2 + 1]) return;

        visited[0][cx2][cy2][cy2 + 1] = true;

        pq.add(new Position(cnt + 1, cx2, cy2, cx2 ,cy2 + 1, "row"));
      }
    };

    public int x1, y1, x2, y2;

    Direction() {}

    Direction(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }

    public static Direction getDirection(String type) {
      Direction direction = Direction.valueOf(type.toUpperCase());
      return direction;
    }

    // 왼쪽으로 이동
    public abstract void moveLeft(int cnt, int cx1, int cy1, int cx2, int cy2);
    // 오른쪽으로 이동
    public abstract void moveRight(int cnt, int cx1, int cy1, int cx2, int cy2);
    // 위로 이동
    public abstract void moveUp(int cnt, int cx1, int cy1, int cx2, int cy2);
    // 아래로 이동
    public abstract void moveDown(int cnt, int cx1, int cy1, int cx2, int cy2);
    // 왼쪽 위로 회전
    public abstract void rotateLeftUp(int cnt, int cx1, int cy1, int cx2, int cy2);
    // 왼쪽 아래로 회전
    public abstract void rotateLeftDown(int cnt, int cx1, int cy1, int cx2, int cy2);
    // 오른쪽 위로 회전
    public abstract void rotateRightUp(int cnt, int cx1, int cy1, int cx2, int cy2);
    // 오른쪽 아래로 회전
    public abstract void rotateRightDown(int cnt, int cx1, int cy1, int cx2, int cy2);

  }

  public static void main(String[] args) {

//    int[][] board = {
//        {0, 0, 0, 1, 1},
//        {0, 0, 0, 1, 0},
//        {0, 1, 0, 1, 1},
//        {1, 1, 0, 0, 1},
//        {0, 0, 0, 0, 0}
//    };

//    int[][] board = {
//        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
//        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
//        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//        {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
//        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//        {0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
//        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//    };

    int[][] board = {
        {0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 0, 0}
    };


    System.out.println(solution(board));


  }

}
