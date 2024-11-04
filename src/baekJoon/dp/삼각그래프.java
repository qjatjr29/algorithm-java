package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 삼각그래프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = 1;
        while(true) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(input.nextToken());

            if(row == 0) {
                break;
            }

            int[][] rows = new int[row][3];
            int startNumber, endNumber;

            for(int i = 0; i < row; i++) {
                input = new StringTokenizer(br.readLine());
                for(int j = 0; j < 3; j++) {
                    rows[i][j] = Integer.parseInt(input.nextToken());
                }
            }

            rows[0][2] += rows[0][1];
            rows[1][0] += rows[0][1];
            rows[1][1] += Math.min(rows[1][0], Math.min(rows[0][1], rows[0][2]));
            rows[1][2] += Math.min(rows[1][1], Math.min(rows[0][1], rows[0][2]));

            for(int i = 2; i < row; i++) {

                int leftMin = Math.min(rows[i - 1][0], rows[i - 1][1]);
                int rightMin = Math.min(rows[i - 1][1], rows[i - 1][2]);
                rows[i][0] += leftMin;
                rows[i][1] += Math.min(rows[i][0], Math.min(leftMin, rightMin));
                rows[i][2] += Math.min(rows[i][1], rightMin);
            }

            bw.write(String.valueOf(testcase) + ". ");
            bw.write(String.valueOf(rows[row - 1][1]));
            bw.newLine();

            testcase++;
        }


        bw.flush();
        bw.close();
        br.close();

    }
}
