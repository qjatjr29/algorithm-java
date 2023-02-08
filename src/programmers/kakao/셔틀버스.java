package programmers.kakao;

import java.util.PriorityQueue;

public class 셔틀버스 {

  public String solution(int n, int t, int m, String[] timetable) {
    String answer = "";

    PriorityQueue<Crew> crews = new PriorityQueue<>();

    for(int i = 0; i < timetable.length; i++) {
      crews.add(new Crew(timetable[i]));
    }

    int busArriveHour = 9;
    int bustArriveMin = 0;

    // 막차 전 버스 순환
    for(int i = 0; i < n - 1; i++) {

      System.out.println("버스 도착 시간");
      System.out.println(busArriveHour + " : " + bustArriveMin);

      for(int j = 0; j < m; j++) {

        if(crews.isEmpty()) break;
        Crew crew = crews.peek();

        System.out.println("크루 도착 시간");
        System.out.println(crew.arriveHour + " : " + crew.arriveMin);

        // 크루 도착 시간이 버스 시간보다 늦는 경우
        if(crew.arriveHour > busArriveHour) break;
        else if(crew.arriveHour == busArriveHour) {
          if(crew.arriveMin > bustArriveMin) break;
        }

        // 버스 도착 전에 크루가 도착한 경우
        crews.poll();
      }

      // 버스 시간 갱신
      bustArriveMin += t;
      if(bustArriveMin >= 60) {
        busArriveHour++;
        bustArriveMin -= 60;
      }
    }
    System.out.println("막차 도착 시간");
    System.out.println(busArriveHour + " : " + bustArriveMin);

    int cnt = 0;
    Crew lastCrew = null;

    while(!crews.isEmpty()) {

      Crew crew = crews.peek();

      // 버스 출발 시간보다 늦게 도착.
      if(crew.arriveHour > busArriveHour) break;
      else if(crew.arriveHour == busArriveHour) {
        if(crew.arriveMin > bustArriveMin) break;
      }

      cnt++;
      lastCrew = crews.poll();
      if(cnt >= m) break;
    }

    // 막차에 모두 탑승 할 수 있는 경우..
    // 콘이 탑승하기 위해서는 마지막 크루보다 1분 더 빨리 와야함.
    if(cnt >= m) {
      int answerHour = lastCrew.arriveHour;
      int answerMin = lastCrew.arriveMin - 1;
      if (answerMin < 0) {
        answerHour--;
        answerMin += 60;
      }
      answer = getAnswer(answerHour, answerMin);
    }

    // 막차에 아직 자리가 남은 경우 - 막차시간에 도착하면 된다.
    else {
      answer = getAnswer(busArriveHour, bustArriveMin);
    }

    return answer;
  }

  private String getAnswer(int answerHour, int answerMin) {
    String result;
    if(answerHour < 10) {
      if(answerMin < 10) {
        result = "0" + answerHour + ":0" + answerMin;
      }
      else result = "0" + answerHour + ":" + answerMin;
    }
    else {
      if(answerMin < 10) {
        result = answerHour + ":0" + answerMin;
      }
      else result = answerHour + ":" + answerMin;
    }
    return result;
  }

  private class Crew implements Comparable<Crew> {
    int arriveHour;
    int arriveMin;

    public Crew(String time) {
      String[] timeInfo = time.split(":");
      arriveHour = Integer.parseInt(timeInfo[0]);
      arriveMin = Integer.parseInt(timeInfo[1]);
    }


    @Override
    public int compareTo(Crew o) {
      if(this.arriveHour > o.arriveHour) return 1;
      else if(this.arriveHour == o.arriveHour) {
        if(this.arriveMin > o.arriveMin) return 1;
        else return -1;
      }
      return -1;
    }
  }

}
