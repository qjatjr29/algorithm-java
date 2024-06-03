package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 추월 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());
        int answer = 0;

        int count = Integer.parseInt(input.nextToken());
        Queue<String> inQueue = new ArrayDeque<>();

        for(int i = 0; i < count; i++) {
            input = new StringTokenizer(br.readLine());
            String carNumber = input.nextToken();
            inQueue.add(carNumber);
        }

        for(int i = 0; i < count; i++) {
            input = new StringTokenizer(br.readLine());
            String outCarNumber = input.nextToken();

            if(!inQueue.peek().equals(outCarNumber)) {
                answer++;
                inQueue.remove(outCarNumber);
                continue;
            }
            inQueue.poll();
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
    private static class Car {
        String preCarNumber;
        int id;
        String number;

        public Car(String preCarNumber, int id, String number) {
            this.preCarNumber = preCarNumber;
            this.id = id;
            this.number = number;
        }
    }
}
