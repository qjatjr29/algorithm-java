package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 지구온난화 {

    private static final int MAX_SIZE = 10;
    private static final char LAND = 'X';
    private static final char SEA = '.';
    private static final int REMOVE_LIMIT= 3;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());

        char[][] map = new char[r][c];

        for(int i = 0; i < r; i++) {
            input = new StringTokenizer(br.readLine());
            String str = input.nextToken();
            for(int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int minX = MAX_SIZE;
        int minY = MAX_SIZE;
        int maxX = 0;
        int maxY = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(map[i][j] == LAND) {
                    int count = 0;
                    for(int z = 0; z < 4; z++) {
                        int nx = i + dx[z];
                        int ny = j + dy[z];
                        if(nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == '.') {
                            count++;
                        }
                    }
                    if(count >= REMOVE_LIMIT) {
                        queue.add(new int[] {i, j});
                        continue;
                    }
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] removedIsland = queue.poll();

            int x = removedIsland[0];
            int y = removedIsland[1];

            map[x][y] = '.';
        }

        for(int i = minX; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++) {
                bw.write(map[i][j]);
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
