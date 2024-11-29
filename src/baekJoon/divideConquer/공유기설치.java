package baekJoon.divideConquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치 {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());

        int[] houses = new int[N];
        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            houses[i] = Integer.parseInt(input.nextToken());
        }

        Arrays.sort(houses);
        // 두 공유기간 최대, 최소 걸이
        int minDistance = 1;
        int maxDistance = houses[N - 1] - houses[0] + 1;


        answer = 0;
        check(minDistance, maxDistance, houses, C);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void check(int min, int max, int[] houses, int C) {

        while(min < max) {
            int distance = (min + max) / 2;

            // 공유기를 C개만큼 설치하지 못한 경우
            if(getInternet(distance, houses) < C) {
                max = distance;
            }
            else {
                answer = distance;
                min = distance + 1;
            }
        }

    }

    private static int getInternet(int distance, int[] houses) {
        int count = 1;

        int house = houses[0];

        for(int i = 1; i < houses.length; i++) {

            int nHouse = houses[i];
            // 집과 집 사이 거리
            int gap = nHouse - house;

            if(distance <= gap) {
                count++;
                house = nHouse;
            }
        }
        return count;
    }
}
