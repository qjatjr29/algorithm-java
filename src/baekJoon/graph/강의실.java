package baekJoon.graph;

import static java.util.Collections.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실 {

  private static int N, answer;
  private static PriorityQueue<ClassRoom> startTime;
  private static PriorityQueue<ClassRoom> endTime;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    startTime = new PriorityQueue<>();
    endTime = new PriorityQueue<>();

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int idx = Integer.parseInt(st.nextToken());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      startTime.add(new ClassRoom(idx, start));
      endTime.add(new ClassRoom(idx, end));
    }

    sol();

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class ClassRoom implements Comparable<ClassRoom> {
    int idx, time;

    public ClassRoom(int idx, int time) {
      this.idx = idx;
      this.time = time;
    }

    @Override
    public int compareTo(ClassRoom o) {
      if(this.time > o.time) return 1;
      else if(this.time == o.time) return 0;
      else return -1;
    }
  }

  private static void sol() {

    while(true) {
      if(startTime.isEmpty()) break;

      ClassRoom start = startTime.poll();
      int sTime = start.time;

      ClassRoom end = endTime.peek();
      int eTime = end.time;

      if(sTime < eTime) {
        answer++;
      } else {
        endTime.poll();
      }
    }
  }

}
