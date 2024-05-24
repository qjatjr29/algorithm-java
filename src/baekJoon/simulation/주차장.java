package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 주차장 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int answer = 0;
        Car[] cars = new Car[M + 1];
        Map<Integer, Garage> usedParkingMap = new HashMap<>();
        PriorityQueue<Garage> garageQueue = new PriorityQueue<>();
        Queue<Integer> waitingQueue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            garageQueue.add(new Garage(i, Integer.parseInt(input.nextToken())));
        }

        for (int i = 1; i <= M; i++) {
            input = new StringTokenizer(br.readLine());
            cars[i] = new Car(i, Integer.parseInt(input.nextToken()));
        }

        for (int i = 0; i < M * 2; i++) {
            input = new StringTokenizer(br.readLine());
            int carNumber = Integer.parseInt(input.nextToken());

            if (carNumber < 0) {
                carNumber *= -1;
                Garage garage = usedParkingMap.remove(carNumber);

                if (waitingQueue.isEmpty()) {
                    garageQueue.add(garage);
                    continue;
                }

                int nextCarNumber = waitingQueue.poll();
                usedParkingMap.put(nextCarNumber, garage);
                answer += garage.calculateCost(cars[nextCarNumber].weight);
                continue;
            }

            if (carNumber > 0) {
                if (garageQueue.isEmpty()) {
                    waitingQueue.add(carNumber);
                    continue;
                }
                Garage garage = garageQueue.poll();
                usedParkingMap.put(carNumber, garage);
                answer += garage.calculateCost(cars[carNumber].weight);
            }
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Garage implements Comparable<Garage> {

        int id;
        int costPerWeight;

        public Garage(int id, int costPerWeight) {
            this.id = id;
            this.costPerWeight = costPerWeight;
        }

        public int calculateCost(int weight) {
            return costPerWeight * weight;
        }

        @Override
        public int compareTo(Garage o) {
            return this.id - o.id;
        }
    }

    private static class Car {
        int id;
        int weight;

        public Car(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }
}
