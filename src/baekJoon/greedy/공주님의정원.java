package baekJoon.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공주님의정원 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        Flower[] flowers = new Flower[n];
        int startDate = 301;
        int endDate = 1130;

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int sMonth = Integer.parseInt(input.nextToken());
            int sDay = Integer.parseInt(input.nextToken());
            int eMonth = Integer.parseInt(input.nextToken());
            int eDay = Integer.parseInt(input.nextToken());
            int sDate = sMonth * 100 + sDay;
            int eDate = eMonth * 100 + eDay;
            flowers[i] = new Flower(sDate, eDate);
        }

        Arrays.sort(flowers, (a, b) -> {
            if (a.startDate == b.startDate) return a.endDate - b.endDate;
            return a.startDate - b.startDate;
        });

        int answer = selectFlower(startDate, endDate, flowers);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int selectFlower(int startDate, int endDate, Flower[] flowers) {

        int count = 0;
        int idx = 0;
        int cEndDate = 0;

        // startDate : 이전 꽃들 중 가장 늦게 지는 꽃의 날짜

        while (startDate <= endDate) {

            int nextFlower = -1;

            for (int i = idx; i < flowers.length; i++) {
                if (flowers[i].startDate > startDate) {
                    break;
                }

                if (flowers[i].endDate > cEndDate) {
                    cEndDate = flowers[i].endDate;
                    nextFlower = i;
                }
            }

            if (nextFlower == -1) {
                break;
            }
            idx = nextFlower;
            count++;
            startDate = cEndDate;
        }

        if (cEndDate <= endDate) {
            count = 0;
        }
        return count;
    }

    private static class Flower {
        int startDate;
        int endDate;

        Flower(int startDate, int endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }
}
