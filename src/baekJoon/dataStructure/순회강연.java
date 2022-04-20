package baekJoon.dataStructure;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.util.Arrays.sort;

public class 순회강연 {

    static int[] dp;

    static class Lecture implements Comparable<Lecture> {
        int pay, date;

        Lecture(int pay, int date) {
            this.pay = pay;
            this.date = date;
        }

        @Override
        public int compareTo(Lecture o) {
            if(pay > o.pay) return -1;
            else if (pay == o.pay) return 0;
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        if(n == 0) {
            bw.write("0");
            bw.newLine();
            bw.close();
            return ;
        }
        PriorityQueue<Lecture> pq = new PriorityQueue<>();
        dp = new int[10001];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.add(new Lecture(p, d));
        }

        int answer = 0;
        int now = 1;
        while (true) {
            if(pq.isEmpty()) break;
            Lecture here = pq.poll();
            for(int i = here.date; i >= 1; i--) {
                if(dp[i] != 0) continue;
                answer += here.pay;
                dp[i] = now;
                now++;
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
