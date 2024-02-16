package pccp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/250136
public class 석유시추 {

    private static final int EMPTY = 0;
    private static final int OIL = 1;

    public int solution(int[][] land) {
        int answer = 0;

        int height = land.length;
        int width = land[0].length;
        Land[][] lands = new Land[height][width];
        List<Area> areaList = new ArrayList<>();
        boolean[][] visited = new boolean[height][width];

        int id = 0;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(land[i][j] == OIL && !visited[i][j]) {
                    Area newArea = measurementOilArea(i, j, id, land, lands, visited);
                    id++;
                    areaList.add(newArea);
                }
            }
        }

        for(int i = 0; i < width; i++) {

            int ans = 0;

            Set<Integer> areaIds = new HashSet<>();

            for(int j = 0; j < height; j++) {
                if(land[j][i] == EMPTY) {
                    continue;
                }
                areaIds.add(lands[j][i].areaId);
            }

            for(int areaId : areaIds) {
                ans += areaList.get(areaId).size;
            }
            answer = Math.max(answer, ans);
        }

        return answer;
    }

    private Area measurementOilArea(int x, int y, int id, int[][] land,
                                    Land[][] lands, boolean[][] visited) {

        Queue<int[]> oilAreaQueue = new LinkedList<>();
        int count = 1;
        oilAreaQueue.add(new int[] {x, y});
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!oilAreaQueue.isEmpty()) {

            int[] here = oilAreaQueue.poll();
            int cx = here[0];
            int cy = here[1];
            lands[cx][cy] = new Land(id);
            // System.out.println(id + " - " + cx + "  " + cy);

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 0 || nx >= lands.length || ny < 0 || ny >= lands[0].length) {
                    continue;
                }
                if(visited[nx][ny] || land[nx][ny] == EMPTY) {
                    continue;
                }
                oilAreaQueue.add(new int[] {nx, ny});
                visited[nx][ny] = true;
                count++;
            }
        }
        return new Area(id, count);
    }

    private class Land {
        int areaId;

        public Land(int areaId) {
            this.areaId = areaId;
        }
    }

    private class Area {
        int areaId;
        int size;

        public Area(int areaId, int size) {
            this.areaId = areaId;
            this.size = size;
        }
    }
}
