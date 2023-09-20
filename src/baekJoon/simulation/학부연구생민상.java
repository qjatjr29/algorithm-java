package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 학부연구생민상 {

    private static final int AIR_CONDITIONER = 9;
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int answer = 0;
        int[][] lab = new int[N][M];
        Queue<AirFlow> airFlowQueue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(input.nextToken());
                if(lab[i][j] == AIR_CONDITIONER) {
                    visited[i][j] = true;
                    airFlowQueue.add(new AirFlow(Direction.NORTH, i, j));
                    airFlowQueue.add(new AirFlow(Direction.SOUTH, i, j));
                    airFlowQueue.add(new AirFlow(Direction.EAST, i, j));
                    airFlowQueue.add(new AirFlow(Direction.WEST, i, j));
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while(!airFlowQueue.isEmpty()) {

            AirFlow here = airFlowQueue.poll();

            int cx = here.x;
            int cy = here.y;

            int dir = here.direction.getValue();

            while(true) {
                cx += dx[dir];
                cy += dy[dir];
                if(cx < 0 || cx >= N || cy < 0 || cy >= M) break;
                visited[cx][cy] = true;
                if(lab[cx][cy] == AIR_CONDITIONER) break;
                if(lab[cx][cy] != EMPTY) {
                    Item item = Item.getItem(lab[cx][cy]);
                    if(item.getValue() == 1 || item.getValue() == 2) {
                        if(isCanMove(here.direction, item)) continue;
                        else break;
                    }
                    airFlowQueue.add(new AirFlow(reflect(here.direction, item), cx, cy));
                    break;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j]) answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static Direction reflect(Direction dir, Item item) {
        int d = dir.getValue();
        int i = item.getValue();
        if(i == 3) return Direction.getDirection((d + 2) % 4);
        else return Direction.getDirection(3 - d);
    }

    private static boolean isCanMove(Direction dir, Item item) {
        int d = dir.getValue();
        int i = item.getValue();
        if(d == 0 || d == 1){
            return i == 1;
        }
        else return i == 2;
    }

    private static class AirFlow {
        Direction direction;
        int x, y;

        public AirFlow(Direction direction, int x, int y) {
            this.direction = direction;
            this.x = x;
            this.y = y;
        }
    }

    public enum Item {

        ITEM1 (1),
        ITEM2 (2),
        ITEM3 (3),
        ITEM4 (4);

        int value;

        Item(int value) {
            this.value = value;
        }

        public static Item getItem(int value) {
            return Arrays.stream(Item.values())
                .filter(item -> item.getValue() == value)
                .findFirst()
                .orElseGet(() -> Item.ITEM1);
        }

        private int getValue() {
            return this.value;
        }
    }

    public enum Direction {
        NORTH(0),
        SOUTH(1),
        EAST(2),
        WEST(3);

        int value;

        Direction(int value) {
            this.value = value;
        }

        public static Direction getDirection(int value) {
            return Arrays.stream(Direction.values())
                .filter(dir -> dir.getValue() == value)
                .findFirst()
                .orElseGet(() -> Direction.EAST);
        }

        public int getValue() {
            return this.value;
        }
    }

}
