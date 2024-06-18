package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 최종순위 {

    private static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());
        for(int testcase = 0; testcase < T; testcase++) {
            input = new StringTokenizer(br.readLine());
            int teamCount = Integer.parseInt(input.nextToken());

            int[] lastScore = new int[teamCount + 1];
            int[] inDegree = new int[teamCount + 1];

            // 순위가 높음 -> 순위가 낮음 으로 이어지는 리스트
            List<Integer>[] list = new List[teamCount + 1];

            input = new StringTokenizer(br.readLine());
            for(int i = 1; i <= teamCount; i++) {
                lastScore[i] = Integer.parseInt(input.nextToken());
                list[i] = new ArrayList<>();
            }

            for(int i = 1; i <= teamCount; i++) {
                int higher = lastScore[i];
                for(int j = i + 1; j <= teamCount; j++) {
                    list[higher].add(lastScore[j]);
                    inDegree[lastScore[j]]++;
                }
            }

            input = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(input.nextToken());

            for(int i = 0; i < m; i++) {
                // 순위 변동
                input = new StringTokenizer(br.readLine());
                int higherScoreTeam = Integer.parseInt(input.nextToken());
                int lowerScoreTeam = Integer.parseInt(input.nextToken());

                if(list[higherScoreTeam].contains(lowerScoreTeam)) {
                    list[higherScoreTeam].remove(Integer.valueOf(lowerScoreTeam));
                    inDegree[higherScoreTeam]++;
                    inDegree[lowerScoreTeam]--;
                    list[lowerScoreTeam].add(higherScoreTeam);
                }
                else {
                    list[lowerScoreTeam].remove(Integer.valueOf(higherScoreTeam));
                    list[higherScoreTeam].add(lowerScoreTeam);
                    inDegree[higherScoreTeam]--;
                    inDegree[lowerScoreTeam]++;
                }
            }

            Queue<Integer> queue = new ArrayDeque<>();
            for(int i = 1; i <= teamCount; i++) {
                if(inDegree[i] == 0) {
                    queue.add(i);
                }
            }

            if(queue.size() > 1) {
                bw.write(String.valueOf("?"));
                bw.newLine();
                continue;
            }

            boolean chk = true;
            StringBuilder sb = new StringBuilder();

            for(int i = 1; i <= teamCount; i++) {
                if(queue.isEmpty()) {
                    bw.write(String.valueOf(IMPOSSIBLE));
                    bw.newLine();
                    chk = false;
                    break;
                }

                int high = queue.poll();
                sb.append(String.valueOf(high) + " ");
                for(int next : list[high]) {
                    inDegree[next]--;
                    if(inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            if(chk) {
                bw.write(sb.toString());
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
