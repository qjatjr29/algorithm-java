package baekJoon.Implementation;

import java.io.*;
import java.util.*;

public class 주사위윷놀이 {

    private static final int DICE_COUNT = 10;
    private static final int START_POINT = 0;
    private static final int END_POINT = 42;
    private static int answer;
    private static List<Node>[] routes;
    private static List<int[]> moves;
    private static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        init();
        dice = new int[DICE_COUNT];

        for (int i = 0; i < DICE_COUNT; i++) {
            dice[i] = Integer.parseInt(input.nextToken());
        }
        dfs(0, new int[10]);

        for (int i = 0; i < moves.size(); i++) {
            answer = Math.max(answer, solve(moves.get(i), dice));
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Node {
        int cur;
        int next;
        boolean isFastRoute;

        public Node(int cur, int next, boolean isFastRoute) {
            this.cur = cur;
            this.next = next;
            this.isFastRoute = isFastRoute;
        }
    }

    private static void init() {
        routes = new List[END_POINT + 1];
        moves = new ArrayList<>();

        // 모든 루트 초기화 및 기본루트 설정(0->2->4 ...)
        for (int i = START_POINT; i <= END_POINT; i++) {
            routes[i] = new ArrayList<Node>();

            if (i % 2 == 0) routes[i].add(new Node(i, i + 2, false));
        }

        // 지름길 루트
        for (int i = 10; i <= 16; i += 3) {
            routes[i].add(new Node(i, i + 3, true));
        }
        routes[19].add(new Node(19, 25, true));

        for (int i = 20; i <= 22; i += 2) {
            routes[i].add(new Node(i, i + 2, true));
        }
        routes[24].add(new Node(24, 25, true));

        routes[30].add(new Node(30, 28, true));
        for (int i = 28; i >= 26; i--) {
            routes[i].add(new Node(i, i - 1, true));
        }

        for (int i = 25; i <= 35; i += 5) {
            routes[i].add(new Node(i, i + 5, true));
        }
    }

    private static int solve(int[] move, int[] dice) {
        int result = 0;

        // 4개 말 초기 세팅
        Node[] nodes = new Node[4];
        for (int i = 0; i < 4; i++) {
            nodes[i] = new Node(START_POINT, START_POINT + 2, false);
        }

        for (int i = 0; i < move.length; i++) {
            // 움직일 말의 번호
            int nNumber = move[i];

            // 움직여야하는 칸 횟수
            int m = dice[i];

            // 이미 도착지점에 있는 말을 움직여야 하는 경우 (말 안됌)
            if (nodes[nNumber].cur == END_POINT) {
                result = 0;
                break;
            }

            // 해당 말을 움직였을 때 위치지점
            Node next = nextNode(nodes[nNumber], m);

            // 이미 다른 말판이 있는 칸인지 확인
            boolean check = true;
            for (int j = 0; j < 4; j++) {
                if (nodes[j].cur == END_POINT) continue;
                if (isSameNode(nodes[j], next)) {
                    check = false;
                    break;
                }
            }
            if (!check) {
                result = 0;
                break;
            }

            nodes[nNumber] = next;
            if (next.cur != END_POINT) result += nodes[nNumber].cur;
        }
        return result;
    }

    private static boolean isSameNode(Node n1, Node n2) {
        if (n1.cur != n2.cur || n1.next != n2.next || n1.isFastRoute != n2.isFastRoute) return false;
        return true;
    }

    private static void dfs(int cnt, int[] move) {
        if (cnt == DICE_COUNT) {
            answer = Math.max(answer, solve(move, dice));
            return;
        }
        for (int i = 0; i < 4; i++) {
            // 4개의 말 중 하나 선택
            move[cnt] = i;
            dfs(cnt + 1, move);
        }
    }

    private static Node nextNode(Node node, int mCnt) {

        Node cur = node;
        while (mCnt > 0) {

            // 이동전 칸 번호
            int num = cur.cur;

            // 이미 도착한 경우는 움직이지 않는다.
            if (num == END_POINT) break;

            // 현재 번호가 지름길인지
            boolean isFastRoute = cur.isFastRoute;
            // 연결된 다음 번호
            int next = cur.next;

            // 하나뿐인 루트
            if (routes[next].size() == 1) {
                cur = routes[next].get(0);
            }

            else if (routes[next].size() == 2) {
                if (isFastRoute) cur = routes[next].get(1);
                else cur = routes[next].get(0);
            }

            // 30인 경우
            else {
                if (!isFastRoute) cur = routes[next].get(0);
                else {
                    if (num == 25) cur = routes[next].get(2);
                    else cur = routes[next].get(1);
                }
            }
            mCnt--;
        }
        if (cur.cur == 10) {
            cur = routes[10].get(1);
        }
        if (cur.cur == 20) {
            cur = routes[20].get(1);
        }
        if (cur.cur == 30) {
            if (cur.next != 35) cur = routes[30].get(1);
        }
        return cur;
    }

}
