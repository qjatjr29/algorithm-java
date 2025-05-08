package baekJoon.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 연료채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken()); // 주유소 개수
        GasStation[] stations = new GasStation[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int distance = Integer.parseInt(input.nextToken()); // 시작 -> 주유소 까지의 거리
            int fuel = Integer.parseInt(input.nextToken()); // 해당 주유소에서 얻을 수 있는 연료량
            stations[i] = new GasStation(distance, fuel);
        }

        Arrays.sort(stations, (a, b) -> a.distance - b.distance);

        input = new StringTokenizer(br.readLine());
        int totalDistance = Integer.parseInt(input.nextToken());
        int startFuel = Integer.parseInt(input.nextToken());

        // 갈 수 있는 주유소에서 얻을 수 있는 연료가 가장 큰 곳으로 가야 최소의 주유소 경유 횟수로 원하는 곳에 도착
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // 각 주유소에서 얻을 수 있는 연료

        boolean chk = false;
        int index = 0;
        int fuel = startFuel;

        while (true) {

            // 도착하는 경우
            if (fuel >= totalDistance) {
                chk = true;
                break;
            }

            for (int i = index; i < stations.length; i++) {
                GasStation station = stations[i];
                int distance = station.distance;

                // 해당 주유소는 현재 연료로 못감.
                if (fuel < distance) {
                    index = i;
                    break;
                }
                pq.add(station.fuel);
            }

            // 주유소를 하나 선택 (가장 많은 연료를 얻을 수 있는 곳으로)
            if (pq.isEmpty()) break;
            fuel += pq.poll();
            answer++;
        }

        if (!chk) answer = -1;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class GasStation {
        int distance;
        int fuel;

        GasStation(int distance, int fuel) {
            this.distance = distance;
            this.fuel = fuel;
        }
    }
}
