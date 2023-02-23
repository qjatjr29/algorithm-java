package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13904
public class 과제 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    Task[] tasks = new Task[N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int d = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      tasks[i] = new Task(d, w);
    }
    Arrays.sort(tasks);

    int answer = 0;
    boolean[] taskedDay = new boolean[1001];
    for (Task task : tasks) {
      int deadLine = task.deadline;
      int score = task.score;

      boolean canTask = false;
      while(deadLine > 0) {
        if(taskedDay[deadLine]) deadLine--;
        else {
          taskedDay[deadLine] = true;
          canTask = true;
          break;
        }
      }
      if(canTask) answer += score;
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
  private static class Task implements Comparable<Task> {
    int deadline, score;

    public Task(int deadline, int score) {
      this.deadline = deadline;
      this.score = score;
    }

    @Override
    public int compareTo(Task o) {
      if(this.score < o.score) return 1;
      else if(this.score == o.score) {
        if(this.deadline < o.deadline) return 1;
        else return -1;
      }
      return -1;
    }

  }

}
