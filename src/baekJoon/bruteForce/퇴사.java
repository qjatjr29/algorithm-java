package baekJoon.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 퇴사 {
    private static int answer;
    private static int lastDay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        lastDay = Integer.parseInt(input.nextToken());
        answer = 0;
        Consulting[] consultings = new Consulting[lastDay];

        for(int i = 0; i < lastDay; i++) {
            input = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());

            consultings[i] = new Consulting(day, cost);
        }

        dfs(0, consultings, 0);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
    private static class Consulting {
        int usedDay;
        int cost;

        public Consulting(int usedDay, int cost) {
            this.usedDay = usedDay;
            this.cost = cost;
        }

        public boolean isCanConsulting(int currentDay) {
            return currentDay + usedDay <= lastDay;
        }
    }

    private static void dfs(int day, Consulting[] consultings, int cost) {
        if(day >= consultings.length) {
            answer = Math.max(answer, cost);
            return;
        }

        // 해당 상담 하는 경우
        Consulting consult = consultings[day];
        if(consult.isCanConsulting(day)) {
            dfs(day + consult.usedDay, consultings, cost + consult.cost);
        }

        // 안하는 경우
        dfs(day + 1, consultings, cost);
    }
}
