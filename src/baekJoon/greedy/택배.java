package baekJoon.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 택배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int answer = 0;
        int n = Integer.parseInt(input.nextToken());
        int capacity = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(input.nextToken());

        // 택배 배달 정보
        Delivery[] delivery = new Delivery[m];

        // 각 도시는 capacity 만큼의 용량을 최대로 받을 수 있다.
        int[] capacities = new int[n + 1];
        Arrays.fill(capacities, capacity);

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(input.nextToken());
            int to = Integer.parseInt(input.nextToken());
            int box = Integer.parseInt(input.nextToken());
            delivery[i] = new Delivery(from, to, box);
        }

        // 택배가 도착하는 도시가 가장 작은 순으로 정렬
        Arrays.sort(delivery);

        for (int i = 0; i < m; i++) {
            Delivery d = delivery[i];

            int start = d.from;
            int end = d.to;
            int count = d.count;

            // 택배 시작~도착 도시까지의 담을 수 있는 택배 양(최대)
            // 이 범위의 모든 도시를 가는데 실을 수 있는 최대 양이여야 한다.
            int maxDelivery = Integer.MAX_VALUE;

            for (int j = start; j < end; j++) { // 택배를 받는 도시는 뺌
                maxDelivery = Math.min(maxDelivery, capacities[j]);
            }

            // 현재 택배의 양을 모두 담을 수 있는 경우
            if (maxDelivery >= count) {
                for (int j = start; j < end; j++) {
                    capacities[j] -= count;
                }
                answer += count;
            }
            // 현재 택배 양을 모두 담을 수 없으므로 나눠서 들어야함.
            else {
                for (int j = start; j < end; j++) {
                    capacities[j] -= maxDelivery;
                }
                answer += maxDelivery;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Delivery implements Comparable<Delivery> {
        int from;
        int to;
        int count;
        public Delivery(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }

        @Override
        public int compareTo(Delivery o) {
            if (this.to == o.to) return this.from - o.from;
            return this.to - o.to;
        }
    }
}
