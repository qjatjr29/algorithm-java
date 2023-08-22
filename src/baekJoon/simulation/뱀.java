package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀 {

    private static final int EMPTY = 0;
    private static final int APPLE = 1;
    private static final int SNAKE = 2;

    private static Command[] commands;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int[][] board = new int[size + 1][size + 1];
        int time = 0;

        input = new StringTokenizer(br.readLine());
        int appleCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < appleCount; i++) {
            input = new StringTokenizer(br.readLine());
            int appleX = Integer.parseInt(input.nextToken());
            int appleY = Integer.parseInt(input.nextToken());
            board[appleX][appleY] = APPLE;
        }

        input = new StringTokenizer(br.readLine());
        int conversionCnt = Integer.parseInt(input.nextToken());
        commands = new Command[conversionCnt];

        for(int i = 0; i < conversionCnt; i++) {
            input = new StringTokenizer(br.readLine());
            int convertTime = Integer.parseInt(input.nextToken());
            String direction = input.nextToken();
            commands[i] = new Command(convertTime, direction);
        }

        Arrays.sort(commands);

        time = move(1, 1, board);

        bw.write(String.valueOf(time));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();


    }

    private static int move(int startX, int startY, int[][] board) {

        int usedTime = 0;
        int rotateCnt = 0;
        int size = board.length;
        Queue<Snake> queue = new LinkedList<>();

        List<int[]> nodeList = new ArrayList<>();
        nodeList.add(new int[]{startX, startY});

        queue.add(new Snake(nodeList, Direction.RIGHT));
        int cx = startX;
        int cy = startY;

        while(!queue.isEmpty()) {

            usedTime++;
            Snake snake = queue.poll();

            Direction direction = snake.direction;

            int nextX = cx + direction.dx;
            int nextY = cy + direction.dy;

            if(isFinish(nextX, nextY, size, snake.nodes)) break;

            if(board[nextX][nextY] == APPLE) {
                board[nextX][nextY] = EMPTY;
                snake.nodes.add(new int[]{nextX, nextY});
            }
            else {
                snake.nodes.add(new int[]{nextX, nextY});
                snake.nodes.remove(0);
            }

            cx = nextX;
            cy = nextY;

            if(rotateCnt < commands.length) {
                if(usedTime == commands[rotateCnt].time) {
                    direction = direction.rotate(commands[rotateCnt].direction);
                    snake.direction = direction;
                    rotateCnt += 1;
                }
            }

            queue.add(snake);
        }

        return usedTime;
    }

    private static boolean isFinish(int x, int y, int size, List<int[]> snakes) {
        if(x <= 0 || x >= size || y <= 0 || y >= size) return true;

        for(int[] snake : snakes) {
            if(x == snake[0] && y == snake[1]) return true;
        }

        return false;
    }

    private static class Command implements Comparable<Command> {
        int time;
        String direction;

        public Command(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }

        @Override
        public int compareTo(Command o) {
            return this.time - o.time;
        }
    }

    private static class Snake {
        List<int[]> nodes;
        Direction direction;

        public Snake(List<int[]> nodes, Direction direction) {
            this.nodes = nodes;
            this.direction = direction;
        }

        public void addNode(int x, int y) {
            nodes.add(new int[]{x, y});
        }
    }

    private enum Direction {

        RIGHT(0, 0, 1),
        DOWN(1, 1, 0),
        LEFT(2, 0, -1),
        UP(3, -1, 0);

        int dir, dx, dy;

        Direction(int dir, int dx, int dy) {
            this.dir = dir;
            this.dx = dx;
            this.dy = dy;
        }

        public static Direction getDirection(int order) {
            return Arrays.stream(Direction.values())
                .filter(direction -> direction.getDir() == order)
                .findFirst()
                .get();
        }

        public Direction rotate(String cmd) {
            int currentDir = this.dir;

            int dir = Objects.equals(cmd, "L") ? -1 : 1;

            int next = (currentDir + dir) % 4;
            if(next == -1) next = 3;
            return Direction.getDirection(next);
        }

        public int getDir() {
            return this.dir;
        }
    }
}
