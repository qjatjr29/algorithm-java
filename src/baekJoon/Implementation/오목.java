package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class 오목 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] board = new char[19][19];
        int[] dx = {0, 1, 1, -1};
        int[] dy = {1, 0, 1, 1};

        for (int i = 0; i < 19; i++) {
            String input = br.readLine();
            for (int j = 0, index = 0; j < 19; index += 2, j++) {
                board[i][j] = input.charAt(index);
            }
        }

        for (int j = 0; j < 19; j++) {
            for (int i = 0; i < 19; i++) {
                if (board[i][j] == '1' || board[i][j] == '2') {
                    for (int k = 0; k < 4; k++) {
                        int nx = i; // x좌표
                        int ny = j; // y좌표
                        int cnt = 1; // 일치하는 바둑알의 개수

                        // 증가하는 방향 탐색
                        while (true) {
                            nx += dx[k];
                            ny += dy[k];
                            if (0 <= nx && nx < 19 && 0 <= ny && ny < 19) {
                                if (board[i][j] == board[nx][ny])
                                    cnt++;
                                else {
                                    break;
                                }
                            } else
                                break;
                        }
                        nx = i;
                        ny = j;

                        // 증가하는 방향의 반대방향 탐색
                        while (true) {
                            nx -= dx[i];
                            ny -= dy[i];
                            if (0 <= nx && nx < 19 && 0 <= ny && ny < 19) {
                                if (board[i][j] == board[nx][ny])
                                    cnt++;
                                else
                                    break;

                            } else
                                break;
                        }

                        // 같은 오목눈이 5개라면
                        if (cnt == 5) {
                            System.out.println(board[i][j]);
                            System.out.println((i + 1) + " " + (j + 1));
                            return;
                        }

                    }
                }
            }
        }

//		아무도 이기지 않았을 경우
    }
}