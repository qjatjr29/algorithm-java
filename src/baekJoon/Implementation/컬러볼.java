package baekJoon.Implementation;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 컬러볼 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        Ball[] balls = new Ball[n];
        int[][] counts = new int[n + 1][2];
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(input.nextToken());
            int size = Integer.parseInt(input.nextToken());
            balls[i] = new Ball(i, color, size);
        }

        Arrays.sort(balls, (a, b) -> {
            return a.size - b.size;
        });

        int prevSize = 0;
        int sameSize = 0; // 같은 사이즈의 볼들의 사이즈 합
        int totalSize = 0; // 모든 볼들의 사이즈 합

        for (int i = 0; i < n; i++) {
            Ball ball = balls[i];
            // 현재 사이즈
            int size = ball.size;
            int color = ball.color;

            // 이전 사이즈와 다른 경우
            if (prevSize != size) {
                totalSize += sameSize;

                for (int j = 1; j <= n; j++) {
                    // j 번째 색 / 현재 사이즈(size)보다 작은 사이즈들의 합
                    counts[j][0] += counts[j][1];
                    counts[j][1] = 0;
                }
                prevSize = size;
                sameSize = 0;
            }

            // 현재 볼이 사로잡을 수 있는 사이즈의 합
            // 전체 합에서 같은 색을 빼준다.
            answer[ball.id] = totalSize - counts[color][0];

            // 현재 color의 같은 사이즈 더 해주기
            counts[color][1] += ball.size;
            sameSize += ball.size;
        }

        for (int i = 0; i < n; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static class Ball {
        int id;
        int color;
        int size;

        Ball(int id, int color, int size) {
            this.id = id;
            this.color = color;
            this.size = size;
        }
    }
}
