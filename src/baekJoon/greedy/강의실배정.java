package baekJoon.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int answer = 0;

        Class[] classes = new Class[n];

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(input.nextToken());
            int e = Integer.parseInt(input.nextToken());

            classes[i] = new Class(s, e);
        }

        // 강의가 끝난 후 시간
        Arrays.sort(classes);
        int time = classes[0].end;

        // 강의실
        PriorityQueue<ClassRoom> pq = new PriorityQueue<>();
        pq.add(new ClassRoom(time));

        // 다음 강의가 강의실이 필요한지 확인
        for (int i = 1; i < n; i++) {
            int start = classes[i].start;

            while (!pq.isEmpty()) {

                ClassRoom prev = pq.peek();

                // 강의실이 필요없는 경우
                if (prev.time <= start) {
                    pq.poll(); // 현재 강의가 끝난 시간으로 다시 강의실을 업데이트
                    continue;
                }
                // 강의실이 필요한 경우
                break;
            }
            pq.add(new ClassRoom(classes[i].end));
            answer = Math.max(answer, pq.size());
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Class implements Comparable<Class>{

        int start;
        int end;
        public Class (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }

    }

    private static class ClassRoom implements Comparable<ClassRoom>{
        int time;

        public ClassRoom(int time) {
            this.time = time;
        }

        @Override
        public int compareTo(ClassRoom o) {
            return this.time - o.time;
        }
    }
}
