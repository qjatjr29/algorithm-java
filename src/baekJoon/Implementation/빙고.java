package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 빙고 {

    private static final int BOARD_SIZE = 5;
    private static final int BINGO_COUNT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input;

        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        int[][] answer = new int[BOARD_SIZE][BOARD_SIZE];

        int[] rowBingo = new int[BOARD_SIZE];
        int[] colBingo = new int[BOARD_SIZE];
        int[] crossBingo = new int[2];
        Map<Integer, BingoNode> bingoNodeMap = new HashMap<>();

        for(int i = 0; i < BOARD_SIZE; i++) {
            input = new StringTokenizer(br.readLine());

            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
                bingoNodeMap.put(board[i][j], new BingoNode(i, j));
            }
        }

        int count = 0;
        int bingoCount = 0;
        for(int i = 0; i < BOARD_SIZE; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < BOARD_SIZE; j++) {
                answer[i][j] = Integer.parseInt(input.nextToken());

                if(bingoCount < BINGO_COUNT) {
                    BingoNode bingoNode = bingoNodeMap.get(answer[i][j]);

                    int row = bingoNode.row;
                    int col = bingoNode.col;

                    rowBingo[row]++;
                    colBingo[col]++;

                    if(rowBingo[row] == BOARD_SIZE) bingoCount++;
                    if(colBingo[col] == BOARD_SIZE) bingoCount++;

                    if(row == col) {
                        crossBingo[0]++;
                        if(crossBingo[0] == BOARD_SIZE) bingoCount++;
                    }

                    if(row + col == BOARD_SIZE - 1) {
                        crossBingo[1]++;
                        if(crossBingo[1] == BOARD_SIZE) bingoCount++;
                    }
                    count++;
                }

            }
        }


        bw.write(String.valueOf(count));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class BingoNode {
        int row, col;

        public BingoNode(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
