package baekJoon.knapsack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 함께블록쌓기 {

    private static final int INF = 10007;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int studentCount = Integer.parseInt(input.nextToken());
        int studentBlockMaxCount = Integer.parseInt(input.nextToken());
        int targetHeight = Integer.parseInt(input.nextToken());

        List<Integer>[] studentBlocks = new List[studentCount + 1];

        for(int i = 1; i <= studentCount; i++) {
            studentBlocks[i] = new ArrayList<Integer>();
            input = new StringTokenizer(br.readLine());
            while (input.hasMoreTokens()) {
                studentBlocks[i].add(Integer.parseInt(input.nextToken()));
            }
        }

        // 최대 높이 1000
        // 1번부터 N번까지의 학생들이 가진 블록을 '차례대로' 사용
        // 즉, 순서가 존재함.

        // 학생들의 순서가 왔을 때, 해당 학생들의 블록을 이용해서 쌓을 수 있는 높이의 경우의 수를 체크
        int[][] dp = new int[studentCount + 1][targetHeight + 1];

        for(int i = 1; i <= studentCount; i++) {

            // i번 학생이 height 만큼의 높이의 블록을 쌓을 수 있는지
            for(int height = 1; height <= targetHeight; height++) {

                // 학생이 가지고 있는 블록
                for(int cHeight : studentBlocks[i]) {
                    // 이전 블록을 안 사용하고 해당 블록만 사용
                    if(cHeight == height) dp[i][height]++;

                    // 이전에 쌓아놓은 블록 + 학생 블록 = height 인 경우
                    if(height > cHeight) {
                        dp[i][height] += dp[i - 1][height - cHeight];
                    }
                }
                // 블록을 사용하지 않은 경우
                dp[i][height] += dp[i - 1][height];
                dp[i][height] %= INF;
            }
        }

        int answer = dp[studentCount][targetHeight];

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

}
