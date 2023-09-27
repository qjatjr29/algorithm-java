package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SmallWorldNetwork {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int people = Integer.parseInt(input.nextToken());
        int relations = Integer.parseInt(input.nextToken());
        int[][] dp = new int[people + 1][people + 1];
        String answer = "Small World!";

        for(int i = 0; i <= people; i++) {
            for(int j = 0; j <= people; j++) {
                if(i == j) continue;
                dp[i][j] = 987654321;
            }
        }

        for(int i = 0; i < relations; i++) {
            input = new StringTokenizer(br.readLine());
            int friend1 = Integer.parseInt(input.nextToken());
            int friend2 = Integer.parseInt(input.nextToken());
            dp[friend1][friend2] = 1;
            dp[friend2][friend1] = 1;
        }

        for(int i = 1; i <= people; i++) {
            for(int j = 1; j <= people; j++) {
                for(int z = 1; z <= people; z++) {
                    if(j == z) continue;
                    dp[j][z] = Math.min(dp[j][z], dp[j][i] + dp[i][z]);
                }
            }
        }

        for(int i = 1; i <= people; i++) {
            for(int j = 1; j <= people; j++) {
                if(dp[i][j] >= 7) {
                    answer = "Big World!";
                    break;
                }
            }
        }

        bw.write(answer);
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

}
