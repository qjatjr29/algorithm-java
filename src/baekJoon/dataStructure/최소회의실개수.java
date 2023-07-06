package baekJoon.dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소회의실개수 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = 0;
    int N = Integer.parseInt(st.nextToken());
    PriorityQueue<Meeting> meetings = new PriorityQueue<>();
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      meetings.add(new Meeting(start, true));
      meetings.add(new Meeting(end, false));
    }

    int meetingRoom = 0;
    while(!meetings.isEmpty()) {
      Meeting meeting = meetings.poll();
      boolean isStart = meeting.isStart;

      if(isStart) {
        meetingRoom++;
        answer = Math.max(answer, meetingRoom);
      }
      else {
        meetingRoom--;
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Meeting implements Comparable<Meeting> {
    int time;
    boolean isStart;

    public Meeting(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }

    @Override
    public int compareTo(Meeting o) {
      return this.time - o.time;
    }
  }

}
