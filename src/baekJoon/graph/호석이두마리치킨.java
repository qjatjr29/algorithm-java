package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 호석이두마리치킨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int[][] city = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            Arrays.fill(city[i], 101);
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(input.nextToken());
            int c2 = Integer.parseInt(input.nextToken());
            city[c1][c2] = 1;
            city[c2][c1] = 1;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for(int z = 1; z <= N; z++) {
                    if(i == j) continue;
                    city[i][j] = Math.min(city[i][j], city[i][z] + city[z][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        int answerCity1 = 0;
        int answerCity2 = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                int result = 0;
                for(int z = 1; z <= N; z++) {
                    if(i == z || j == z) continue;
                    result += Math.min(city[i][z], city[j][z]);
                }
                if(answer > result) {
                    answer = result;
                    answerCity1 = i;
                    answerCity2 = j;
                }
            }
        }

        bw.write(answerCity1 + " ");
        bw.write(answerCity2 + " ");
        bw.write(answer * 2+ " ");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
