package programmers.graph;

import java.util.PriorityQueue;

public class 리코쳇로봇 {

  private static final Character EMPTY = '.';
  private static final Character OBSTACLE = 'D';
  private static final Character GOAL = 'G';
  private static final Character ROBOT = 'R';

  public int solution(String[] board) {
    int answer = -1;

    PriorityQueue<Coordinate> queue = new PriorityQueue<>();
    char[][] map = new char[board.length][board[0].length()];
    boolean[][] visited = new boolean[board.length][board[0].length()];

    for(int i = 0; i < board.length; i++) {
      String row = board[i];
      for(int j = 0; j < row.length(); j++) {
        char state = row.charAt(j);
        if(state == ROBOT) {
          queue.add(new Coordinate(i, j, 0));
          visited[i][j] = true;
        }
        map[i][j] = state;
      }
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    while(!queue.isEmpty()) {

      Coordinate coordinate = queue.poll();

      int cx = coordinate.x;
      int cy = coordinate.y;
      int count = coordinate.count;

      if(map[cx][cy] == GOAL) {
        answer = count;
        break;
      }

      for(int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        while(true) {
          if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
            nx -= dx[i];
            ny -= dy[i];

            if(!visited[nx][ny]) {
              visited[nx][ny] = true;
              queue.add(new Coordinate(nx, ny, count + 1));
            }
            break;
          }

          if(map[nx][ny] == OBSTACLE) {

            nx -= dx[i];
            ny -= dy[i];

            if(!visited[nx][ny]) {
              visited[nx][ny] = true;
              queue.add(new Coordinate(nx, ny, count + 1));
            }
            break;
          }
          nx += dx[i];
          ny += dy[i];
        }
      }

    }

    return answer;
  }


  private class Coordinate implements Comparable<Coordinate> {
    int x, y;
    int count;

    Coordinate(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
    @Override
    public int compareTo(Coordinate o) {
      return this.count - o.count;
    }
  }
}
