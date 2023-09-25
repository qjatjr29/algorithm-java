package baekJoon.unionFind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 소셜네트워킹어플리케이션 {

    private static final int IS_FRIEND = 1;
    private static final int NOT_FRIEND = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {
            bw.write("Scenario " + String.valueOf(testcase + 1) + ":");
            bw.newLine();
            input = new StringTokenizer(br.readLine());
            int users = Integer.parseInt(input.nextToken());
            int[] friends = new int[users + 1];
            for(int i = 0; i <= users; i++) friends[i] = i;

            input = new StringTokenizer(br.readLine());
            int relations = Integer.parseInt(input.nextToken());

            for(int i = 0; i < relations; i++) {
                input = new StringTokenizer(br.readLine());
                int user1 = Integer.parseInt(input.nextToken());
                int user2 = Integer.parseInt(input.nextToken());
                makeFriend(user1, user2, friends);
            }

            input = new StringTokenizer(br.readLine());
            int matchCount = Integer.parseInt(input.nextToken());
            for(int i = 0; i < matchCount; i++) {
                input = new StringTokenizer(br.readLine());
                int user1 = Integer.parseInt(input.nextToken());
                int user2 = Integer.parseInt(input.nextToken());
                if(canFriend(user1, user2, friends)) bw.write(String.valueOf(IS_FRIEND));
                else bw.write(String.valueOf(NOT_FRIEND));
                bw.newLine();
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static int find(int x, int[] friends){
        if(friends[x] == x) return x;
        else return friends[x] = find(friends[x], friends);
    }

    private static boolean canFriend(int user1, int user2, int[] friends) {
        user1 = find(user1, friends);
        user2 = find(user2, friends);
        return user1 == user2;
    }


    private static void makeFriend(int user1, int user2, int[] friends) {
        user1 = find(user1, friends);
        user2 = find(user2, friends);
        if(user1 != user2) friends[user2] = user1;
    }

}
