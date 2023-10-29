package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 타임머신 {

    private static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int cityCount = Integer.parseInt(input.nextToken());
        int busRouteCount = Integer.parseInt(input.nextToken());

        // 각 도시와 연결된 경로의 정보
        List<Route>[] cityConnections = new List[cityCount + 1];
        // 최단 거리 배열
        long[] distances = new long[cityCount + 1];
        Arrays.fill(distances, INF);
        distances[0] = 0;

        for(int i = 0; i <= cityCount; i++) {
            distances[i] = INF;
            cityConnections[i] = new ArrayList<>();
        }

        for(int i = 0; i < busRouteCount; i++) {
            input = new StringTokenizer(br.readLine());
            int startPoint = Integer.parseInt(input.nextToken());
            int endPoint = Integer.parseInt(input.nextToken());
            int distance = Integer.parseInt(input.nextToken());
            cityConnections[startPoint].add(new Route(endPoint, distance));
        }

        boolean isCycle = bellmanFord(cityConnections, distances);

        if(isCycle) {
            bw.write("-1");
            bw.newLine();
        }
        else {
            for(int i = 2; i <= cityCount; i++) {
                if(distances[i] == INF) bw.write("-1");
                else bw.write(String.valueOf(distances[i]));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bellmanFord(List<Route>[] connections, long[] distance) {

        // 시작 노드 초기화
        distance[1] = 0;
        // 최단 거리 최소화
        for(int i = 1; i < connections.length; i++) {
            // 모든 간선 확인하기
            for(int j = 1; j < connections.length; j++) {
                if(distance[j] == INF) continue;
                for(Route route : connections[j]) {
                    if(distance[route.end] > distance[j] + route.distance) {
                        distance[route.end] = distance[j] + route.distance;
                        if(i == connections.length - 1) return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Route {
        int end;
        int distance;

        public Route(int end, int distance) {
            this.end = end;
            this.distance = distance;
        }
    }

}
