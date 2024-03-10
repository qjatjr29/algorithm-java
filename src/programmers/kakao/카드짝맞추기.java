package programmers.kakao;

import java.util.ArrayDeque;
import java.util.Queue;

public class 카드짝맞추기 {

    private static final int EMPTY = 0;
    private static final int MAX_HEIGHT = 4;
    private static final int MAX_SIDE = 4;
    private static int answer;
    private static int[][] map;
    private static boolean[] isExistCard;
    private static Point[][] cardPoints;

    public int solution(int[][] board, int r, int c) {
        map = board;
        isExistCard = new boolean[7];
        cardPoints = new Point[7][2];
        int cardTotalCount = 0;

        for(int i = 0; i < MAX_HEIGHT; i++) {
            for(int j = 0 ; j < MAX_SIDE; j++) {
                if(board[i][j] == EMPTY) {
                    continue;
                }
                int cardNumber = board[i][j];

                if(isExistCard[cardNumber]) {
                    cardPoints[cardNumber][1] = new Point(i, j, 0);
                    cardTotalCount++;
                    continue;
                }
                isExistCard[cardNumber] = true;
                cardPoints[cardNumber][0] = new Point(i, j ,0);
            }
        }

        // 1. 카드 선택할 순서를 정하면서 최소 경로 찾기
        answer = Integer.MAX_VALUE;

        move(0, 0, r, c, cardTotalCount);

        return answer;
    }

    public void move(int index, int count, int cx, int cy, int cardCount) {
        // 모든 순서를 다 돔
        if (index == cardCount) {
            answer = Math.min(answer, count);
            return;
        }

        //진행
        for (int i = 1; i <= 6; i++) {

            if (!isExistCard[i]) continue;

            //최소 이동 거리 계산 아래 두 케이스 비교

            // 2 - 1. 카드 A를 먼저 찾기
            int move1 =
                    calculateDistance(cx, cy, cardPoints[i][0].x, cardPoints[i][0].y) +
                            calculateDistance(cardPoints[i][0].x, cardPoints[i][0].y, cardPoints[i][1].x, cardPoints[i][1].y)
                            + 2;

            // 2 - 2. 카드 B를 먼저 찾기
            int move2 =
                    calculateDistance(cx, cy, cardPoints[i][1].x, cardPoints[i][1].y) +
                            calculateDistance(cardPoints[i][1].x, cardPoints[i][1].y, cardPoints[i][0].x, cardPoints[i][0].y)
                            + 2;

            // 다음 순서로 진행
            isExistCard[i] = false;
            map[cardPoints[i][0].x][cardPoints[i][0].y] = 0;
            map[cardPoints[i][1].x][cardPoints[i][1].y] = 0;

            // 카드 A를 먼저 찾았을 때 가 더 거리가 짧을 때 => B가 도착점.
            if (move1 < move2){
                move(index + 1, count + move1, cardPoints[i][1].x, cardPoints[i][1].y, cardCount);
            }

            // A가 도착점 -> A가 다음의 시자점
            else {
                move(index + 1, count + move2, cardPoints[i][0].x, cardPoints[i][0].y, cardCount);
            }

            map[cardPoints[i][0].x][cardPoints[i][0].y] = i;
            map[cardPoints[i][1].x][cardPoints[i][1].y] = i;
            isExistCard[i] = true;
        }
    }

    private int calculateDistance(int sx, int sy, int tx, int ty) {

        Queue<Point> q = new ArrayDeque<>();
        boolean[][] check = new boolean[4][4];

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        q.add(new Point(sx, sy, 0));
        check[sx][sy] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            // 도착
            if (cur.x == tx && cur.y == ty) {
                return cur.count;
            }

            // 1. 4반향 1칸씩 계산
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= MAX_HEIGHT || ny < 0 || ny >= MAX_SIDE || check[nx][ny]) {
                    continue;
                }
                check[nx][ny] = true;
                q.add(new Point(nx, ny,  cur.count + 1));
            }

            // 2. ctrl + 이동 계산
            for (int i = 0; i < 4; i++) {
                int nx = cur.x;
                int ny = cur.y;

                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    // 범위 넘어간 경우
                    if (nx == MAX_HEIGHT || ny == MAX_SIDE || nx == -1 || ny == -1) {
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                    // 카드를 만난 경우 멈춤
                    if (map[nx][ny] != EMPTY) {
                        break;
                    }
                }
                if (check[nx][ny]) {
                    continue;
                }
                check[nx][ny] = true;
                q.add(new Point(nx, ny, cur.count + 1));
            }
        }
        return -1;
    }

    private class Point {
        int x;
        int y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
