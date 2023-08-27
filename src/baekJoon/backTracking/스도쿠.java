package baekJoon.backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스도쿠 {

    private static final int EMPTY = 0;
    private static final int BOARD_SIZE = 9;
    private static final int AREA_SIZE = 3;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input;

        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        Queue<int[]> emptyQueue = new LinkedList<>();

        for(int i = 0; i < BOARD_SIZE; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
                if(board[i][j] == EMPTY) emptyQueue.add(new int[] {i, j});
            }
        }

        dfs(0, 0, board);
    }

    private static void dfs(int x, int y, int[][] board) {

        if(y == BOARD_SIZE) {
            dfs(x + 1, 0, board);
            return;
        }

        if(x == BOARD_SIZE) {
            for(int i = 0; i < BOARD_SIZE; i++) {
                for(int j = 0; j < BOARD_SIZE; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        if(board[x][y] == EMPTY) {
            for(int i = 1; i <= BOARD_SIZE; i++) {
                if(!isContainNumber(x, y, i, board)) {
                    board[x][y] = i;
                    dfs(x, y + 1, board);
                }
            }
            board[x][y] = EMPTY;
            return;
        }

        dfs(x, y + 1, board);
    }
    private static boolean isContainNumber(int x, int y, int number, int[][] board) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(board[x][i] == number) return true;
        }

        for(int i = 0; i < BOARD_SIZE; i++) {
            if(board[i][y] == number) return true;
        }

        int areaX = (x / 3) * 3;
        int areaY = (y / 3) * 3;

        for(int i = areaX; i < areaX + AREA_SIZE; i++) {
            for(int j = areaY; j < areaY + AREA_SIZE; j++) {
                if(board[i][j] == number) return true;
            }
        }
        return false;
    }

}
