package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 여행 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[][] score = new int[N + 1][M + 1];
        List<int[]>[] airplane = new List[N + 1];
        for(int i = 0; i < N + 1; i++) {
            airplane[i] = new ArrayList<>();
            Arrays.fill(score[i], -1);
        }
        score[1][1] = 0;

        for(int i = 0; i < K; i++) {
            input = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(input.nextToken());
            int city2 = Integer.parseInt(input.nextToken());
            int cost = Integer.parseInt(input.nextToken());
            if(city1 >= city2) continue;
            airplane[city1].add(new int[] {city2, cost});
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {1, 1});

        while(!queue.isEmpty()) {

            int[] here = queue.poll();
            int cCity = here[0];
            int cCount = here[1];

            if(cCount >= M) continue;

            List<int[]> cities = airplane[cCity];

            for(int[] next : cities) {
                int nCity = next[0];
                int nCost = next[1];

                // 더 높은 점수를 발견한 경우에만 갱신
                if (score[nCity][cCount + 1] < score[cCity][cCount] + nCost) {
                    score[nCity][cCount + 1] = score[cCity][cCount] + nCost;
                    queue.add(new int[] {nCity, cCount + 1});
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= M; i++) {
            answer = Math.max(answer, score[N][i]);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
