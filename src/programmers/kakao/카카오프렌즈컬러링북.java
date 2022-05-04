package programmers.kakao;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.max;

public class 카카오프렌즈컬러링북 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int findArea(int m, int n, int x, int y, int[][]picture, int[][]visited) {
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(x, y));
        int base = picture[x][y];
        int cnt = 1;
        while(true) {
            if(q.isEmpty()) break;
            Pair here = q.poll();
            int cx = here.x;
            int cy = here.y;
            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(picture[nx][ny] == 0 || visited[nx][ny] == 1) continue;
                else if(picture[nx][ny] == base) {
                    q.add(new Pair(nx, ny));
                    visited[nx][ny] = 1;
                    cnt++;
                }
            }
        }
        return cnt;
    }

        public int[] solution(int m, int n, int[][] picture) {
            int numberOfArea = 0;
            int maxSizeOfOneArea = 0;
            int[][] visited = new int[m][n];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(picture[i][j] == 0 || visited[i][j] == 1) continue;
                    numberOfArea++;
                    int areaCnt = findArea(m, n, i, j, picture, visited);
                    maxSizeOfOneArea = max(maxSizeOfOneArea, areaCnt);
                }
            }
            int[] answer = new int[2];
            answer[0] = numberOfArea;
            answer[1] = maxSizeOfOneArea;
            return answer;
        }
    public static void main(String[] args) {

    }
}
