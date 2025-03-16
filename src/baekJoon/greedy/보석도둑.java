package baekJoon.greedy;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보석도둑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        PriorityQueue<Jewel> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());
            queue.add(new Jewel(w, c));
        }

        PriorityQueue<Integer> bagCapacity = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            bagCapacity.add(Integer.parseInt(input.nextToken()));
        }

        long answer = 0;
        PriorityQueue<Integer> costQueue = new PriorityQueue<>((a, b) -> b - a);

        while (!bagCapacity.isEmpty()) {

            int capacity = bagCapacity.poll();

            while (!queue.isEmpty()) {
                Jewel jewel = queue.peek();
                if (jewel.weight <= capacity) {
                    costQueue.add(jewel.cost);
                    queue.poll();
                }
                else break;
            }
            if (!costQueue.isEmpty()) answer += costQueue.poll();
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }


    private static class Jewel implements Comparable<Jewel> {
        int weight;
        int cost;

        Jewel (int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight;
        }
    }
}
