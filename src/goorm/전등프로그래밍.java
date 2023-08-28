package goorm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 전등프로그래밍 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(input.nextToken());
        int col = Integer.parseInt(input.nextToken());

        int[][] lamp = new int[row + 1][col + 1];
        int[][] toggleResult = new int[row + 1][col + 1];

        for(int i = 1; i <= row; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 1; j <= col; j++) {
                lamp[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        input = new StringTokenizer(br.readLine());
        int programmingCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < programmingCount; i++) {
            input = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(input.nextToken());
            int index = Integer.parseInt(input.nextToken());

            toggle(type, index, toggleResult);
        }

        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                if(toggleResult[i][j] % 2 != 0) {
                    lamp[i][j] = lamp[i][j] % 2 == 0 ? 1 : 0;
                }
                bw.write(String.valueOf(lamp[i][j]) + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static void toggle(int type, int index, int[][] toggleResult) {
        if(type == 0) {
            if(index < 0 || index >= toggleResult.length) return;
            toggleRow(index, toggleResult);
        }

        else {
            if(index < 0 || index >= toggleResult[0].length) return;
            toggleCol(index, toggleResult);
        }
    }

    // 왼쪽에서 오른쪽
    private static void toggleRow(int row, int[][] toggleResult) {
        for(int i = 1; i < toggleResult[0].length; i++) {
            toggleResult[row][i]++;
        }
    }

    // 위에서 아래
    private static void toggleCol(int col, int[][] toggleResult) {
        for(int i = 1; i < toggleResult.length; i++) {
            toggleResult[i][col]++;
        }
    }

}
