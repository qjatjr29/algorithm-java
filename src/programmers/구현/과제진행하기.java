package programmers.구현;
import java.util.*;


// https://school.programmers.co.kr/learn/courses/30/lessons/176962
public class 과제진행하기 {

  public String[] solution(String[][] plans) {
    List<String> answer = new ArrayList<>();

    // 과제들
    PriorityQueue<Task> tasks = new PriorityQueue<>();

    // 다 하지 못한 과제들
    Stack<Task> remainTasks = new Stack<>();

    int size = plans.length;

    for(int i = 0; i < size; i++) {
      String[] plan = plans[i];
      tasks.add(new Task(plan[0], plan[1], Integer.parseInt(plan[2])));
    }

    while(!tasks.isEmpty()) {

      Task task = tasks.poll();

      String taskName = task.name;
      int startTime = task.startTime;
      int playtime = task.playtime;

      int currentTime = startTime;

      if(!tasks.isEmpty()) {

        Task next = tasks.peek();

        int finishTime = currentTime + playtime;

        // 현재 진행중인 과제를 먼저 끝낼 수 있는 경우
        if(finishTime < next.startTime) {

          answer.add(taskName);
          currentTime = finishTime;

          // 끝내지 못한 과제를 해결
          while(!remainTasks.empty()) {

            Task remain = remainTasks.pop();

            String rName = remain.name;
            int remainTime = remain.playtime;
            int rFinishTime = currentTime + remainTime;

            if(rFinishTime <= next.startTime) {
              answer.add(rName);
              currentTime = rFinishTime;
              continue;
            }

            else {
              int gap = remain.playtime - (next.startTime - currentTime);
              remain.playtime = gap;
              remainTasks.push(remain);
              break;
            }
          }

        }

        // 현재 진행중인 과제 끝나는 시간 == 다음 과제 시작 시간
        else if(finishTime == next.startTime) {
          answer.add(taskName);
          continue;
        }

        else {
          int gap = next.startTime - currentTime;
          task.playtime = playtime - gap;
          remainTasks.push(task);
        }

      }

      else {

        // 남아있는 과제가 존재
        if(!remainTasks.empty()) {

          answer.add(taskName);

          // 남아있는 과제들을 정해진 순서대로 끝내면 됨
          while(!remainTasks.empty()) {
            Task remain = remainTasks.pop();
            answer.add(remain.name);
          }
        }
        else {
          answer.add(taskName);
          currentTime += playtime;
        }

      }
    }


    return answer.toArray(new String[size]);
  }

  private class Task implements Comparable<Task>{

    String name;
    int startTime;
    int playtime;


    Task(String name, String startTime, int playtime) {
      this.name = name;
      String[] times = startTime.split(":");
      this.startTime = 60 * Integer.parseInt(times[0]) + Integer.parseInt(times[1]);
      this.playtime = playtime;
    }


    @Override
    public int compareTo(Task o) {
      return this.startTime - o.startTime;
    }

  }
}
