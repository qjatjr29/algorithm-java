package programmers.kakao;

import static java.lang.Math.max;
import static java.util.Collections.sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class 추석트래픽 {

  private static int answer;
  private static List<Log> logDatas;

  public static int solution(String[] lines) {

    logDatas = new ArrayList<>();

    for (String line : lines) {

      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

      String[] info = line.split(" ");
      String gap = info[2].split("s")[0];
      String dateStr = info[0] + " " + info[1];

      // 처리 시간
      double processingTime = Double.parseDouble(gap);
//      processingTime += 1;

      Date endDate = null;
      Date startDate = null;
      Calendar calendar = Calendar.getInstance();

      try {
        endDate = dateFormat.parse(dateStr);
        calendar.setTime(endDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      calendar.add(Calendar.SECOND, 1);
      try {
        endDate = dateFormat.parse(dateFormat.format(calendar.getTime()));
      } catch (ParseException e) {
        e.printStackTrace();
      }

      calendar.add(Calendar.SECOND, -1);
      calendar.add(Calendar.MILLISECOND, (int)(-(processingTime - 0.001) * 1000));

      try {
        startDate = dateFormat.parse(dateFormat.format(calendar.getTime()));
      } catch (ParseException e) {
        e.printStackTrace();
      }

      Long endTime = endDate.getTime();
      Long startTime = startDate.getTime();

//      System.out.println(startDate +  " : " + endDate);

      logDatas.add(new Log(startTime, endTime));
    }

    sort(logDatas);

    for(int i = 0; i < logDatas.size(); i++) {
      Log log = logDatas.get(i);

//      System.out.println(log.start + " " + log.end);

      int count = 1;
      for(int j = 0; j < i; j++) {
        if(logDatas.get(j).end > log.start) count++;
      }
      answer = max(answer, count);
    }
    return answer;
  }

  private static class Log implements Comparable<Log> {
    Long start, end;

    public Log(Long start, Long end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Log o) {
      if(this.start > o.start) return 1;
      else if (this.start.equals(o.start)) {
        if(this.end > o. end) return 1;
        else if(this.end.equals(o.end)) return 0;
        else return -1;
      }
      else return -1;
    }
  }

  public static void main(String[] args) {

//    String[] lines = {"2016-09-15 00:00:00.000 3s"};
//    String[] lines = {"2016-09-15 20:59:57.421 0.351s",
//        "2016-09-15 20:59:58.233 1.181s",
//        "2016-09-15 20:59:58.299 0.8s",
//        "2016-09-15 20:59:58.688 1.041s",
//        "2016-09-15 20:59:59.591 1.412s",
//        "2016-09-15 21:00:00.464 1.466s",
//        "2016-09-15 21:00:00.741 1.581s",
//        "2016-09-15 21:00:00.748 2.31s",
//        "2016-09-15 21:00:00.966 0.381s",
//        "2016-09-15 21:00:02.066 2.62s"
//    };
//    String[] lines = {"2016-09-15 01:00:04.002 2.0s",
//        "2016-09-15 01:00:07.000 2s"};

    String[] lines ={"2016-09-15 01:00:04.001 2.0s",
        "2016-09-15 01:00:07.000 2s"};

    System.out.println(solution(lines));
  }

}
