package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11000
public class 강의실배정 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = 0;
    int N = Integer.parseInt(st.nextToken());

    Course[] courses = new Course[N];
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      courses[i] = new Course(start, end);
    }

    Arrays.sort(courses);

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    pq.add(courses[0].endTime);

    for(int i = 1; i < N; i++) {

      int start = courses[i].startTime;

      while(!pq.isEmpty()) {

        int preEndTime = pq.peek();

        // 강의실 따로 필요 없음.
        if(start >= preEndTime) pq.poll();
        // 강의실 필요
        else break;
      }
      pq.add(courses[i].endTime);
      answer = Math.max(answer, pq.size());
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static class Course implements Comparable<Course> {
    int startTime;
    int endTime;

    public Course(int startTime, int endTime) {
      this.startTime = startTime;
      this.endTime = endTime;
    }

    @Override
    public int compareTo(Course o) {
      return this.startTime - o.startTime;
    }
  }

}
