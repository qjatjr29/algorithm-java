package baekJoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 면접보는승범이네 {

    private static final long INF = 10000000001L;
    private static List<Route>[] routes;
    private static long[] resultDistances;
    private static Queue<Integer> meetingCityQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        routes = new ArrayList[N + 1];
        resultDistances = new long[N + 1];
        meetingCityQueue = new LinkedList<>();

        for(int i = 0; i <= N; i++) {
            routes[i] = new ArrayList<>();
            resultDistances[i] = INF;
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int sCity = Integer.parseInt(input.nextToken());
            int eCity = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());

            routes[eCity].add(new Route(sCity, cost));
        }

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int meetingCity = Integer.parseInt(input.nextToken());
            meetingCityQueue.add(meetingCity);
        }

        findMinDistanceForMeeting();

        int answerCity = 0;
        long maxDistance = 0L;

        for(int i = 1; i <= N; i++) {
            if(maxDistance < resultDistances[i]) {
                maxDistance = resultDistances[i];
                answerCity = i;
            }
        }

        bw.write(String.valueOf(answerCity));
        bw.newLine();
        bw.write(String.valueOf(maxDistance));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void findMinDistanceForMeeting() {

        Long[] distances = new Long[routes.length];
        PriorityQueue<Route> pq = new PriorityQueue<>();

        Arrays.fill(distances, -1L);

        for(int meetingCity : meetingCityQueue) {
            pq.add(new Route(meetingCity, 0L));
            distances[meetingCity] = 0L;
        }

        while(!pq.isEmpty()) {

            Route cRoute = pq.poll();

            int cCity = cRoute.city;
            long cDistance = cRoute.distance;

            // 이미 더 작은 경우
            if(distances[cCity] == -1 || distances[cCity] < cDistance) continue;

            for(Route nRoute : routes[cCity]) {
                int nCity = nRoute.city;
                long nDistance = cDistance+ nRoute.distance;

                if(distances[nCity] == -1 || distances[nCity] > nDistance) {
                    distances[nCity] = nDistance;
                    pq.add(new Route(nCity, nDistance));
                }
            }
        }

        for(int i = 1; i < resultDistances.length; i++) {
            resultDistances[i] = Math.min(resultDistances[i], distances[i]);
        }

    }

    private static class Route implements Comparable<Route> {
        int city;
        long distance;

        public Route(int city, long distance) {
            this.city = city;
            this.distance = distance;
        }

        @Override
        public int compareTo(Route o) {
            return Long.compare(this.distance, o.distance);
        }
    }

}
