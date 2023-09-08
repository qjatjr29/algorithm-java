package goorm;

import java.io.*;
import java.util.*;

public class 단풍나무 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int length = Integer.parseInt(input.nextToken());
        int[][] park = new int[length][length];

        for(int i = 0; i < length; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < length; j++) {
                park[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        int day = 0;
        while(true) {

            int[][] updated = new int[length][length];
            int count = 0;

            for(int i = 0; i < length; i++) {
                for(int j = 0; j < length; j++) {
                    if(park[i][j] > 0) {
                        checkAround(i, j, park, updated);
                        count++;
                    }
                }
            }

            if(count == 0) break;
            update(park, updated);
            day++;
        }

        bw.write(String.valueOf(day));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void checkAround(int x, int y, int[][] park, int[][] updateRequest) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= park.length || ny < 0 || ny >= park[0].length) continue;
            if(park[nx][ny] == 0) updateRequest[x][y] += 1;
        }
    }

    private static void update(int[][] park, int[][] updateRequest) {

        int size = park.length;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                park[i][j] -= updateRequest[i][j];
                if(park[i][j] <= 0) park[i][j] = 0;
            }
        }

    }

}
