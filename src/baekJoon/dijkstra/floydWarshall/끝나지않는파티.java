package baekJoon.dijkstra.floydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 끝나지않는파티 {

    private static final String CAN_ATTEND_PARTY = "Enjoy other party";
    private static final String CAN_NOT_ATTEND_PARTY = "Stay here";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int customerCount = Integer.parseInt(input.nextToken());

        int[][] distance = new int[size + 1][size + 1];

        for(int i = 1; i <= size; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 1; j <= size; j++) {
                distance[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        calculateMinDistance(distance);

        for(int i = 0; i < customerCount; i++) {
            input = new StringTokenizer(br.readLine());

            int startPoint = Integer.parseInt(input.nextToken());
            int destination = Integer.parseInt(input.nextToken());
            int remainTime = Integer.parseInt(input.nextToken());

            if(distance[startPoint][destination] > remainTime) bw.write(CAN_NOT_ATTEND_PARTY);
            else bw.write(CAN_ATTEND_PARTY);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void calculateMinDistance(int[][] distance) {

        int size = distance.length;

        for(int i = 1; i <= size - 1; i++) {
            for(int j = 1; j <= size - 1; j++) {
                for(int z = 1; z <= size - 1; z++) {
                    if(j == z) continue;
                    distance[j][z] = Math.min(distance[j][z], distance[j][i] + distance[i][z]);
                }
            }
        }


    }

}
