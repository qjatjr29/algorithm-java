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

public class ACMCraft {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {
            input = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(input.nextToken());
            int K = Integer.parseInt(input.nextToken());

            int[] buildTimes = new int[N + 1];
            int[] connectCounts = new int[N + 1];
            List<Integer>[] nextBuildings = new List[N + 1];

            input = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                buildTimes[i] = Integer.parseInt(input.nextToken());
                nextBuildings[i] = new ArrayList<>();
            }

            for(int i = 0; i < K; i++) {
                input = new StringTokenizer(br.readLine());
                int prev = Integer.parseInt(input.nextToken());
                int next = Integer.parseInt(input.nextToken());

                nextBuildings[prev].add(next);
                connectCounts[next]++;
            }

            input = new StringTokenizer(br.readLine());
            int targetBuilding = Integer.parseInt(input.nextToken());

            int answer = topologicalSort(buildTimes, connectCounts, nextBuildings, targetBuilding);

            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int topologicalSort(int[] buildTimes, int[] connectCounts, List<Integer>[] nextBuildings, int target) {
        Queue<Integer> queue = new ArrayDeque<>();

        int[] times = new int[buildTimes.length];
        for(int i = 1; i < buildTimes.length; i++) {
            times[i] = buildTimes[i];
            if(connectCounts[i] == 0) {
                queue.add(i); // 자기 자신부터 시작하는 경우
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next : nextBuildings[current]) {
                times[next] = Math.max(times[next], times[current] + buildTimes[next]);
                connectCounts[next]--;
                if(connectCounts[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return times[target];
    }

}
