package baekJoon.graph.bfs;

import java.io.*;
import java.util.*;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class 열쇠 {

//    private static class Pair {
//        int x, y;
//        Pair(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    private static class Door {
//        List<Pair> pair;
//        Door(List pair) {
//            this.pair = pair;
//        }
//    }
//    static Queue<Pair> q;
//    static int[][] visited;
//    static Character[][] map;
//    static int[] dx = {-1, 1, 0, 0};
//    static int[] dy = {0, 0, 1, -1};
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int t = Integer.parseInt(st.nextToken());
//
//        for(int testcase = 0; testcase < t; testcase++) {
//            st = new StringTokenizer(br.readLine());
//            int N = Integer.parseInt(st.nextToken());
//            int M = Integer.parseInt(st.nextToken());
//
//            int answer = 0;
//
//            map = new Character[N + 1][M + 1];
//
//            Door[] door = new Door[26];
//            Door[] key = new Door[26];
//
//            visited = new int[N + 1][M + 1];
//
//            for(int i = 0; i < 26; i++) {
//                door[i] = new Door(new ArrayList<>());
//                key[i] = new Door(new ArrayList<>());
//            }
//
//            q = new LinkedList<>();
//            for(int i = 1; i <= N; i++) {
//                st = new StringTokenizer(br.readLine());
//                String s = st.nextToken();
//                for(int j = 1; j <= M; j++) {
//                    char c = s.charAt(j - 1);
//                    map[i][j] = c;
//                    if('A' <= c && c <= 'Z') {
//                        door[c - 'A'].pair.add(new Pair(i, j));
//                    }
//
//                    if('a' <= c && c <= 'z') {
//                        key[c - 'a'].pair.add(new Pair(i, j));
//                    }
//
//                    // 가장자리.. + 벽이 아님.
//                    if((i == 1 || j == 1 || i == N || j == M) && (c == '.' || c == '$')) {
//                        q.add(new Pair(i, j));
//                        visited[i][j] = 1;
//                    }
//                }
//            }
//            st = new StringTokenizer(br.readLine());
//            String keys = st.nextToken();
//            if(!keys.equals("0")) {
//                for (int i = 0; i < keys.length(); i++) {
//                    char c = keys.charAt(i);
//                    int idx = c - 'a';
//                    if(!key[idx].pair.isEmpty()) {
//                        for(int j = 0; j < key[idx].pair.size(); j++) {
//                            openDoor(key[idx], j, N, M);
//                        }
//                    }
//                    if(!door[idx].pair.isEmpty()) {
//                        for(int j = 0; j < door[idx].pair.size(); j++) {
//                            openDoor(door[idx], j, N, M);
//                        }
//                    }
//                }
//            }
//
//            while(true) {
//
//                if(q.isEmpty()) break;
//
//                Pair here = q.poll();
//                int cx = here.x;
//                int cy = here.y;
//
//                if(map[cx][cy] == '$') {
//                    answer++;
//                }
//
//                for(int i = 0; i < 4; i++) {
//                    int nx = cx + dx[i];
//                    int ny = cy + dy[i];
//
//                    if (baseCase(N, M, nx, ny)) continue;
//
//                    char nextValue = map[nx][ny];
//
//                    if(nextValue == '*') continue;
//                    if(nextValue == '.' || nextValue == '$') {
//                        q.add(new Pair(nx, ny));
//                        visited[nx][ny] = 1;
//                        continue;
//                    }
//
//                    // 소문자
//                    if('a' <= nextValue && nextValue <= 'z') {
//                        visited[nx][ny] = 1;
//                        q.add(new Pair(nx, ny));
//                        int idx = nextValue - 'a';
//                        if(!door[idx].pair.isEmpty()) {
//                            for(int j = 0; j < door[idx].pair.size(); j++) {
//                                openDoor(door[idx], j, N, M);
//                            }
//                        }
//                    }
//
//                }
//            }
//
//            bw.write(String.valueOf(answer));
//            bw.newLine();
//        }
//        bw.flush();
//        bw.close();
//    }
//
//    private static void openDoor(Door door, int j, int N, int M) {
//        int doorX = door.pair.get(j).x;
//        int doorY = door.pair.get(j).y;
//
//        map[doorX][doorY] = '.';
//
//        // 문이 가장자리에 있는 경우.
//        if(doorX == 1 || doorX == N || doorY == 1 || doorY == M) {
//            q.add(new Pair(doorX, doorY));
//            visited[doorX][doorY] = 1;
//        } else {
//            boolean chk = false;
//            for(int z = 0; z < 4; z++) {
//                int chkX = doorX + dx[z];
//                int chkY = doorY + dy[z];
//                if (chkX <= 0 || chkX > N || chkY <= 0 || chkY > M) continue;
//                if(visited[chkX][chkY] == 1) {
//                    chk = true;
//                    break;
//                }
//            }
//            if(chk) {
//                q.add(new Pair(doorX, doorY));
//                visited[doorX][doorY] = 1;
//            }
//        }
//
//    }
//
//    private static boolean baseCase(int N, int M,  int nx, int ny) {
//        if(nx <= 0 || nx > N || ny <= 0 || ny > M) return true;
//        if(visited[nx][ny] == 1) return true;
//        return false;
//    }

    static int h, w;
    static Character[][] map;
    static PriorityQueue<Position> q;
    static boolean[] hasKey;
    static boolean[][][] visited;
    static int answer;
    static int keyCnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    private static class Position implements Comparable<Position> {
        int x, y, count;
        Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Position o) {
            if(this.count - o.count > 0) return -1;
            else if(this.count == o.count) return 0;
            else return 1;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for(int testcase = 0; testcase < t; testcase++) {

            st =  new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            setup();

            for(int i = 0; i <= h + 1; i++) {
                Arrays.fill(map[i], '.');
            }

            for(int i = 1; i <= h; i++) {
                st = new StringTokenizer(br.readLine());
                String line = st.nextToken();
                for(int j = 1; j <= w; j++) {
                    char c = line.charAt(j - 1);
                    map[i][j] = c;
                }
            }

            String key = new StringTokenizer(br.readLine()).nextToken();
            keyCnt = 0;
            if(!key.equals("0")) {
                for(char k : key.toCharArray()) {
                    hasKey[k] = true;
                    keyCnt++;
                }
            }
            q.add(new Position(0, 0, keyCnt));
            move();
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void setup() {
        map = new Character[h + 2][w + 2];
        q = new PriorityQueue<>();
        visited = new boolean[h + 2][w + 2]['z' - 'a' + 2];
        hasKey = new boolean['z' + 1];
        keyCnt = 0;
        answer = 0;

    }

    private static void move() {
        while(true) {
            if(q.isEmpty()) break;
            Position here = q.poll();
            int cKeyCnt = here.count;

            if(cKeyCnt < keyCnt) break;

            int cx = here.x;
            int cy = here.y;

            visited[cx][cy][cKeyCnt] = true;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx > h + 1 || ny < 0 || ny > w + 1) continue;
                if(visited[nx][ny][cKeyCnt]) continue;

                char next = map[nx][ny];

                if(next== '*') continue;

                if(next == '.') {
                    q.add(new Position(nx, ny, cKeyCnt));
                } else if(hasKey[next]) {
                    // 열쇠 발견
                    // 열쇠 있는 case
                    map[nx][ny] = '.';
                    q.add(new Position(nx, ny, cKeyCnt));
                } else if('a' <= next && next <= 'z') {
                    // 처음 발견하는 열쇠
                    hasKey[next] = true;
                    map[nx][ny] = '.';
                    cKeyCnt++;
                    keyCnt++;
                    q.add(new Position(nx, ny, cKeyCnt));
                } else if('A' <= next && next <= 'Z') {
                    char alpha = toLowerCase(next);
                    if(hasKey[alpha]) {
                        map[nx][ny] = '.';
                        q.add(new Position(nx, ny, cKeyCnt));
                    }
                } else if(next == '$') {
                    answer++;
                    map[nx][ny] = '.';
                    q.add(new Position(nx, ny, cKeyCnt));
                }
            }
        }
    }



}
