package goorm;

import java.util.*;
import java.io.*;

public class ADAS시스템 {

    private static Map<Character, Integer> orderMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());
        orderMap = new HashMap<>();
        int answer = 0;

        setMap();

        int height = Integer.parseInt(input.nextToken());
        int width = Integer.parseInt(input.nextToken());

        Point[][] map = new Point[height][width];
        int startX = 0;
        int startY = 0;


        for(int i = 0; i < height; i++) {
            input = new StringTokenizer(br.readLine());
            String str = input.nextToken();
            for(int j = 0; j < width; j++) {
                map[i][j] = new Point(i, j, str.charAt(j), false);
                if(str.charAt(j) == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(map[startX][startY]);
        map[startX][startY].isVisited = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!queue.isEmpty()) {

            Point point = queue.poll();
            int cx = point.x;
            int cy = point.y;

            if(point.state == 'E') break;

            answer += calculateRiskScore(cx, cy, map);

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
                if(map[nx][ny].isVisited) continue;
                map[nx][ny].isVisited = true;
                queue.add(map[nx][ny]);
            }
        }

        if(answer < 0) answer = 0;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }


    private static void setMap(){
        orderMap.put('S', -1);
        orderMap.put('E', 0);
        orderMap.put('P', 1);
        orderMap.put('0', 2);
    }

    private static int calculateRiskScore(int x, int y, Point[][] map) {

        Point point = map[x][y];
        char state = point.state;

        if(state == 'E') return 0;
        int pCount = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};


        for(int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
            if(map[nx][ny].state == 'P') pCount++;
        }

        if(state == 'P') return pCount - 3;
        else if(state == '0') return pCount;
        return 0;
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        char state;
        int order;
        boolean isVisited;

        Point(int x, int y, char state, boolean isVisited) {
            this.x = x;
            this.y = y;
            this.state = state;
            calcuateOrder();
            this.isVisited = isVisited;
        }

        @Override
        public int compareTo(Point o) {
            if(this.order == o.order) {
                if(this.x == o.x) return this.y - o.y;
                return this.x - o.x;
            }
            return this.order - o.order;
        }

        private void calcuateOrder() {
            this.order = orderMap.get(this.state);
        }
    }


}
