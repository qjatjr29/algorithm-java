package baekJoon.dijkstra.floydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 플로이드2 {

    private static final int INF = 10000001;
    private static int[][] moveCost;
    private static List<Integer>[][] movePaths;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int cityCount = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int busCount = Integer.parseInt(input.nextToken());

        moveCost = new int[cityCount + 1][cityCount + 1];
        movePaths = new List[cityCount + 1][cityCount + 1];

        for(int i = 1; i <= cityCount; i++) {
            for(int j = 1; j <= cityCount; j++) {
                movePaths[i][j] = new ArrayList<>();
                if(i == j) continue;
                moveCost[i][j] = INF;
            }
        }


        for(int i = 0; i < busCount; i++) {
            input = new StringTokenizer(br.readLine());
            int sCity = Integer.parseInt(input.nextToken());
            int eCity = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            moveCost[sCity][eCity] = Math.min(moveCost[sCity][eCity], cost);
        }

        floyd(cityCount);

        printPathCost(cityCount);
        printPaths(cityCount);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void floyd(int cityCount) {
        for(int i = 1; i <= cityCount; i++) {
            for(int j = 1; j <= cityCount; j++) {
                for(int z = 1; z <= cityCount; z++) {
                    if(moveCost[j][z] > moveCost[j][i] + moveCost[i][z]) {
                        moveCost[j][z] = moveCost[j][i] + moveCost[i][z];
                        resetRoute(j, z, i);
                    }
                }
            }
        }
    }

    private static void resetRoute(int start, int end, int stopover) {
        movePaths[start][end].clear();

        for(int path : movePaths[start][stopover]) {
            movePaths[start][end].add(path);
        }
        movePaths[start][end].add(stopover);
        for(int path : movePaths[stopover][end]) {
            movePaths[start][end].add(path);
        }
    }

    private static void printPathCost(int cityCount) {

        for(int i = 1; i <= cityCount; i++) {
            for(int j = 1; j <= cityCount; j++) {
                if(i == j || moveCost[i][j] == INF) {
                    sb.append("0 ");
                    continue;
                }
                sb.append(moveCost[i][j] + " ");
            }
            sb.append("\n");
        }
    }

    private static void printPaths(int cityCount) {
        for(int i = 1; i <= cityCount; i++) {
            for(int j = 1; j <= cityCount; j++) {
                if(i == j || moveCost[i][j] == INF) {
                    sb.append("0");
                }
                else {
                    sb.append(movePaths[i][j].size() + 2 + " ");
                    sb.append(i + " ");
                    for(int path : movePaths[i][j]) {
                        sb.append(path + " ");
                    }
                    sb.append(j + " ");
                }
                sb.append("\n");
            }
        }
    }
}
