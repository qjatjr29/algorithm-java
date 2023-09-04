package goorm;

import java.io.*;
import java.util.*;

public class 외주 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int taskCnt = Integer.parseInt(input.nextToken());
        long answer = 0;
        Task[] tasks = new Task[taskCnt];
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();

        for(int i = 0; i < taskCnt; i++) {
            input = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(input.nextToken());
            int deadLine = Integer.parseInt(input.nextToken());
            tasks[i] = new Task(i, cost, deadLine);
        }

        Arrays.sort(tasks, new Comparator<Task>(){
            @Override
            public int compare(Task t1, Task t2) {
                return t1.deadLine - t2.deadLine;
            }
        });


        int index = taskCnt - 1;
        int day = tasks[index].deadLine;

        while(day > 0) {
            int temp = -1;
            for(int i = index; i >= 0; i--) {

                if(tasks[i].deadLine < day) break;

                taskQueue.add(tasks[i]);
                temp = i;
            }

            if(temp != -1) index = temp - 1;

            if(!taskQueue.isEmpty()) {
                Task next = taskQueue.poll();
                answer += next.cost;
            }
            day--;
        }


        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Task implements Comparable<Task> {
        int index;
        int cost;
        int deadLine;

        Task(int index, int cost, int deadLine) {
            this.index = index;
            this.cost = cost;
            this.deadLine = deadLine;
        }

        @Override
        public int compareTo(Task o) {
            return o.cost - this.cost;
        }

    }

}
