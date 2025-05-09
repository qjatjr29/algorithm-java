package baekJoon.dp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 사회망서비스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        List<Integer>[] friends = new List[n + 1];
        int[][] dp = new int[n + 1][2]; // i 친구가 얼리아답터가 아닌 경우, 인 경우의 얼리아답터 수

        for (int i = 1; i <= n; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            input = new StringTokenizer(br.readLine());
            int user1 = Integer.parseInt(input.nextToken());
            int user2 = Integer.parseInt(input.nextToken());
            friends[user1].add(user2);
            friends[user2].add(user1);
        }

        earlyAdaptor(1, 0, dp, friends);

        int answer = Math.min(dp[1][0], dp[1][1]);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void earlyAdaptor(int user, int prev, int[][] dp, List<Integer>[] friends) {

        dp[user][0] = 0;
        dp[user][1] = 1;

        for (int friend : friends[user]) {

            // 사회망 서비스 트리에서 이미 확인한 친구인 경우 넘어감
            if (prev == friend) {
                continue;
            }

            // 다음 친구로 넘어감.
            earlyAdaptor(friend, user, dp, friends);

            // 현재 유저가 얼리아답터가 아닌 경우 친구는 무조건 얼리 아답터야 함.
            dp[user][0] += dp[friend][1];

            // 현재 유저가 얼리아답터인 경우는 친구는 얼리 아답터 여도 가능하고 아니여도 가능
            // 최소 값으로 결정.
            dp[user][1] += Math.min(dp[friend][0], dp[friend][1]);
        }
    }
}
