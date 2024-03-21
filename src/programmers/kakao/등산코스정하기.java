package programmers.kakao;

import java.util.*;

public class 등산코스정하기 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        List<Route>[] routes = new List[n + 1];
        int[] intensity = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            routes[i] = new ArrayList<Route>();
            intensity[i] = 987654321;
        }

        for(int[] path : paths) {
            int number1 = path[0];
            int number2 = path[1];
            int distance = path[2];
            routes[number1].add(new Route(number2, distance));
            routes[number2].add(new Route(number1, distance));
        }

        PriorityQueue<Peak> queue = new PriorityQueue<>();
        boolean[] isSummit = new boolean[n + 1];

        for(int gate : gates) {
            queue.add(new Peak(gate, 0));
            intensity[gate] = 0;
        }

        for(int summit : summits) {
            isSummit[summit] = true;
        }


        while(!queue.isEmpty()) {
            Peak cPeak = queue.poll();
            int cNumber = cPeak.number;
            int cIntensity = cPeak.intensity;

            // 이미 해당 peak에 도달하는 다른 경로에서 최소 intensity가 존재하는 경우 넘어간다.
            if(intensity[cNumber] < cIntensity) {
                continue;
            }

            List<Route> nextRoutes = routes[cNumber];

            for(Route route : nextRoutes) {
                int nNumber = route.destination;
                int distance = route.distance;

                // 다음으로 갈 곳의 intensity가 현재 경로로 가는 것보다 더 작은 값이라면 해당 경로로 갈 필요가 없다.
                if(intensity[nNumber] <= Math.max(intensity[cNumber], distance)) {
                    continue;
                }

                intensity[nNumber] = Math.max(intensity[cNumber], distance);

                // 다음이 산봉우리일 경우
                // 출입구 -> 산봉우리가 최소 거리일 경우
                // 그대로 내려오는게 최소이기 때문에 산봉우리부터 가는 경로는 계산해주지 않아도 된다.
                if(isSummit[nNumber]) {
                    continue;
                }

                queue.add(new Peak(nNumber, intensity[nNumber]));
            }
        }

        int minIntensity = Integer.MAX_VALUE;
        int minSummit = 0;
        for(int summit : summits) {
            if(intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }

            if(intensity[summit] == minIntensity) {
                if(minSummit > summit) {
                    minSummit = summit;
                }
            }
        }

        answer = new int[] {minSummit, minIntensity};

        return answer;
    }


    private class Route {
        int destination;
        int distance;

        Route(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    private class Peak implements Comparable<Peak> {
        int number;
        int intensity;

        Peak(int number, int intensity) {
            this.number = number;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Peak o) {
            return this.intensity - o.intensity;
        }
    }
}
