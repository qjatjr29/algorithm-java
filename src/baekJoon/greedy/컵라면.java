package baekJoon.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 컵라면 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        PriorityQueue<Problem> problemQueue = new PriorityQueue<>((a, b) -> {
            if (a.deadLine == b.deadLine) {
                return b.amount - a.amount;
            }
            return b.deadLine - a.deadLine;
        });

        int lastDeadLine = 0;
        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int dl = Integer.parseInt(input.nextToken());
            int amount = Integer.parseInt(input.nextToken());
            problemQueue.add(new Problem(i, dl, amount));
            lastDeadLine = Math.max(lastDeadLine, dl);
        }

        int time = lastDeadLine + 1;
        int answer = 0;
        PriorityQueue<Integer> amountQueue = new PriorityQueue<>((a, b) -> b - a);

        while (!problemQueue.isEmpty()) {
            Problem problem = problemQueue.poll();
            int deadLine = problem.deadLine;
            int amount = problem.amount;
            amountQueue.add(amount);

            if (deadLine != time) {
                for (int i = deadLine; i < time; i++) {
                    if (amountQueue.isEmpty()) break;
                    int maxAmount = amountQueue.poll();
                    answer += maxAmount;
                }
                time = deadLine;
            }
        }

        if (time > 1) {
            while (!amountQueue.isEmpty()) {
                time--;
                if (time == 0) break;
                answer += amountQueue.poll();
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Problem {
        int number;
        int deadLine;
        int amount;
        Problem(int number, int deadLine, int amount) {
            this.number = number;
            this.deadLine = deadLine;
            this.amount = amount;
        }
    }
}
