package programmers.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class 상담원인원 {

    private static Map<Integer, List<Consulting>> consultingMap;
    private static int[][] waitingTimeArray;
    private static int answer;

    public int solution(int k, int n, int[][] reqs) {
        answer = 987654321;

        consultingMap = new HashMap<>();
        for(int i = 1; i <= k; i++) {
            consultingMap.put(i, new ArrayList<>());
        }


        // 각각의 type 마다 상담원은 1명이상 이므로
        // 각각의 type의 최대 상담원 수는 n - k + 1 이다.
        waitingTimeArray = new int[k + 1][n - k + 2];

        for(int [] req : reqs) {
            int st = req[0];
            int et = req[1];
            int type = req[2];
            List<Consulting> consultingList = consultingMap.get(type);
            consultingList.add(new Consulting(type, st, et));
            consultingMap.put(type, consultingList);
        }

        for(int i = 1; i <= k; i++) {
            for(int j = 1; j < n - k + 2; j++) {
                // i번 상담을 하는 상담원이 j 만큼 있을 때
                // i번 상담을 하는 멘티들이 기다려야하는 시간
                waitingTimeArray[i][j] = calculateWaitingTime(i, j);
            }
        }

        int[] mentors = new int[k + 1];
        Arrays.fill(mentors, 1);

        calculateMinWaitingTime(1, k, n, mentors);

        return answer;
    }

    private void calculateMinWaitingTime(int type, int count, int maxMentor, int[] mentors) {

        if(count == maxMentor) {
            int result = 0;
            for(int i = 1; i < mentors.length; i++) {
                result += waitingTimeArray[i][mentors[i]];
            }
            answer = Math.min(answer, result);
            return;
        }

        if(type == mentors.length - 1) {
            mentors[type] += (maxMentor - count);
            calculateMinWaitingTime(type + 1, maxMentor, maxMentor, mentors);
            mentors[type] -= (maxMentor - count);
            return;
        }


        for(int i = 0; i <= maxMentor - count; i++) {
            mentors[type] += i;
            calculateMinWaitingTime(type + 1, count + i, maxMentor, mentors);
            mentors[type] -= i;
        }
    }

    private int calculateWaitingTime(int type, int count) {
        List<Consulting> consultingList = consultingMap.get(type);

        int waitingTime = 0;

        // mentor PriorityQueue
        // 우선순위 큐로 저장했기 때문에 빨리 끝나는 멘토가 가장 앞에 존재할 것
        PriorityQueue<Integer> mentorQueue = new PriorityQueue<>();

        // count 만큼의 멘토들은 처음에는 시작하지 않아 0으로 초기화한다.
        for(int i = 0; i < count; i++) {
            mentorQueue.add(0);
        }

        for(Consulting consulting : consultingList) {
            // 멘토 poll
            int cMentor = mentorQueue.poll();

            // 해당 멘토가 컨설팅을 할 수 있는 경우
            // 해당 컨설팅이 끝나는 시간을 큐에 저장.
            if(cMentor < consulting.startTime) {
                mentorQueue.add(consulting.startTime + consulting.consultingTime);
                continue;
            }

            // 해당 멘토가 컨설팅을 할 수 없는 경우
            // 기다렸다가 멘토의 상담이 끝나는 시간에 상담시작
            waitingTime += cMentor - consulting.startTime;
            mentorQueue.add(cMentor + consulting.consultingTime);
        }

        return waitingTime;
    }

    private class Consulting {
        private int type;
        private int startTime;
        private int consultingTime;

        public Consulting(int type, int startTime, int consultingTime) {
            this.type = type;
            this.startTime = startTime;
            this.consultingTime = consultingTime;
        }
    }
}
