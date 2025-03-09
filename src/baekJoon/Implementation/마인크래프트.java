package baekJoon.Implementation;

import java.io.*;
import java.util.StringTokenizer;

public class 마인크래프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int b = Integer.parseInt(input.nextToken());
        int time = Integer.MAX_VALUE;
        int height = Integer.MIN_VALUE;

        int[][] ground = new int[n][m];

        int minHeight = 256;
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ground[i][j] = Integer.parseInt(input.nextToken());
                minHeight = Math.min(minHeight, ground[i][j]);
                maxHeight = Math.max(maxHeight, ground[i][j]);
            }
        }

        for (int h = minHeight; h <= maxHeight; h++) {
            int cTime = 0;
            int requiredBlock = 0;
            int block = b;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int gap = ground[i][j] - h;

                    if (gap > 0) {
                        // 블록을 제거
                        cTime += (gap * 2);
                        block += gap;
                    }

                    if (gap < 0) {
                        // 블록을 사용
                        cTime += (gap * -1);
                        requiredBlock += (gap * -1);
                    }
                }
            }
            if (requiredBlock > block) {
                continue;
            }

            if (time > cTime) {
                time = cTime;
                height = h;
            }

            if (time == cTime) {
                height = Math.max(height, h);
            }
        }

        bw.write(time + " " + height);
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
