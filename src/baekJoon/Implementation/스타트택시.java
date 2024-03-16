package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트택시 {

    private static final int WALL = 1;
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int fuel = Integer.parseInt(input.nextToken());

        int[][] map = new int[N + 1][N + 1];
        int[][] isPassenger = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
                isPassenger[i][j] = -1;
            }
        }

        input = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(input.nextToken());
        int ty = Integer.parseInt(input.nextToken());

        Taxi taxi = new Taxi(tx, ty, fuel);
        Passenger[] passengers = new Passenger[M];

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int px = Integer.parseInt(input.nextToken());
            int py = Integer.parseInt(input.nextToken());
            int dx = Integer.parseInt(input.nextToken());
            int dy = Integer.parseInt(input.nextToken());
            isPassenger[px][py] = i;
            passengers[i] = new Passenger(i, px, py, dx, dy);
            passengers[i].calculateDistance(map);
        }

        int arrivePassenger = 0;
        boolean isFinish = false;

        while(true) {

            if(arrivePassenger == passengers.length) {
                isFinish = true;
                break;
            }
            // 다음 손님
            int[] nextPassengerInfo = getNextPassenger(map, isPassenger, passengers, taxi);

            int number = nextPassengerInfo[0];
            int count = nextPassengerInfo[1];

            //
            if(number == -1) {
                break;
            }
            Passenger passenger = passengers[number];

//            System.out.println("========next passenger - " + number);
//            System.out.println("distance - " + passenger.distance);
//            System.out.println("---------");
//            System.out.println("남은 연료 - " + taxi.fuel);

            // 다음 손님까지의 위치까지 못가는 경우.
            if(taxi.fuel < count) {
                break;
            }

            // 손님 위치까지 이동을 하자.
            taxi.fuel -= count;

            // 손님의 목적지까지 못가는 경우
            if(taxi.fuel < passenger.distance) {
                break;
            }

            // 이동
            taxi.fuel += passenger.distance;
            taxi.taxiPoint = passenger.destinationPoint;
            passenger.isArrive = true;
            isPassenger[passenger.startPoint.x][passenger.startPoint.y] = -1;
            arrivePassenger++;
        }

        if(isFinish) {
            bw.write(String.valueOf(taxi.fuel));
        }
        if(!isFinish) {
            bw.write("-1");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] getNextPassenger(int[][] map, int[][] isPassenger, Passenger[] passengers, Taxi taxi) {

        int[] result = new int[2];
        int moveCount = 987654321;

        int passengerNumber = -1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int startX = taxi.taxiPoint.x;
        int startY = taxi.taxiPoint.y;

        boolean[][] checked = new boolean[map.length][map[0].length];

        checked[startX][startY] = true;

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(taxi.taxiPoint);

        while(!queue.isEmpty()) {

            Point cPoint = queue.poll();
            int cx = cPoint.x;
            int cy = cPoint.y;

            if(isPassenger[cx][cy] != -1) {

                Passenger passenger = passengers[isPassenger[cx][cy]];
                if(moveCount > cPoint.count) {
                    moveCount = cPoint.count;
                    passengerNumber = isPassenger[cx][cy];
                }

                if(moveCount == cPoint.count) {
                    if(passengers[passengerNumber].startPoint.x > passenger.startPoint.x) {
                        passengerNumber = isPassenger[cx][cy];
                    }
                    if(passengers[passengerNumber].startPoint.x == passenger.startPoint.x) {
                        if(passengers[passengerNumber].startPoint.y > passenger.startPoint.y) {
                            passengerNumber = isPassenger[cx][cy];
                        }
                    }
                }
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx <= 0 || nx >= map.length || ny <= 0 || ny >= map[0].length || checked[nx][ny]) {
                    continue;
                }

                if(map[nx][ny] == WALL) {
                    continue;
                }

                checked[nx][ny] = true;
                queue.add(new Point(nx, ny, cPoint.count + 1));
            }

        }

        result[0] = passengerNumber;
        result[1] = moveCount;
        return result;
    }


    private static class Taxi {
        Point taxiPoint;
        int fuel;

        public Taxi(int x, int y, int fuel) {
            taxiPoint = new Point(x, y);
            this.fuel = fuel;
        }
    }

    private static class Passenger {
        Point startPoint;
        Point destinationPoint;
        boolean isArrive;
        int distance;
        int id;

        public Passenger(int id, int sx, int sy, int dx, int dy) {
            this.id = id;
            startPoint = new Point(sx, sy);
            destinationPoint = new Point(dx, dy);
            this.isArrive = false;
            this.distance = 987654321;
        }

        public boolean isArrive() {
            return this.isArrive;
        }

        public void calculateDistance(int[][] map) {

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            boolean[][] checked = new boolean[map.length][map[0].length];

            checked[startPoint.x][startPoint.y] = true;

            PriorityQueue<Point> queue = new PriorityQueue<>();
            queue.add(startPoint);

            while(!queue.isEmpty()) {

                Point cPoint = queue.poll();
                int cx = cPoint.x;
                int cy = cPoint.y;

                if(cx == destinationPoint.x && cy == destinationPoint.y) {
                    this.distance = cPoint.count;
                    break;
                }

                for(int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if(nx <= 0 || nx >= map.length || ny <= 0 || ny >= map[0].length || checked[nx][ny]) {
                        continue;
                    }

                    if(map[nx][ny] == WALL) {
                        continue;
                    }

                    checked[nx][ny] = true;
                    queue.add(new Point(nx, ny, cPoint.count + 1));
                }

            }

        }
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int count;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.count = 0;
        }

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }


        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }
}
