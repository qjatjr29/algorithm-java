package baekJoon.dp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class DanceDanceRevolution {

    static int[][][] dp;

    public static int getDist(int start, int end) {
        if(end == start) return 1;
        if(start == 0) return 2;
        if(start % 2 == 0 && end % 2 == 0) return 4;
        if(start % 2 == 1 && end % 2 == 1) return 4;
        else return 3;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp = new int[100001][5][5];

        for(int i = 0; i < 100001; i++) {
            for(int j = 0; j < 5; j++) {
                for(int z = 0; z < 5; z++) {
                    dp[i][j][z] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][0][0] = 0;

        int end = 1;

        while(true) {
            int position = Integer.parseInt(st.nextToken());
            if(position == 0) break;

            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(dp[end - 1][i][j] == Integer.MAX_VALUE) continue;

                    int beforeCost = dp[end - 1][i][j];

                    int leftMoveCost= getDist(i, position);
                    int rightMoveCost = getDist(j, position);

                    // 왼발로 해당 번호로 이동하는 경우
                    dp[end][position][j] = min(dp[end][position][j], beforeCost + leftMoveCost);

                    // 오른발로 해당 번호로 이동하는 경우
                    dp[end][i][position] = min(dp[end][i][position], beforeCost + rightMoveCost);
                }
            }
            end++;
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j< 5; j++) {
                if(dp[end - 1][i][j] == -1) continue;
                answer = min(answer, dp[end - 1][i][j]);
            }
        }
        System.out.println(answer);
    }
}
