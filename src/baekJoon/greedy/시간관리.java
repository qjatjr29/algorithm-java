package baekJoon.greedy;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 시간관리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(input.nextToken());
            int s = Integer.parseInt(input.nextToken());
            pq.offer(new int[]{t, s});
        }

        int answer = -1;
        while (!pq.isEmpty()) {

            int[] task = pq.poll();
            int deadLine = task[1];
            int taskTime = task[0];

            if (answer == -1) {
                answer = deadLine - taskTime;
                continue;
            }

            if (answer < deadLine) {
                answer -= taskTime;
            }
            else {
                answer = deadLine - taskTime;
            }
        }

        if (answer < 0) {
            answer = -1;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
