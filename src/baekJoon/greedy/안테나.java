package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 안테나 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int houseCount = Integer.parseInt(input.nextToken());
        int[] housePositions = new int[houseCount];
        int answer = 0;

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < houseCount; i++) {
            housePositions[i] = Integer.parseInt(input.nextToken());
        }

        Arrays.sort(housePositions);
        if(houseCount % 2 == 0) {
            answer = housePositions[houseCount / 2 - 1];
        }
        else {
            answer = housePositions[houseCount / 2];
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

}
