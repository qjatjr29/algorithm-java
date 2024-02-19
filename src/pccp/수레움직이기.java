package pccp;

// https://school.programmers.co.kr/learn/courses/30/lessons/250134
public class 수레움직이기 {

    private static final int EMPTY = 0;
    private static final int WALL = 5;
    private static final int MAX = 987654321;
    private static boolean[][][] visited;
    private static boolean isRedArrived, isBlueArrived;

    public int solution(int[][] maze) {

        Wagon red = null;
        Wagon blue = null;

        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                if(maze[i][j] == 1) {
                    red = new Wagon(i, j);
                }
                if(maze[i][j] == 2) {
                    blue = new Wagon(i, j);
                }
            }
        }

        visited = new boolean[2][maze.length][maze[0].length];
        visited[0][red.x][red.y] = true;
        visited[1][blue.x][blue.y] = true;

        int answer = move(red, blue, maze, 0);
        answer = answer == MAX ? 0 : answer;
        return answer;
    }

    public int move(Wagon red, Wagon blue, int[][] maze, int count) {

        if(isRedArrived && isBlueArrived) return count;
        int answer = MAX;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {

                Wagon nRed = (!isRedArrived) ? getNextWagon(red.x, red.y, i) : red;
                Wagon nBlue = (!isBlueArrived) ? getNextWagon(blue.x, blue.y, j) : blue;

                if(!isPossibleMove(red, nRed, blue, nBlue, maze)) {
                    continue;
                }

                visited[0][nRed.x][nRed.y] = true;
                visited[1][nBlue.x][nBlue.y] = true;

                if(maze[nRed.x][nRed.y] == 3) isRedArrived = true;
                if(maze[nBlue.x][nBlue.y] == 4) isBlueArrived = true;

                answer = Math.min(answer, move(nRed, nBlue, maze, count + 1));

                isRedArrived = false;
                isBlueArrived = false;
                visited[0][nRed.x][nRed.y] = false;
                visited[1][nBlue.x][nBlue.y] = false;
            }
        }

        return answer;
    }

    private Wagon getNextWagon(int x, int y, int dir) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        return new Wagon(x + dx[dir], y + dy[dir]);
    }

    private boolean isPossibleMove(Wagon cRed, Wagon nRed, Wagon cBlue, Wagon nBlue,
                                          int[][] maze) {

        int height = maze.length;
        int width = maze[0].length;

        // 1. 범위를 넘어간 경우 또는 이동한 위치가 벽인 경우 - 불가능
        if(nRed.x < 0 || nBlue.x < 0 || nRed.x >= height || nBlue.x >= height ||
                nRed.y < 0 || nBlue.y < 0 || nRed.y >= width || nBlue.y >= width ||
                maze[nRed.x][nRed.y] == WALL || maze[nBlue.x][nBlue.y] == WALL) {
            return false;
        }

        // 2. 중복된 곳에 방문한 경우 - 불가능
        if((!isRedArrived && visited[0][nRed.x][nRed.y]) ||
                (!isBlueArrived && visited[1][nBlue.x][nBlue.y])) {
            return false;
        }

        // 3. 두 수레가 서로 위치를 바꾼 경우 - 불가능
        if(cRed.x == nBlue.x && cRed.y == nBlue.y &&
                cBlue.x == nRed.x && cBlue.y == nRed.y) {
            return false;
        }

        // 4. 두 수레가 같은 위치로 이동한 경우 - 불가능
        if(nRed.x == nBlue.x && nRed.y == nBlue.y) {
            return false;
        }

        return true;
    }

    private class Wagon {
        private int x;
        private int y;

        public Wagon(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
