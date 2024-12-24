package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 캐슬디펜스 {

    private static final int EMPTY = 0;
    private static final int ENEMY = 1;
    private static final int ARCHER_COUNT = 3;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int D = Integer.parseInt(input.nextToken());
        int[][] board = new int[N][M];

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        setArcher(board,0, 0, new int[3], D);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int playGame(int[] archers, int[][] board, int D) {
        int enemiesSlain = 0;
        int result = 0;
        // 처치된 적의 정보
        int[][] enemies = new int[board.length][board[0].length];

        // 각 행을 올려가면서 적을 처치
        for(int line = board.length; line > 0; line--) {
            for(int archer : archers) {

                for(int dist = 1; dist <= D; dist++) {

                    int slainCount = shoot(line, archer, dist, enemies, board);

                    // 처치된 적이 없다면 더 긴 사거리로 처치시도
                    if(slainCount < 0) continue;

                    // 처치된 적이 있다면 break;
                    enemiesSlain += slainCount;
                    break;
                }
            }
        }
        result = Math.max(result, enemiesSlain);
        return result;
    }


    private static int shoot(int line, int col, int distance, int[][] enemies, int[][] board) {

        // 각 궁수가 화살을 쏴서 처치한 적의 수를 리턴
        // 이미 처치된 적인지 확인하는 로직이 필요

        // col : 현재 궁수의 열 번호
        // 궁수의 열에서 처치할 수 있는 열까지 루프
        for(int enemyCol = col - distance; enemyCol <= col + distance; enemyCol++) {

            // 열을 고려하고 화살을 쏠 수 있는 행 (남은 사거리)
            int enemyLine = line - (distance - Math.abs(enemyCol - col));

            if(enemyLine < 0 || enemyLine >= line || enemyCol < 0 || enemyCol >= enemies[0].length) {
                continue;
            }

            if(board[enemyLine][enemyCol] == EMPTY) {
                continue;
            }

            // 처치되지 않은 적인 경우
            if(enemies[enemyLine][enemyCol] == 0) {
                enemies[enemyLine][enemyCol] = line;
                return 1;
            }

            // 이미 처치되어 있는 적인 경우
            if(enemies[enemyLine][enemyCol] == line) {
                return 0;
            }
        }
        return -1;
    }


    private static void setArcher(int[][] board, int count, int start, int[] archers, int D) {

        if(count == ARCHER_COUNT) {
            answer = Math.max(answer, playGame(archers, board, D));
            return;
        }

        for(int i = start; i < board[0].length; i++) {
            archers[count] = i;
            setArcher(board, count + 1, i + 1, archers, D);
        }
    }

}
