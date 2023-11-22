package programmers.kakao;

import java.util.ArrayList;
import java.util.List;

public class 외벽점검 {
    private static int answer;
    private static int[][] weakPoints;

    public int solution(int n, int[] weak, int[] dist) {
        answer = 9;

        weakPoints = new int[weak.length][weak.length];

        for(int i = 0; i < weak.length; i++) {
            int index = 0;
            for(int j = i; j < weak.length; j++) {
                weakPoints[i][index] = weak[j];
                index++;
            }

            for(int j = 0; j < i; j++) {
                weakPoints[i][index] = weak[j] + n;
                index++;
            }
        }

        setDistances(new ArrayList<>(), new boolean[dist.length], dist);

        if(answer == 9) answer = -1;
        return answer;
    }

    private void checkWeakPoint(int[] weakPoints, int[] distances) {

        int cPoint = 0;
        int nPoint;
        int distIdx = 0;

        while(cPoint < weakPoints.length && distIdx < distances.length) {
            nPoint = cPoint + 1;
            while(nPoint < weakPoints.length &&
                    weakPoints[cPoint] + distances[distIdx] >= weakPoints[nPoint]) {
                nPoint++;
            }
            cPoint = nPoint;

            distIdx++;
        }

        if(cPoint == weakPoints.length && distIdx < answer) {
            answer = distIdx;
        }

    }

    private void setDistances(List<Integer> temp, boolean[] visited, int[] dist) {

        if(temp.size() == dist.length) {
            int[] distance = temp.stream()
                    .mapToInt(i -> i)
                    .toArray();

            for(int[] points : weakPoints) {
                checkWeakPoint(points, distance);
            }
            return;
        }

        for(int i = 0; i < dist.length; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            temp.add(dist[i]);
            setDistances(temp, visited, dist);

            visited[i] = false;
            temp.remove(temp.size() - 1);
        }


    }
}
