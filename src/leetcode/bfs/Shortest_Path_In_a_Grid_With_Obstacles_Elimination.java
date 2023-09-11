package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Shortest_Path_In_a_Grid_With_Obstacles_Elimination {

    private static final int OBSTACLE = 1;
    private static final int startX = 0;
    private static final int startY = 0;
    private static final int EMPTY = 0;

    public int shortestPath(int[][] grid, int k) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int r = grid.length;
        int c = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY, 0, 0});

        boolean[][][] visited = new boolean[r][c][k + 1];
        visited[startX][startY][0] = true;

        while(!queue.isEmpty()) {

            int[] here = queue.poll();
            int cx = here[0];
            int cy = here[1];
            int ck = here[2];
            int moveCount = here[3];

            if(cx == r - 1 && cy == c - 1) return moveCount;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                if(grid[nx][ny] == OBSTACLE) {
                    if(ck == k) continue;
                    if(visited[nx][ny][ck + 1]) continue;
                    visited[nx][ny][ck + 1] = true;
                    queue.add(new int[] {nx, ny, ck + 1, moveCount + 1});
                }
                else {
                    if(visited[nx][ny][ck]) continue;
                    visited[nx][ny][ck] = true;
                    queue.add(new int[] {nx, ny, ck, moveCount + 1});
                }
            }

        }
        return -1;
    }

}
