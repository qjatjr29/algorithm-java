package baekJoon.knapsack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 할로윈의양아치 {

    private static int[] relationship;
    private static Map<Integer, Relationship> relationshipMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        relationshipMap = new HashMap<>();

        int childrenCount = Integer.parseInt(input.nextToken());
        int relations = Integer.parseInt(input.nextToken());
        int minChildrenCount = Integer.parseInt(input.nextToken());

        Children[] children = new Children[childrenCount + 1];
        relationship = new int[childrenCount + 1];

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= childrenCount; i++) {
            children[i] = new Children(i, Integer.parseInt(input.nextToken()));
            relationship[i] = i;
        }

        for(int i = 0; i < relations; i++) {
            input = new StringTokenizer(br.readLine());
            int child1 = Integer.parseInt(input.nextToken());
            int child2 = Integer.parseInt(input.nextToken());
            union(child1, child2);
        }

        for(int i = 1; i <= childrenCount; i++) {
            makeRelation(i, children);
        }

        List<Relationship> relationships = new ArrayList<>();
        for(int key : relationshipMap.keySet()) {
            relationships.add(relationshipMap.get(key));
        }

        int[][] dp = new int[relationships.size() + 1][minChildrenCount];

        for(int i = 1; i <= relationships.size(); i++) {

            Relationship relationship = relationships.get(i - 1);
            int childrenCnt = relationship.getFriendCount();
            int candy = relationship.getCandy();

            for(int j = 0; j < minChildrenCount; j++) {
                if(j >= childrenCnt) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - childrenCnt] + candy);
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int answer = dp[relationships.size()][minChildrenCount - 1];
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static int findRelationship(int x) {
        if(relationship[x] == x) return x;
        else return relationship[x] = findRelationship(relationship[x]);
    }

    private static void union(int x, int y) {
        x = findRelationship(x);
        y = findRelationship(y);
        if(x != y) relationship[y] = x;
    }

    private static void makeRelation(int x, Children[] children) {
        Relationship relationship = getRelationship(x);
        relationship.addFriend(children[x]);
        relationshipMap.put(findRelationship(x), relationship);
    }


    private static Relationship getRelationship(int x) {
        x = findRelationship(x);
        return relationshipMap.getOrDefault(x, new Relationship());
    }

    private static class Relationship {
        List<Integer> friendList;
        int candyCount;

        Relationship() {
            this.friendList = new ArrayList<>();
            this.candyCount = 0;
        }

        public void addFriend(Children children) {
            if(!friendList.contains(children.getId())) {
                friendList.add(children.getId());
                this.candyCount += children.getCandy();
            }
        }

        public int getCandy() {
            return this.candyCount;
        }

        public int getFriendCount() {
            return this.friendList.size();
        }
    }

    private static class Children {
        int childrenId;
        int candy;

        public Children(int childrenId, int candy) {
            this.childrenId = childrenId;
            this.candy = candy;
        }

        public int getId() {
            return childrenId;
        }

        public int getCandy() {
            return candy;
        }
    }
}
