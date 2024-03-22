package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 경비원 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int length = Integer.parseInt(input.nextToken());
        int height = Integer.parseInt(input.nextToken());
        int totalDistance = (length + height) * 2;

        input = new StringTokenizer(br.readLine());
        int storeCount = Integer.parseInt(input.nextToken());

        int[] stores = new int[storeCount];

        for(int i = 0; i < storeCount; i++) {
            input = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(input.nextToken());
            int distance = Integer.parseInt(input.nextToken());

            stores[i] = calculateDistance(direction, distance, length, height);
        }

        input = new StringTokenizer(br.readLine());
        int direction = Integer.parseInt(input.nextToken());
        int distance = Integer.parseInt(input.nextToken());
        int me = calculateDistance(direction, distance, length, height);

        int answer = 0;
        for(int i = 0; i < storeCount; i++) {
            int distance1 = Math.abs(me - stores[i]);
            int distance2 = totalDistance - distance1;
            answer += Math.min(distance1, distance2);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static int calculateDistance(int direction ,int distance, int length, int height) {
        int result = 0;
        if(direction == 1) {
            result = distance;
        }
        if(direction == 2) {
            result = length + height + (length - distance);
        }
        if(direction == 3) {
            result =  2 * length + height + (height - distance);
        }
        if(direction == 4) {
            result = length + distance;
        }
        return result;
    }
}
