package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 여왕벌 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int repeatDay = Integer.parseInt(input.nextToken());

        int[][] larva = new int[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                larva[i][j] = 1;
            }
        }

        for(int i = 0; i < repeatDay; i++) {

            input = new StringTokenizer(br.readLine());
            int zeroCnt = Integer.parseInt(input.nextToken());
            int oneCnt = Integer.parseInt(input.nextToken());
            int twoCnt = Integer.parseInt(input.nextToken());

            int cx = size - 1;
            int cy = 0;
            boolean isUp = true;

            for(int j = 0; j < zeroCnt; j++) {
                if(cx == 0) isUp = false;
                if(isUp) cx--;
                else cy++;
            }

            for(int j = 0; j < oneCnt; j++) {
                if(cx == 0) isUp = false;
                larva[cx][cy] += 1;
                if(isUp) cx--;
                else cy++;
            }

            for(int j = 0; j < twoCnt; j++) {
                if(cx == 0) isUp = false;
                larva[cx][cy] += 2;
                if(isUp) cx--;
                else cy++;
            }
        }

        for(int j = 1; j < size; j++) {
            for(int z = 1; z < size; z++) {
                int increase = Math.max(larva[j][z - 1],
                    Math.max(larva[j - 1][z], larva[j - 1][z - 1]));
                larva[j][z] += increase - 1;
            }
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                bw.write(String.valueOf(larva[i][j]) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
