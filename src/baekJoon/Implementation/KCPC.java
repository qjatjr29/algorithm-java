package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class KCPC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {
            input = new StringTokenizer(br.readLine());
            int teamCount = Integer.parseInt(input.nextToken());
            int problemCount = Integer.parseInt(input.nextToken());
            int teamId = Integer.parseInt(input.nextToken());
            int logCount = Integer.parseInt(input.nextToken());

            Team[] teams = new Team[teamCount + 1];
            for(int i = 0; i <= teamCount; i++) {
                teams[i] = new Team(i);
            }

            teams[0].score = -1;

            for(int i = 0; i < logCount; i++) {
                input = new StringTokenizer(br.readLine());
                int tId = Integer.parseInt(input.nextToken());
                int problemId = Integer.parseInt(input.nextToken());
                int score = Integer.parseInt(input.nextToken());
                teams[tId].solve(problemId, score, i);
            }

            Arrays.sort(teams);
            int result = 0;
            for(int i = 0; i < teams.length - 1; i++) {
                if(teams[i].id == teamId) {
                    result = i + 1;
                    break;
                }
            }
            bw.write(String.valueOf(result));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static class Team implements Comparable<Team> {
        int id;
        int score;
        Map<Integer, Integer> problems;
        int solvedCount;
        int solvedTime;

        public Team(int id) {
            this.id = id;
            this.score = 0;
            this.problems = new HashMap<>();
        }

        public void solve(int problemId, int score, int time) {
            int prevScore = this.problems.getOrDefault(problemId, 0);
            int nScore = Math.max(prevScore, score);
            this.problems.put(problemId, nScore);
            this.score += (nScore - prevScore);
            this.solvedTime = time;
            this.solvedCount++;
        }

        @Override
        public int compareTo(Team o) {
            if(this.score == o.score) {
                if(this.solvedCount == o.solvedCount) {
                    return this.solvedTime - o.solvedTime;
                }
                return this.solvedCount - o.solvedCount;
            }
            return o.score - this.score;
        }
    }

}
