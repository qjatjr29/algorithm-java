package swExpertAcademy;

import java.util.*;
import java.io.*;

public class 보급로 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 1; testcase <= T; testcase++) {

            input = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(input.nextToken());

            int[][] map = new int[size][size];

            for(int i = 0; i < size; i++) {
                input = new StringTokenizer(br.readLine());
                String row = input.nextToken();
                for(int j = 0; j < size; j++) {
                    map[i][j] = row.charAt(j) - '0';
                }
            }

            int answer = findMinPath(map);

            bw.write(String.valueOf("#") + testcase + " " + String.valueOf(answer));
            bw.newLine();

        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static int findMinPath(int[][] map) {

        int result = 0;
        int size = map.length;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[size][size];
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        pq.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            Node cNode = pq.poll();
            int cx = cNode.x;
            int cy = cNode.y;
            int cCnt = cNode.count;

            if(cx == size - 1 && cy == size - 1) {
                result = cCnt;
                break;
            }

            visited[cx][cy] = true;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 0 || nx >= size || ny < 0 || ny >= size || visited[nx][ny]) {
                    continue;
                }

                pq.add(new Node(nx, ny, cCnt + map[nx][ny]));
            }

        }

        return result;
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int count;
        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

}
