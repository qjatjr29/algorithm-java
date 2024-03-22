package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 사다리조작 {

    private static final int CONNECT = 1;
    private static final int LIMIT = 3;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int H = Integer.parseInt(input.nextToken());
        answer = -1;

        int[][] connect = new int[N + 1][H + 1];

        // i번째 세로선이 h 점선 위치에서 어느 세로선에 있는지에 대한 정보
        int[][] position = new int[N + 1][H + 1];

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(input.nextToken());
            int line = Integer.parseInt(input.nextToken());

            connect[line][height] = CONNECT;
        }

        for(int i = 0; i <= LIMIT; i++) {
            if(answer != -1) {
                break;
            }
            modify(0, i, connect, 1);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void modify(int count, int max, int[][] connect, int height) {

        if(count == max) {
            if(isCorrectPath(connect)) {
                answer = max;
            }
            return;
        }

        // 현재 높이에서 만들 수 있는 가로선을 만들어 재귀적으로 확인
        for(int h = height; h < connect[0].length; h++) {
            for(int line = 1; line < connect.length - 1; line++) {
                if(connect[line][h] == CONNECT || connect[line - 1][h] == CONNECT || connect[line + 1][h] == CONNECT) {
                    continue;
                }

                connect[line][h] = CONNECT;
                modify(count + 1, max, connect, h);
                connect[line][h] = 0;
            }
        }
    }


    private static boolean isCorrectPath(int[][] connect) {

        int lines = connect.length;
        int height = connect[0].length;

        boolean isCorrect = true;
        for(int line = 1; line < lines; line++) {
            int start = line;
            for(int h = 1; h < height; h++) {
                if (connect[start][h] == CONNECT) {
                    start = start + 1;
                    continue;
                }
                if (connect[start - 1][h] == CONNECT) {
                    start = start - 1;
                }
            }
            if(start != line) {
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }

}
