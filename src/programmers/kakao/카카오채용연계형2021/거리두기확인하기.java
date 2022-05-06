package programmers.kakao.카카오채용연계형2021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 거리두기확인하기 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static Character[][] map;

    public static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Position {
        int x, y, count;
        Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static boolean checkDistance(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(x, y, 0));
        visited = new boolean[5][5];
        visited[x][y] = true;
        while(true) {
            if (queue.isEmpty()) break;
            Position here = queue.poll();
            if (here.count == 2) continue;
            int cx = here.x;
            int cy = here.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 'P') {
                    return false;
                } else if (map[nx][ny].equals('X')) continue;


                visited[nx][ny] = true;

                queue.add(new Position(nx, ny, here.count + 1));
            }
        }
        return true;
    }

    public static int[] solution(String[][] places) {
        int[] answer;
        answer = new int[5];

        List<Pair> candidate = new ArrayList<>();

        for(int i = 0; i < places.length; i++) {
            map = new Character[5][5];
            candidate.clear();
            for(int j = 0; j < 5; j++) {
                for(int z = 0; z < 5; z++) {
                    map[j][z] = places[i][j].charAt(z);
                    if(map[j][z] == 'P') {
                        candidate.add(new Pair(j, z));
                    }
                }
            }
            boolean chk = false;
            for (Pair person : candidate) {
                if(!checkDistance(person.x, person.y)) {
                    chk = true;
                    break;
                }
            }
            if(!chk) answer[i] = 1;
        }

        return answer;
    }

    public static void main(String[] args) {

        String[][] places = new String[5][5];
        places[0][0] = "POOOP";
        places[0][1] = "OXXOX";
        places[0][2] = "OPXPX";
        places[0][3] = "OOXOX";
        places[0][4] = "POXXP";

        places[1][0] = "POOPX";
        places[1][1] = "OXPXP";
        places[1][2] = "PXXXO";
        places[1][3] = "OXXXO";
        places[1][4] = "OOOPP";

        places[2][0] = "PXOPX";
        places[2][1] = "OXOXP";
        places[2][2] = "OXPOX";
        places[2][3] = "OXXOP";
        places[2][4] = "PXPOX";

        places[3][0] = "OOOXX";
        places[3][1] = "XOOOX";
        places[3][2] = "OOOXX";
        places[3][3] = "OXOOX";
        places[3][4] = "OOOOO";

        places[4][0] = "PXPXP";
        places[4][1] = "XPXPX";
        places[4][2] = "PXPXP";
        places[4][3] = "XPXPX";
        places[4][4] = "PXPXP";

        int[] solution = solution(places);
        for (int i : solution) {
            System.out.println(i);
        }

    }
}
