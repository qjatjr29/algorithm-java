package baekJoon.graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class DSLR {

    private static String[] cmd = {"D", "S", "L", "R"};
    private static int[][] numbers;
    private static boolean[] visited;
    private static String answer;
    private static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            visited[start] = true;

            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(start, ""));

            while(true) {
                if(q.isEmpty()) break;

                Pair poll = q.poll();
                int cNumber = poll.num;
                if(cNumber == end) {
                    bw.write(poll.cmd);
                    bw.newLine();
                    break;
                }

                if(!visited[poll.D()]) {
                    q.add(new Pair(poll.D(), poll.cmd + "D"));
                    visited[poll.D()] = true;
                }
                if(!visited[poll.S()]) {
                    q.add(new Pair(poll.S(), poll.cmd + "S"));
                    visited[poll.S()] = true;
                }
                if(!visited[poll.L()]) {
                    q.add(new Pair(poll.L(), poll.cmd + "L"));
                    visited[poll.L()] = true;
                }
                if(!visited[poll.R()]) {
                    q.add(new Pair(poll.R(), poll.cmd + "R"));
                    visited[poll.R()] = true;
                }
            }
        }
        bw.flush();
        bw.close();
    }

    private static class Pair {
        int num;
        String cmd;

        public Pair(int num, String cmd) {
            this.num = num;
            this.cmd = cmd;
        }

        int D() {
            return (num * 2) %10000;
        }
        int S() {
            return num == 0 ? 9999 : num - 1;
        }
        int L() {
            return num % 1000 * 10 + num / 1000;
        }
        int R() {
            return num % 10 * 1000 + num / 10;
        }

    }
}
