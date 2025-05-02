package baekJoon.Implementation;

import java.io.*;
import java.util.StringTokenizer;

public class 사다리조작 {

    private static int n;
    private static int h;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        h = Integer.parseInt(input.nextToken());

        int[][] ladder = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            ladder[a][b] = 1;
        }

        // 추가할 수 있는 가로 선의 개수는 0 ~ 3
        answer = 4;
        for (int i = 0; i <= 3; i++) {
            if (answer < 4) break;
            dfs(ladder, 1, 0, i);
        }
        if (answer == 4) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
    private static void dfs(int[][] ladder, int row, int count, int maxLine) {

        if (count == maxLine) {
            // i번 세로선의 결과가 i번이 나오는 사다리인지 확인
            if (checkLadder(ladder)) {
                // 원하는 사다리 형태인 경우
                answer = Math.min(answer, maxLine);
            }
            return;
        }

        for (int nextRow = row; nextRow <= h; nextRow++) {
            for (int col = 1; col < n; col++) {
                // 이미 사다리가 있는 경우
                if (ladder[nextRow][col] == 1) {
                    continue;
                }

                // 왼쪽 오른쪽 사다리가 연결되어 있으면 안됌.
                if (ladder[nextRow][col - 1] == 1) {
                    continue;
                }

                if (ladder[nextRow][col + 1] == 1) {
                    continue;
                }

                // 사다리 연결 후 높이 내려가도록 설정
                ladder[nextRow][col] = 1;
                dfs(ladder, nextRow, count + 1, maxLine);
                ladder[nextRow][col] = 0;
            }
        }
    }

    private static boolean checkLadder(int[][] ladder) {
        // 첫 번째 세로선부터 출발
        for (int i = 1; i <= n; i++) {

            int cLine = i; // 사다리를 움직일때 위치한 세로선의 번호

            for (int height = 1; height <= h; height++) {
                // 현재 위치가 사다리로 연결되어 있는 경우 -> 로 이동
                if (ladder[height][cLine] == 1) {
                    cLine += 1;
                }
                // 현재 위치의 왼쪽에 사다리가 연결되어 있는 경우 <- 로 이동
                else if (ladder[height][cLine - 1] == 1) {
                    cLine -= 1;
                }
                // 사다리로 연결이 되어 있지 않은 경우 그냥 아래로 이동
            }
            if (cLine != i) return false;
        }
        return true;
    }
}
