package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 로봇시뮬레이션 {

  private static int[][] map;
  private static int row, col;
  private static Robot[] robots;
  private static int crashX, crashY;
  private static int[] dx = {0, -1, 0, 1};
  private static int[] dy = {1, 0, -1, 0};
  private enum Direction {
    N(1),
    W(2),
    S(3),
    E(4);

    private int value;

    Direction(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static Direction getDirection(int value) {
      return Arrays.stream(Direction.values())
          .filter(d -> d.getValue() == value)
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
  }

  private enum Command {
    L{
      @Override
      public void move(int count, Robot robot, int index) {
        int newDirection = robot.direction.getValue();

        for(int i = 0; i < count; i++) {
          newDirection++;
          if(newDirection > 4) newDirection = 1;
        }
        robots[index].direction = Direction.getDirection(newDirection);
      }
    },
    R{
      @Override
      public void move(int count, Robot robot, int index) {
        int newDirection = robot.direction.getValue();

        for(int i = 0; i < count; i++) {
          newDirection--;
          if(newDirection < 1) newDirection = 4;
        }
        robots[index].direction = Direction.getDirection(newDirection);
      }
    },
    F{
      @Override
      public void move(int count, Robot robot, int index) {
        int direction = robot.direction.getValue();
        int cx = robot.x;
        int cy = robot.y;
        map[cx][cy] = 0;
        for(int i = 0; i < count; i++) {
          cx += dx[direction - 1];
          cy += dy[direction - 1];
          if(cx <= 0 || cx > row || cy <= 0 || cy > col) {
            crashX = index;
            throw new WallConflictException();
          }
          if(map[cx][cy] != 0) {
            crashX = index;
            crashY = map[cx][cy];
            throw new RobotConflictException();
          }
        }
        map[cx][cy] = index;
        robot.x = cx;
        robot.y = cy;
      }
    };

    public abstract void move(int count, Robot robot, int index);

  }

  private static class RobotConflictException extends RuntimeException { }

  private static class WallConflictException extends RuntimeException {}

  private static class Robot {
    int x, y;
    Direction direction;

    public Robot(int x, int y, Direction direction) {
      this.x = x;
      this.y = y;
      this.direction = direction;
    }
  }


  public static void main(String[] args) throws IOException {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());

    map = new int[row + 1][col + 1];

    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    robots = new Robot[N + 1];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      Direction direction = Direction.valueOf(d);

      robots[i + 1] = new Robot(x, y, direction);
      map[x][y] = i + 1;
    }

    try {
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());

        int robot = Integer.parseInt(st.nextToken());
        String cmd = st.nextToken();
        int count = Integer.parseInt(st.nextToken());

        Command command = Command.valueOf(cmd);
        command.move(count, robots[robot], robot);
      }
    } catch (RobotConflictException e) {
      bw.write("Robot " + crashX + " crashes into robot " + crashY);
    } catch (WallConflictException e) {
      bw.write("Robot " + crashX + " crashes into the wall");
    }
    if(crashX == 0) bw.write("OK");
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }
}
