package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 지름길 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int roadCount = Integer.parseInt(input.nextToken());
        int totalDistance = Integer.parseInt(input.nextToken());
        List<Road>[] roads = new List[totalDistance + 1];
        int[] distance = new int[totalDistance + 1];

        for(int i = 0; i <= totalDistance; i++) {
            roads[i] = new ArrayList<>();
        }

        for(int i = 0; i < roadCount; i++) {
            input = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            int dist = Integer.parseInt(input.nextToken());

            if(end > totalDistance) continue;
            if(end - start < dist) continue;
            roads[end].add(new Road(start, dist));
        }

        for(int i = 0; i <= totalDistance; i++) {
            distance[i] = i;
        }

        for(int i = 1; i <= totalDistance; i++) {

            if(roads[i].size() > 0) {
                for(Road road : roads[i]) {
                    int startPoint = road.start;
                    int dist = road.distance;

                    if(distance[startPoint] + dist > distance[i]) continue;
                    distance[i] = Math.min(distance[i - 1] + 1,
                         distance[startPoint] + dist);
                }
            }
            else distance[i] = distance[i - 1] + 1;
        }

        bw.write(String.valueOf(distance[totalDistance]));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Road {
        int start;
        int distance;

        public Road(int start, int distance) {
            this.start = start;
            this.distance = distance;
        }

    }

}
