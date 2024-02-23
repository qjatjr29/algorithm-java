package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 거북이 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {
            input = new StringTokenizer(br.readLine());
            String command = input.nextToken();

            Turtle turtle = new Turtle(0, 0, Direction.UP);
            int minX = 0;
            int minY = 0;
            int maxX = 0;
            int maxY = 0;

            for(int index = 0; index < command.length(); index++) {
                char cmd = command.charAt(index);
                switch (cmd) {
                    case 'F':
                        turtle.moveForward();
                        break;
                    case 'B':
                        turtle.moveBack();
                        break;
                    case 'L':
                        turtle.turnLeft();
                        break;
                    case 'R':
                        turtle.turnRight();
                        break;
                    default:
                        break;
                }

                minX = Math.min(minX, turtle.x);
                minY = Math.min(minY, turtle.y);
                maxX = Math.max(maxX, turtle.x);
                maxY = Math.max(maxY, turtle.y);
            }
            int answer = (maxX - minX) * (maxY - minY);
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Turtle {
        int x;
        int y;
        Direction direction;

        public Turtle(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void moveForward() {
            int[] next = this.direction.move(this.x, this.y);
            this.x = next[0];
            this.y = next[1];
        }

        public void moveBack() {
            Direction back = this.direction.turnBack();
            int[] next = back.move(this.x, this.y);
            this.x = next[0];
            this.y = next[1];
        }

        public void turnLeft() {
            this.direction = this.direction.turnLeft();
        }

        public void turnRight() {
            this.direction = this.direction.turnRight();
        }
    }

    public enum Direction {
        UP {
            @Override
            int[] move(int x, int y) {
                x -= 1;
                return new int[] {x, y};
            }

            @Override
            Direction turnLeft() {
                return Direction.LEFT;
            }

            @Override
            Direction turnRight() {
                return Direction.RIGHT;
            }

            @Override
            Direction turnBack() {
                return Direction.DOWN;
            }
        },
        DOWN {
            @Override
            int[] move(int x, int y) {
                x += 1;
                return new int[] {x, y};
            }

            @Override
            Direction turnLeft() {
                return Direction.RIGHT;
            }

            @Override
            Direction turnRight() {
                return Direction.LEFT;
            }

            @Override
            Direction turnBack() {
                return Direction.UP;
            }
        },
        LEFT {
            @Override
            int[] move(int x, int y) {
                y -= 1;
                return new int[] {x, y};
            }

            @Override
            Direction turnLeft() {
                return Direction.DOWN;
            }

            @Override
            Direction turnRight() {
                return Direction.UP;
            }

            @Override
            Direction turnBack() {
                return Direction.RIGHT;
            }
        },
        RIGHT {
            @Override
            int[] move(int x, int y) {
                y += 1;
                return new int[] {x, y};
            }

            @Override
            Direction turnLeft() {
                return Direction.UP;
            }

            @Override
            Direction turnRight() {
                return Direction.DOWN;
            }

            @Override
            Direction turnBack() {
                return Direction.LEFT;
            }
        };

        abstract int[] move(int x, int y);
        abstract Direction turnLeft();
        abstract Direction turnRight();
        abstract Direction turnBack();

    }
}
