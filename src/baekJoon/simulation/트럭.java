package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트럭 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int truckCount = Integer.parseInt(input.nextToken());
        int bridgeLength = Integer.parseInt(input.nextToken());
        int bridgeLoad = Integer.parseInt(input.nextToken());

        Queue<Truck> truckQueue = new ArrayDeque<>();
        input = new StringTokenizer(br.readLine());

        for(int i = 0; i < truckCount; i++) {
            truckQueue.add(new Truck(Integer.parseInt(input.nextToken())));
        }

        int time = 0;
        int cWeight = 0;
        Queue<Truck> truckOnBridge = new ArrayDeque<>();

        while(!truckQueue.isEmpty()) {
            time++;
            Truck truck = truckQueue.peek();
            int truckWeight = truck.weight;

            while(!truckOnBridge.isEmpty()) {
                Truck t = truckOnBridge.peek();
                if(time - t.initTime >= bridgeLength) {
                    cWeight -= t.weight;
                    truckOnBridge.poll();
                    continue;
                }
                break;
            }

            if (cWeight + truckWeight <= bridgeLoad) {
                if(truckOnBridge.size() < bridgeLength) {
                    cWeight += truckWeight;
                    truckQueue.poll();
                    truck.initTime = time;
                    truckOnBridge.add(truck);
                }
            }
        }

        while(!truckOnBridge.isEmpty()) {

            Truck remainTruck = truckOnBridge.poll();
            int moveDistance = time - remainTruck.initTime;
            time += bridgeLength - moveDistance;
        }

        bw.write(String.valueOf(time));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Truck {
        int weight;
        int initTime;

        public Truck(int weight) {
            this.weight = weight;
            this.initTime = 0;
        }

        public Truck(int weight, int initTime) {
            this.weight = weight;
            this.initTime = initTime;
        }
    }
}
