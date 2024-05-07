package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 센서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int answer = 0;
        int sensor = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int target = Integer.parseInt(input.nextToken());

        int[] sensors = new int[sensor];
        int[] distances = new int[sensor - 1];
        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < sensor; i++) {
            sensors[i] = Integer.parseInt(input.nextToken());
        }
        Arrays.sort(sensors);

        for(int i = 1; i < sensor; i++) {
            distances[i - 1] = sensors[i] - sensors[i - 1];
        }
        Arrays.sort(distances);

        for(int i = 0; i <= distances.length - target; i++) {
            answer += distances[i];
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

}
